package com.dhn.client.controller;

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

import com.dhn.client.model.DhnResult;
import com.dhn.client.service.DhnResultService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class GetResult {
	private static DhnResultService dhnResService;
	
	public static boolean isRunning = false;
	private static final Logger log = LoggerFactory.getLogger(GetResult.class);
	private static Environment env;
	private static int totalcnt;
	@Autowired
	public GetResult(DhnResultService dhnresService, Environment env) {
		GetResult.dhnResService = dhnresService;
		GetResult.env = env;
	}
	
	public static void run() {
		if(!isRunning) {
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
			Date time = new Date();
			String starttime = format1.format(time);
			totalcnt =0;
			try {
				isRunning = true;
				//List<DhnResult> dhnResults = dhnResService.findByRESULT("N");
				
				//if(dhnResults != null && dhnResults.size() > 0 ) {
					final String URL = env.getProperty("server") + "resall";
					HttpHeaders headers = new HttpHeaders();
	
					headers.setContentType(MediaType.APPLICATION_JSON);
					headers.set("userid", env.getProperty("userid"));
					//log.info("Request Send Start !! ");
					
					ObjectMapper mapper = new ObjectMapper();
					mapper.setSerializationInclusion(Include.NON_NULL);

					AsyncRestTemplate restTemp = new AsyncRestTemplate();
					
					
					//for(int i=0; i<dhnResults.size(); i++) {
					//	DhnResult dr = (DhnResult)dhnResults.get(i);
						HttpEntity<String> entity = new HttpEntity<String>(null,headers);	
						ListenableFuture<ResponseEntity<String>> response = restTemp.postForEntity(URL,	entity, String.class); 
						response.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {

							@Override
							public void onSuccess(ResponseEntity<String> result) {
								Map<String, String> res;
								List<DhnResult> dhnResutls = new ArrayList<DhnResult>();
								//log.info("길이 : " + result.getBody().length());
								try {
									JsonNode json = mapper.readTree(result.getBody());
									//log.info("길이 : " + json.size());
									json.forEach(r -> {
										totalcnt++;
										DhnResult dhnResult = new DhnResult();

										if(r.get("msgid") != null)
											dhnResult.setMSGID(r.get("msgid").asText()) ;
										
										if(r.get("ad_flag") != null)
											dhnResult.setADFLAG(r.get("ad_flag").asText()) ;
										
										if(r.get("button1") != null)
											dhnResult.setBUTTON1(r.get("button1").asText()) ;
										
										if(r.get("button2") != null)
											dhnResult.setBUTTON2(r.get("button2").asText()) ;
										
										if(r.get("button3") != null)
											dhnResult.setBUTTON3(r.get("button3").asText()) ;
										
										if(r.get("button4") != null)
											dhnResult.setBUTTON4(r.get("button4").asText()) ;
										
										if(r.get("button5") != null)
											dhnResult.setBUTTON5(r.get("button5").asText()) ;
										
										if(r.get("code") != null)
											dhnResult.setCODE(r.get("code").asText());
										
										if(r.get("image_link") != null)
											dhnResult.setIMAGELINK(r.get("image_link").asText()) ;
										
										if(r.get("image_url") != null)
											dhnResult.setIMAGEURL(r.get("image_url").asText()) ;
										
										if(r.get("kind") != null)
											dhnResult.setKIND(r.get("kind").asText());
										
										if(r.get("message") != null)
											dhnResult.setMESSAGE(r.get("message").asText()) ;
										
										if(r.get("message_type") != null)
											dhnResult.setMESSAGETYPE(r.get("message_type").asText()) ;
										
										if(r.get("msg") != null)
											dhnResult.setMSG(r.get("msg").asText()) ;
										
										if(r.get("msg_sms") != null)
											dhnResult.setMSGSMS(r.get("msg_sms").asText()) ;
										
										if(r.get("only_sms") != null)
											dhnResult.setONLYSMS(r.get("only_sms").asText()) ;
										
										if(r.get("p_com") != null)
											dhnResult.setPCOM(r.get("p_com").asText()) ;
										
										if(r.get("p_invoice") != null)
											dhnResult.setPINVOICE(r.get("p_invoice").asText()) ;
										
										if(r.get("phn") != null)
											dhnResult.setPHN(r.get("phn").asText()) ;
										
										if(r.get("profile") != null)
											dhnResult.setPROFILE(r.get("profile").asText()) ;
										
										//if(r.get("reg_dt") != null)
										//	dhnResult.setREGDT(r.get("reg_dt").asText()) ;
										
										if(r.get("remark1") != null)
											dhnResult.setREMARK1(r.get("remark1").asText()) ;
										
										if(r.get("remark2") != null)
											dhnResult.setREMARK2(r.get("remark2").asText()) ;
										
										if(r.get("remark3") != null)
											dhnResult.setREMARK3(r.get("remark3").asText()) ;
//										dhnResult.setREMARK3("1") ;
										
										if(r.get("remark4") != null)
											dhnResult.setREMARK4(r.get("remark4").asText()) ;
										
										if(r.get("remark5") != null)
											dhnResult.setREMARK5(r.get("remark5").asText()) ;
										
										//if(r.get("res_dt") != null)
										//	dhnResult.setRESDT(r.get("res_dt").asText()) ;
										
										if(r.get("reserve_dt") != null)
											dhnResult.setRESERVEDT(r.get("reserve_dt").asText()) ;
										
										if(r.get("result") != null) {
											String msgresult = "Y";
											if(r.get("code") != null) {
												if(!r.get("code").asText().equals("0000")) {
													msgresult = "N";
												}
											} else {
												msgresult = "N";
											}
											dhnResult.setRESULT(msgresult) ;
										}
										
										if(r.get("s_code") != null)
											dhnResult.setSCODE(r.get("s_code").asText()) ;
										
										if(r.get("sms_kind") != null)
											dhnResult.setSMSKIND(r.get("sms_kind").asText()) ;
										
										if(r.get("sms_lms_tit") != null)
											dhnResult.setSMSLMSTIT(r.get("sms_lms_tit").asText()) ;
										
										if(r.get("sms_sender") != null)
											dhnResult.setSMSSENDER(r.get("sms_sender").asText()) ;
										
										dhnResult.setSYNC("Y" ) ;
										
										if(r.get("tmpl_id") != null)
											dhnResult.setTMPLID(r.get("tmpl_id").asText()) ;
										
										if(r.get("wide") != null)
											dhnResult.setWIDE(r.get("wide").asText()) ;

										if(r.get("supplement") != null)
											dhnResult.setSUPPLEMENT(r.get("supplement").asText()) ;

										if(r.get("price") != null)
											dhnResult.setPRICE(r.get("price").asInt()) ;

										if(r.get("currency_type") != null)
											dhnResult.setCURRENCY_TYPE(r.get("currency_type").asText()) ;

										//SaveResult.Save(dhnResult);
										//log.info(r.get("msgid").asText());
										dhnResutls.add(dhnResult);
										//log.info(r.get("remark4").asText());
									
										log.info(r.get("msgid").asText() + " 결과 수신 완료");
									});
									
									if(dhnResutls.size() > 0 ) {
											String resa = dhnResService.SaveAll(dhnResutls);
											log.info("수신 결과  : " + resa);
									}
									
								} catch (JsonMappingException e) {
									log.error(e.toString());
								} catch (JsonProcessingException e) {
									log.error(e.toString());
								} catch(Exception ex) {
									log.error(ex.toString());
								}
								
								
//								try {
//									res = mapper.readValue(result.getBody(),  new TypeReference<Map<String, String>>(){});
//									if(res != null & !res.get("code").equals("9999")) {
//										log.info("Get Result Response : " + result);
//										dhnResService.updateByMsgidQuery(dr.getMSGID(), res.get("code"), res.get("message"));
//									}
//								} catch (JsonProcessingException e) {
//									// TODO Auto-generated catch block
//									e.printStackTrace();
//								}
							}

							@Override
							public void onFailure(Throwable ex) {
								// TODO Auto-generated method stub
								log.info("Get Result Response Failure : " + ex.toString() );
							}
						});
						
					//}
			//	}
			} catch(Exception ex) {
				log.info("수신 처리 중 오류 : " + ex.getLocalizedMessage());
			} finally {
				isRunning = false;
			}
			//if(totalcnt > 0)
			//	log.info(starttime + " - 끝 > " + totalcnt + " 건  수신 ");
		}
	}
	
}
