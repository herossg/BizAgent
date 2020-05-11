package com.dhn.agent.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	private DhnRequestService dhnReqService ;
	
	public static boolean isRunning = false;
	private static final Logger log = LoggerFactory.getLogger(SendRequest.class);
	private Environment env;
	
	public SendRequest(DhnRequestService dhnReqService, Environment env) {
		this.dhnReqService = dhnReqService;
		this.env = env;
		SendRequest.API_SERVER = env.getProperty("API_SERVER");
		SendRequest.PROFILE_KEY = env.getProperty("PROFILE_KEY");
	}
	
	public void run() {
		//log.info("발송 호출 됨");
		if(!isRunning) {
			isRunning = true;
			
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
			Date time = new Date();
			String sendgroup = format1.format(time);
			
			//List<String> msgids = dhnReqService.findByNullSendgroupQuery();
			
			dhnReqService.updateBySendgroupQuery(sendgroup);
			//dhnReqService.updateByMsgidsSendgroupQuery(sendgroup, msgids);
			isRunning = false;
			
			try {
				List<DhnRequest> dhnReqs = dhnReqService.findBySendgroupQuery(sendgroup);
				
				if(dhnReqs != null && dhnReqs.size() > 0) {
					log.info(sendgroup + " 발송 시작");

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

						
						if(dr.getMESSAGETYPE().toLowerCase().equals("at")) {
							
							Date now = new Date();
							SimpleDateFormat serialformat = new SimpleDateFormat("yyyyMMdd-");
							
							Alimtalk alimtalk = new Alimtalk();
							
							alimtalk.setMessage_type("AT");
							alimtalk.setSerial_number(serialformat.format(now) + dr.getMSGID());
							alimtalk.setSender_key(dr.getPROFILE());
							alimtalk.setPhone_number(dr.getPHN());
							//alimtalk.setApp_user_id("");
							alimtalk.setTemplate_code(dr.getTMPLID());
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
							
							//log.info("AT JSON - : " + jsonStr);
							
							HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);						
							
							ListenableFuture<ResponseEntity<String>> response = restTemp.postForEntity(URL, entity, String.class);
							
							
							//dhnReqService.deleteByMsgidQeury(dr.getMSGID());
							
							DhnResult DR = new DhnResult();
							
							DR.setAdflag(dr.getADFLAG());
							DR.setButton1(dr.getBUTTON1());
							DR.setButton2(dr.getBUTTON2());
							DR.setButton3(dr.getBUTTON3());
							DR.setButton4(dr.getBUTTON4());
							DR.setButton5(dr.getBUTTON5());
							DR.setImagelink(dr.getIMAGELINK());
							DR.setImageurl(dr.getIMAGEURL());
							DR.setMessagetype(dr.getMESSAGETYPE());
							DR.setMsg(dr.getMSG());
							DR.setMsgsms(dr.getMSGSMS());
							DR.setMsgid(dr.getMSGID());
							DR.setOnlysms(dr.getONLYSMS());
							DR.setPcom(dr.getPCOM());
							DR.setPinvoice(dr.getPINVOICE());
							DR.setPhn(dr.getPHN());
							DR.setProfile(dr.getPROFILE());
							DR.setRegdt(dr.getREGDT());
							DR.setRemark1(dr.getREMARK1());
							DR.setRemark2(dr.getREMARK2());
							DR.setRemark3(dr.getREMARK3());
							DR.setRemark4(dr.getREMARK4());
							DR.setRemark5(dr.getREMARK5());
							DR.setReservedt(dr.getRESERVEDT());
							DR.setResult("N");
							DR.setScode(dr.getSCODE());
							DR.setSmskind(dr.getSMSKIND());
							DR.setSmslmstit(dr.getSMSLMSTIT());
							DR.setSmssender(dr.getSMSSENDER());
							DR.setSync("N");
							DR.setTmplid(dr.getTMPLID());
							DR.setUserid(dr.getUserid());
							DR.setWide(dr.getWIDE());
							
							SaveResult.Save(DR);
							
							
							response.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

								@Override
								public void onSuccess(ResponseEntity<String> result) {
									Map<String, String> res;
									try {
										res = mapper.readValue(result.getBody(),  new TypeReference<Map<String, String>>(){});
										
										if(dr.getONLYSMS().equals("N")) {        // request 테이블에 onlysms 필드가 "N" 이면 1차 카카오 2차 SMS 발송을 한다.
											SaveResult.UpdateResult(dr.getMSGID(), res.get("code"), res.get("message"), "Y");
										}else if(dr.getONLYSMS().equals("D")) {  // request 테이블에 onlysms 필드가 "D" 이면 1차 카카오 2차 SMS 발송을 한다.
											SaveResult.UpdateResult(dr.getMSGID(), res.get("code"), res.get("message"), "D");
										}
										
										 
									} catch (JsonProcessingException e) {
										// TODO Auto-generated catch block
										log.info("알림톡  발송 오류 : " + e.toString());
									} 
								}

								@Override
								public void onFailure(Throwable ex) {
									// TODO Auto-generated method stub
									log.info(Thread.currentThread().getName() + " / " + "AT Response Failure : " + ex.toString() );
									//e_cnt++;
								}
							});

						} else if(dr.getMESSAGETYPE().toLowerCase().equals("ft")) {
							
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
							friendtalk.setAd_flag(dr.getADFLAG());
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
							
							if(dr.getIMAGEURL() != null && !dr.getIMAGEURL().isEmpty()) {
								att.addImage(dr.getIMAGELINK(), dr.getIMAGEURL());
							}
							
							if(att.button.size() > 0)
								friendtalk.setAttachment( att ); 
							
							String jsonStr = mapper.writeValueAsString(friendtalk);
							
							//log.info("FT JSON - : " + jsonStr);
							
							HttpEntity<String> entity = new HttpEntity<String>(jsonStr,headers);		
							ListenableFuture<ResponseEntity<String>> response = restTemp.postForEntity(URL, entity, String.class);

							//dhnReqService.deleteByMsgidQeury(dr.getMSGID());
							
							DhnResult DR = new DhnResult();
							
							DR.setAdflag(dr.getADFLAG());
							DR.setButton1(dr.getBUTTON1());
							DR.setButton2(dr.getBUTTON2());
							DR.setButton3(dr.getBUTTON3());
							DR.setButton4(dr.getBUTTON4());
							DR.setButton5(dr.getBUTTON5());
							DR.setImagelink(dr.getIMAGELINK());
							DR.setImageurl(dr.getIMAGEURL());
							DR.setMessagetype(dr.getMESSAGETYPE());
							DR.setMsg(dr.getMSG());
							DR.setMsgsms(dr.getMSGSMS());
							DR.setMsgid(dr.getMSGID());
							DR.setOnlysms(dr.getONLYSMS());
							DR.setPcom(dr.getPCOM());
							DR.setPinvoice(dr.getPINVOICE());
							DR.setPhn(dr.getPHN());
							DR.setProfile(dr.getPROFILE());
							DR.setRegdt(dr.getREGDT());
							DR.setRemark1(dr.getREMARK1());
							DR.setRemark2(dr.getREMARK2());
							DR.setRemark3(dr.getREMARK3());
							DR.setRemark4(dr.getREMARK4());
							DR.setRemark5(dr.getREMARK5());
							DR.setReservedt(dr.getRESERVEDT());
							DR.setResult("N");
							DR.setScode(dr.getSCODE());
							DR.setSmskind(dr.getSMSKIND());
							DR.setSmslmstit(dr.getSMSLMSTIT());
							DR.setSmssender(dr.getSMSSENDER());
							DR.setSync("N");
							DR.setTmplid(dr.getTMPLID());
							DR.setUserid(dr.getUserid());
							DR.setWide(dr.getWIDE());
							
							SaveResult.Save(DR);
							
							response.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

								@Override
								public void onSuccess(ResponseEntity<String> result) {
									
									Map<String, String> res;
									try {
										res = mapper.readValue(result.getBody(),  new TypeReference<Map<String, String>>(){});
										
										if(dr.getONLYSMS().equals("N")) {        // request 테이블에 onlysms 필드가 "N" 이면 1차 카카오 2차 SMS 발송을 한다.
											SaveResult.UpdateResult(dr.getMSGID(), res.get("code"), res.get("message"), "Y");
										}else if(dr.getONLYSMS().equals("D")) {  // request 테이블에 onlysms 필드가 "D" 이면 1차 카카오 2차 SMS 발송을 한다.
											SaveResult.UpdateResult(dr.getMSGID(), res.get("code"), res.get("message"), "D");
										}
										
									} catch (JsonProcessingException e) {
										// TODO Auto-generated catch block
										log.info("친구톡 발송 오류 : " + e.toString());
									}
								}

								@Override
								public void onFailure(Throwable ex) {
									// TODO Auto-generated method stub
									//log.info(Thread.currentThread().getName() + " / " + "FT Response Failure : " + ex.toString() );
									//e_cnt++;
								}
							});
							
						} else if(dr.getMESSAGETYPE().toLowerCase().equals("ph")) {
						 
							DhnResult DR = new DhnResult();
							
							DR.setAdflag(dr.getADFLAG());
							DR.setButton1(dr.getBUTTON1());
							DR.setButton2(dr.getBUTTON2());
							DR.setButton3(dr.getBUTTON3());
							DR.setButton4(dr.getBUTTON4());
							DR.setButton5(dr.getBUTTON5());
							DR.setImagelink(dr.getIMAGELINK());
							DR.setImageurl(dr.getIMAGEURL());
							DR.setMessagetype(dr.getMESSAGETYPE());
							DR.setMsg(dr.getMSG());
							DR.setMsgsms(dr.getMSGSMS());
							DR.setMsgid(dr.getMSGID());
							DR.setOnlysms(dr.getONLYSMS());
							DR.setPcom(dr.getPCOM());
							DR.setPinvoice(dr.getPINVOICE());
							DR.setPhn(dr.getPHN());
							DR.setProfile(dr.getPROFILE());
							DR.setRegdt(dr.getREGDT());
							DR.setRemark1(dr.getREMARK1());
							DR.setRemark2(dr.getREMARK2());
							DR.setRemark3(dr.getREMARK3());
							DR.setRemark4(dr.getREMARK4());
							DR.setRemark5(dr.getREMARK5());
							DR.setReservedt(dr.getRESERVEDT());
							DR.setResult("D");
							DR.setScode(dr.getSCODE());
							DR.setSmskind(dr.getSMSKIND());
							DR.setSmslmstit(dr.getSMSLMSTIT());
							DR.setSmssender(dr.getSMSSENDER());
							DR.setSync("N");
							DR.setTmplid(dr.getTMPLID());
							DR.setUserid(dr.getUserid());
							DR.setWide(dr.getWIDE());
							
							SaveResult.Save(DR);
							
						}
						
					}
					dhnReqService.deleteAll(dhnReqs);
					log.info(sendgroup + " : " + "발송 종료 : " + dhnReqs.size());
				}
			} catch (Exception e) {
				log.info(Thread.currentThread().getName() + " / " + e.toString());
			} finally {
			}			
			
		}
	}
	
}
