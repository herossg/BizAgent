package com.dhn.agent.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhn.agent.model.OshotMMS;
import com.dhn.agent.service.DhnResultService;
import com.dhn.agent.service.OshotMMSService;

@Component
public class ResultMMSLog {
	
	@Autowired
	private DhnResultService dhnResultService;
	
	@Autowired
	private OshotMMSService oshotMMSService;

	public static boolean isRunning = false;
	private static final Logger log = LoggerFactory.getLogger(ResultMMSLog.class);
	 
	public void run() {
		if(!isRunning) {
			isRunning = true;
			
			Date month = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMM");
			String monthStr = transFormat.format(month);
			String table = "OShotMMS_" + monthStr;
			try {
				
				List<OshotMMS> mmslog = oshotMMSService.selectLog(table);

				List<Integer> msgids = new ArrayList<>();
				
				for(int i=0; i<mmslog.size(); i++) {
					OshotMMS mms = (OshotMMS)mmslog.get(i);

					if(mms.getSendresult() == 6) {
						SaveResult.UpdateResult(mms.getCb_msg_Id(), "0000", "", "Y");
					} else {
						String code = String.format("8%03d", mms.getSendresult());
						SaveResult.UpdateResult(mms.getCb_msg_Id(), code, "SMS Error", "Y");
					}
					
					msgids.add(mms.getMsgID());
				}
				
				if(msgids.size() > 0)
					oshotMMSService.updateByMsgids(table, msgids);

			}catch(Exception ex) {
				log.error("MMS 자료 생성중 오류 발생 2 : " + ex.toString());
			}
		}
		isRunning = false;
	}
  
}
