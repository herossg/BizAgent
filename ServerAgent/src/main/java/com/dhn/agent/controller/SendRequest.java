package com.dhn.agent.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import com.dhn.agent.model.Alimtalk;
import com.dhn.agent.model.Attachment;
import com.dhn.agent.model.DhnRequest;
import com.dhn.agent.model.DhnResult;
import com.dhn.agent.model.Friendtalk;
import com.dhn.agent.service.DhnRequestService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SendRequest {
	
	private static String PROFILE_KEY = "8afe8c802d0b767929c58e4c193c24517a9341db";
	private static String API_SERVER = "https://dev-bzm-api.kakao.com";
	
	private static DhnRequestService dhnReqService ;
	
	public static boolean isRunning = false;
	private static final Logger log = LoggerFactory.getLogger(SendRequest.class);
	private static Environment env;
	
	@Autowired
	public SendRequest(DhnRequestService dhnReqService, Environment env) {
		SendRequest.dhnReqService = dhnReqService;
		SendRequest.env = env;
		SendRequest.API_SERVER = env.getProperty("API_SERVER");
		SendRequest.PROFILE_KEY = env.getProperty("PROFILE_KEY");
	}
	
	public static void run() {
		if(!isRunning) {
			try {
				isRunning = true;
				List<DhnRequest> dhnReqs = dhnReqService.findAll();
				
				if(dhnReqs != null && dhnReqs.size() > 0) {
					
					HttpHeaders headers = new HttpHeaders();
					final String URL = API_SERVER + "/v2/" + PROFILE_KEY + "/sendMessage";
					List<MediaType> accept = new ArrayList<>();
					accept.add(MediaType.APPLICATION_JSON);
					
					headers.setContentType(MediaType.APPLICATION_JSON);
					headers.setAccept(accept);
					
					ObjectMapper mapper = new ObjectMapper();
					mapper.setSerializationInclusion(Include.NON_NULL);

					//log.info("Request Send Start !! ");
					
					AsyncRestTemplate restTemp = new AsyncRestTemplate();
					
					for(int i=0; i<dhnReqs.size(); i++) {
						DhnRequest dr = (DhnRequest)dhnReqs.get(i);

						
						if(dr.getMESSAGE_TYPE().toLowerCase().equals("at")) {
							
							Date now = new Date();
							SimpleDateFormat serialformat = new SimpleDateFormat("yyyyMMdd-");
							
							Alimtalk alimtalk = new Alimtalk();
							
							alimtalk.setMessage_type("AT");
							alimtalk.setSerial_number(serialformat.format(now) + dr.getMSGID());
							alimtalk.setSender_key(dr.getPROFILE());
							alimtalk.setPhone_number(dr.getPHN());
							//alimtalk.setApp_user_id("");
							alimtalk.setTemplate_code(dr.getTMPL_ID());
							alimtalk.setMessage(dr.getMSG().replaceAll("(\r\n|\r|\n|\n\r)", "\n"));
							alimtalk.setResponse_method("polling");
							alimtalk.setTimeout(30);
							
							Attachment att = new Attachment();
							if(dr.getBUTTON1() != null && !dr.getBUTTON1().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON1(), new TypeReference<Map<String, String>>(){}) );

							if(dr.getBUTTON2() != null && !dr.getBUTTON2().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON2(), new TypeReference<Map<String, String>>(){}) );
							
							if(dr.getBUTTON3() != null && !dr.getBUTTON3().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON3(), new TypeReference<Map<String, String>>(){}) );
							
							if(dr.getBUTTON4() != null && !dr.getBUTTON4().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON4(), new TypeReference<Map<String, String>>(){}) );

							if(dr.getBUTTON5() != null && !dr.getBUTTON5().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON5(), new TypeReference<Map<String, String>>(){}) );
							
							if(att.button.size() > 0)
								alimtalk.setAttachment( att ); 
							
							alimtalk.setChannel_key("base");
						
							String jsonStr = mapper.writeValueAsString(alimtalk);
							
							log.info("AT JSON - : " + jsonStr);
							
							HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);						
							
							ListenableFuture<ResponseEntity<String>> response = restTemp.postForEntity(URL, entity, String.class);
							response.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

								@Override
								public void onSuccess(ResponseEntity<String> result) {
									Map<String, String> res;
									try {
										res = mapper.readValue(result.getBody(),  new TypeReference<Map<String, String>>(){});
										SaveResult.UpdateResult(dr.getMSGID(), res.get("code"), res.get("message"));
										log.info("FT Response Success : " + result );
									} catch (JsonProcessingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									} 
								}

								@Override
								public void onFailure(Throwable ex) {
									// TODO Auto-generated method stub
									log.info("AT Response Failure : " + ex.toString() );
								}
							});
							
							dhnReqService.deleteByMsgidQeury(dr.getMSGID());
							
							DhnResult DR = new DhnResult();
							
							DR.setAd_flag(dr.getAD_FLAG());
							DR.setButton1(dr.getBUTTON1());
							DR.setButton2(dr.getBUTTON2());
							DR.setButton3(dr.getBUTTON3());
							DR.setButton4(dr.getBUTTON4());
							DR.setButton5(dr.getBUTTON5());
							DR.setImage_link(dr.getIMAGE_LINK());
							DR.setImage_url(dr.getIMAGE_URL());
							DR.setMessage_type(dr.getMESSAGE_TYPE());
							DR.setMsg(dr.getMSG());
							DR.setMsg_sms(dr.getMSG_SMS());
							DR.setMsgid(dr.getMSGID());
							DR.setOnly_sms(dr.getONLY_SMS());
							DR.setP_com(dr.getP_COM());
							DR.setP_invoice(dr.getP_INVOICE());
							DR.setPhn(dr.getPHN());
							DR.setProfile(dr.getPROFILE());
							DR.setReg_dt(dr.getREG_DT());
							DR.setRemark1(dr.getREMARK1());
							DR.setRemark2(dr.getREMARK2());
							DR.setRemark3(dr.getREMARK3());
							DR.setRemark4(dr.getREMARK4());
							DR.setRemark5(dr.getREMARK5());
							DR.setReserve_dt(dr.getRESERVE_DT());
							DR.setResult("N");
							DR.setS_code(dr.getS_CODE());
							DR.setSms_kind(dr.getSMS_KIND());
							DR.setSms_lms_tit(dr.getSMS_LMS_TIT());
							DR.setSms_sender(dr.getSMS_SENDER());
							DR.setSync("N");
							DR.setTmpl_id(dr.getTMPL_ID());
							DR.setUserid(dr.getUserid());
							DR.setWide(dr.getWIDE());
							
							SaveResult.Save(DR);
							
							
						} else if(dr.getMESSAGE_TYPE().toLowerCase().equals("ft")) {
							
							Date now = new Date();
							SimpleDateFormat serialformat = new SimpleDateFormat("yyyyMMdd-");
							
							Friendtalk friendtalk = new Friendtalk();
							
							friendtalk.setMessage_type("FT");
							friendtalk.setSerial_number(serialformat.format(now) + dr.getMSGID());
							friendtalk.setSender_key(dr.getPROFILE());
							friendtalk.setPhone_number(dr.getPHN());
							//friendtalk.setApp_user_id("");
							friendtalk.setUser_key("");
							friendtalk.setMessage(dr.getMSG().replaceAll("(\r\n|\r|\n|\n\r)", "\n"));
							friendtalk.setAd_flag(dr.getAD_FLAG());
							friendtalk.setWide(dr.getWIDE());
							
							Attachment att = new Attachment();
							
							if(dr.getBUTTON1() != null && !dr.getBUTTON1().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON1(), new TypeReference<Map<String, String>>(){}) );

							if(dr.getBUTTON2() != null && !dr.getBUTTON2().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON2(), new TypeReference<Map<String, String>>(){}) );
							
							if(dr.getBUTTON3() != null && !dr.getBUTTON3().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON3(), new TypeReference<Map<String, String>>(){}) );
							
							if(dr.getBUTTON4() != null && !dr.getBUTTON4().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON4(), new TypeReference<Map<String, String>>(){}) );

							if(dr.getBUTTON5() != null && !dr.getBUTTON5().isEmpty())
								att.addButton( mapper.readValue(dr.getBUTTON5(), new TypeReference<Map<String, String>>(){}) );
							
							if(att.button.size() > 0)
								friendtalk.setAttachment( att ); 
							
							String jsonStr = mapper.writeValueAsString(friendtalk);
							
							log.info("FT JSON - : " + jsonStr);
							
							HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);		
							ListenableFuture<ResponseEntity<String>> response = restTemp.postForEntity(URL, entity, String.class);

							response.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

								@Override
								public void onSuccess(ResponseEntity<String> result) {
									
									Map<String, String> res;
									try {
										res = mapper.readValue(result.getBody(),  new TypeReference<Map<String, String>>(){});
										SaveResult.UpdateResult(dr.getMSGID(), res.get("code"), res.get("message"));
										log.info("FT Response Success : " + result );
									} catch (JsonProcessingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
								}

								@Override
								public void onFailure(Throwable ex) {
									// TODO Auto-generated method stub
									log.info("FT Response Failure : " + ex.toString() );
								}
							});
							
							dhnReqService.deleteByMsgidQeury(dr.getMSGID());
							
							DhnResult DR = new DhnResult();
							
							DR.setAd_flag(dr.getAD_FLAG());
							DR.setButton1(dr.getBUTTON1());
							DR.setButton2(dr.getBUTTON2());
							DR.setButton3(dr.getBUTTON3());
							DR.setButton4(dr.getBUTTON4());
							DR.setButton5(dr.getBUTTON5());
							DR.setImage_link(dr.getIMAGE_LINK());
							DR.setImage_url(dr.getIMAGE_URL());
							DR.setMessage_type(dr.getMESSAGE_TYPE());
							DR.setMsg(dr.getMSG());
							DR.setMsg_sms(dr.getMSG_SMS());
							DR.setMsgid(dr.getMSGID());
							DR.setOnly_sms(dr.getONLY_SMS());
							DR.setP_com(dr.getP_COM());
							DR.setP_invoice(dr.getP_INVOICE());
							DR.setPhn(dr.getPHN());
							DR.setProfile(dr.getPROFILE());
							DR.setReg_dt(dr.getREG_DT());
							DR.setRemark1(dr.getREMARK1());
							DR.setRemark2(dr.getREMARK2());
							DR.setRemark3(dr.getREMARK3());
							DR.setRemark4(dr.getREMARK4());
							DR.setRemark5(dr.getREMARK5());
							DR.setReserve_dt(dr.getRESERVE_DT());
							DR.setResult("N");
							DR.setS_code(dr.getS_CODE());
							DR.setSms_kind(dr.getSMS_KIND());
							DR.setSms_lms_tit(dr.getSMS_LMS_TIT());
							DR.setSms_sender(dr.getSMS_SENDER());
							DR.setSync("N");
							DR.setTmpl_id(dr.getTMPL_ID());
							DR.setUserid(dr.getUserid());
							DR.setWide(dr.getWIDE());
							
							SaveResult.Save(DR);
							
						}
						
					}
					log.info("Request Send : " + dhnReqs.size());
				}
			} catch (Exception e) {
				log.info(e.toString());
			} finally {
				isRunning = false;	
			}
		}
	}
}
