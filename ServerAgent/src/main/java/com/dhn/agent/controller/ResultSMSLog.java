package com.dhn.agent.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.exception.SQLGrammarException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhn.agent.model.OshotSMS;
import com.dhn.agent.service.DhnResultService;
import com.dhn.agent.service.OshotSMSService;

@Component
public class ResultSMSLog {
	
	@Autowired
	private DhnResultService dhnResultService;
	
	@Autowired
	private OshotSMSService oshotSMSService;

	public static boolean isRunning = false;
	private static final Logger log = LoggerFactory.getLogger(ResultSMSLog.class);
	 
	public void run() {
		if(!isRunning) {
			isRunning = true;
			
			Date month = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMM");
			String monthStr = transFormat.format(month);
			String table = "OShotSMS_" + monthStr;
			try {
				
				List<OshotSMS> smslog = oshotSMSService.selectLog(table);

				List<Integer> msgids = new ArrayList<>();
				
				for(int i=0; i<smslog.size(); i++) {
					OshotSMS sms = (OshotSMS)smslog.get(i);

					if(sms.getSendresult() == 6) {
						SaveResult.UpdateResult(sms.getCb_msg_Id(), "0000", "", "Y");
					} else {
						String code = String.format("8%03d", sms.getSendresult());
						SaveResult.UpdateResult(sms.getCb_msg_Id(), code, "SMS Error", "Y");
					}
					msgids.add(sms.getMsgID());
				}
				
				if(msgids.size() > 0)
					oshotSMSService.updateByMsgids(table, msgids);

			} catch(SQLGrammarException ex) {
				log.error("SMS 자료 생성중 오류 발생 1 : " + ex.toString());
			}catch(Exception ex) {
				log.error("SMS 자료 생성중 오류 발생 1 : " + ex.toString());
			}
		}
		isRunning = false;
	}
  
}
