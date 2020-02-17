package com.dhn.agent.service;

import java.util.List;

import com.dhn.agent.model.DhnRequest;

public interface DhnRequestService {
	List<DhnRequest> findAll();
	
	DhnRequest findById(Integer id);
	
	void deleteById(Integer id);
	
	String save(DhnRequest dhnRequest);
	
	void deleteByMsgidQeury(String msgid);

	
}
