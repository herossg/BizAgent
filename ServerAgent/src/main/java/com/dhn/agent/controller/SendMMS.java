package com.dhn.agent.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhn.agent.model.DhnResult;
import com.dhn.agent.model.OshotMMS;
import com.dhn.agent.model.OshotSMS;
import com.dhn.agent.service.DhnResultService;
import com.dhn.agent.service.OshotMMSService;
import com.dhn.agent.service.OshotSMSService;

@Component
public class SendMMS {
	
	@Autowired
	private DhnResultService dhnResultService;
	
	@Autowired
	private OshotMMSService oshotMMSService;

	@Autowired
	private OshotSMSService oshotSMSService;

	public static boolean isRunning = false;
	private static final Logger log = LoggerFactory.getLogger(SendMMS.class);
	
/*	public SendMMS(DhnResultService _dhnResultService, OshotMMSService _oshotMMSService) {
		this.dhnResultService = _dhnResultService;
		this.oshotMMSService = _oshotMMSService;
	}
	*/
	public void run() {
		if(!isRunning) {
			isRunning = true;
			
			SimpleDateFormat format1 = new SimpleDateFormat ( "yyyyMMddHHmmss");
			Date time = new Date();
			String sendgroup = format1.format(time);
			
			dhnResultService.updateSendGroupByUseridSyncQuery(sendgroup);
			
			//log.info("sendgroup = " + sendgroup);
			
			try {
				List<DhnResult> dhnResult = dhnResultService.selectBySendgroupQuery(sendgroup);
				
				List<OshotMMS> oshotMMSs = new ArrayList<>();
				List<OshotSMS> oshotSMSs = new ArrayList<>();
				
				//log.info("sendgroup = " + sendgroup + " : " + dhnResult.size() + " : " + oshotMMSService.toString());
				
				for(int i=0; i<dhnResult.size(); i++) {
					DhnResult dr = (DhnResult)dhnResult.get(i);
					int strlen = byteCheck(dr.getMsg());
					log.info("DHN Result msg_id = " + dr.getMsgid() + " / 메시지 길이 : " + dr.getMsg().getBytes().length + " / " + strlen);
					
					String phn = "";
					if(dr.getPhn().substring(0, 2).equals("82")) {
						phn = "0" + dr.getPhn().substring(2);
					} else {
						phn = dr.getPhn();
					}
					
					if(strlen > 90) {
					
						OshotMMS mms = new OshotMMS();
						
						mms.setMsggroupid(dr.getRemark4());
						mms.setSender(dr.getSmssender());
						mms.setReceiver(phn);
						mms.setSubject(dr.getSmslmstit());
						mms.setMsg(dr.getMsg());
						
						if(!dr.getReservedt().equals("00000000000000")) {
							mms.setReservedt(LocalDateTime.parse(dr.getReservedt(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
						}
						
						mms.setSendresult(0);
						
						mms.setMst_id(Integer.parseInt(dr.getRemark4()));
						mms.setCb_msg_Id(dr.getMsgid());
						mms.setProc_flag("N");
						
						
						oshotMMSs.add(mms);
					} else {
						OshotSMS sms = new OshotSMS();
						
						sms.setSender(dr.getSmssender());
						sms.setReceiver(phn);
						sms.setMsg(dr.getMsg());
						
						if(!dr.getReservedt().equals("00000000000000")) {
							sms.setReservedt(LocalDateTime.parse(dr.getReservedt(), DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
						}
						
						sms.setSendresult(0);
						sms.setMst_id(Integer.parseInt(dr.getRemark4()));
						sms.setCb_msg_Id(dr.getMsgid());
						sms.setProc_flag("N");
						
						oshotSMSs.add(sms);
					}
				}
				
				if(oshotMMSs.size() > 0) {
					oshotMMSService.save(oshotMMSs);
				}

				if(oshotSMSs.size() > 0) {
					oshotSMSService.save(oshotSMSs);
				}

			}catch(Exception ex) {
				log.error("MMS 자료 생성중 오류 발생 : " + ex.toString());
			}
		}
		isRunning = false;
	}

   public int byteCheck(String txt) {
        if (txt.isEmpty()) { 
        	return 0; 
        }
 
        // 바이트 체크 (영문 1, 한글 2, 특문 1)
        int en = 0;
        int ko = 0;
        int etc = 0;
 
        char[] txtChar = txt.toCharArray();
        for (int j = 0; j < txtChar.length; j++) {
            if (txtChar[j] >= 'A' && txtChar[j] <= 'z') {
                en++;
            } else if (txtChar[j] >= '\uAC00' && txtChar[j] <= '\uD7A3') {
	                ko+=2;
	            } else {
	                etc++;
	            }
	        }
	 
	        int txtByte = en + ko + etc;
	        return txtByte;
	        
  }
  
}
