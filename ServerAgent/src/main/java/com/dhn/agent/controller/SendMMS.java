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

@Component
public class SendMMS {
	
	@Autowired
	private DhnResultService dhnResultService;
	
	@Autowired
	private OshotMMSService oshotMMSService;
	
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
				
				log.info("sendgroup = " + sendgroup + " : " + dhnResult.size() + " : " + oshotMMSService.toString());
				
				for(int i=0; i<dhnResult.size(); i++) {
					DhnResult dr = (DhnResult)dhnResult.get(i);
					log.info("DHN Result msg_id = " + dr.getMsgid());
					
					OshotMMS mms = new OshotMMS();
					
					mms.setMsggroupid(dr.getRemark4());
					mms.setSender(dr.getSmssender());
					mms.setReceiver(dr.getPhn());
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
				}
				
				if(oshotMMSs.size() > 0) {
					oshotMMSService.save(oshotMMSs);
				}
				
			}catch(Exception ex) {
				log.error("MMS 자료 생성중 오류 발생 : " + ex.toString());
			}
		}
		isRunning = false;
	}
}
