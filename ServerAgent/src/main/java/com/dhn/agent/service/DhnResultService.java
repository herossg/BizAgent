package com.dhn.agent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhn.agent.model.DhnResult;


@Service
public interface DhnResultService {
	DhnResult save(DhnResult dhnResult);
	
	DhnResult selectByMsgidQuery(String msgid);
	
	void updateByMsgidQuery(String msgid, String code, String message, String result);

	List<DhnResult> selectByUseridSendgroupQuery(String userid, String send_group);
	
	void SaveAll(List<DhnResult> dhnResults);
	
	void updateByMsgidSyncQuery(String userid, String send_group);
	
	void updateSendGroupByUseridSyncQuery(String send_group);

	List<DhnResult> selectByUserid(String userid);
	
	List<DhnResult> selectBySendgroupQuery(String sendgroup);

}
