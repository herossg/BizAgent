package com.dhn.agent.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.dhn.agent.model.OshotMMS;

public interface OshotMMSService {
	List<OshotMMS> findAll();
	
	String save(List<OshotMMS> oshotMMS);
	
	public List<OshotMMS> selectLog(String table);
	
	public void updateByMsgids(String table, List<Integer> msgids);
}
