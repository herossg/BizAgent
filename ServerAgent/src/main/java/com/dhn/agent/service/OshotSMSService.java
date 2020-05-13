package com.dhn.agent.service;

import java.util.List;

import com.dhn.agent.model.OshotSMS;

public interface OshotSMSService {
	List<OshotSMS> findAll();
	
	String save(List<OshotSMS> oshotSMS);
	
	public List<OshotSMS> selectLog(String table);
	
	public void updateByMsgids(String table, List<Integer> msgids);
}
