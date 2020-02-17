package com.dhn.agent.service;

import org.springframework.stereotype.Service;

import com.dhn.agent.model.DhnResult;


@Service
public interface DhnResultService {
	DhnResult save(DhnResult dhnResult);
	
	DhnResult selectByMsgidQuery(String msgid);
	
	void updateByMsgidQuery(String msgid, String code, String message);
}
