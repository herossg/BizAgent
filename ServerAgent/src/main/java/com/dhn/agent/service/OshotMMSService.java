package com.dhn.agent.service;

import java.util.List;

import com.dhn.agent.model.OshotMMS;

public interface OshotMMSService {
	List<OshotMMS> findAll();
	
	String save(List<OshotMMS> oshotMMS);
}
