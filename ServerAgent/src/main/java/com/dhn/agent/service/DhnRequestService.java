package com.dhn.agent.service;

import java.util.List;

import com.dhn.agent.model.DhnRequest;

public interface DhnRequestService {
	List<DhnRequest> findAll();
	
	DhnRequest findById(Integer id);
	
	void deleteById(Integer id);
	
	String save(List<DhnRequest> dhnRequest);
	
	void deleteAll(List<DhnRequest> dhnRequest);
	
	void deleteByMsgidQeury(String msgid);

	void updateBySendgroupQuery(String sendgroup);
	
	void updateByMsgidsSendgroupQuery(String sendgroup, List<String> msgids);
	
	List<DhnRequest> findBySendgroupQuery(String sendgroup);
	
	List<String> findByNullSendgroupQuery();
}
