package com.dhn.agent.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhn.agent.model.DhnResult;
import com.dhn.agent.repository.DhnResultRepo;

@Component
public class DhnResultServiceImpl implements DhnResultService{
	
	@Autowired
	DhnResultRepo dhnResultRepo;
	
	@Override
	public DhnResult save(DhnResult dhnResult) {
		dhnResultRepo.save(dhnResult);
		return dhnResult;
	}

	@Override
	public DhnResult selectByMsgidQuery(String msgid) {
		DhnResult result = dhnResultRepo.selectByMsgidQuery(msgid);
		return result;
	}

	@Override
	public void updateByMsgidQuery(String msgid, String code, String message) {
		dhnResultRepo.updateByMsgidQuery(msgid, code, message);
		
	}
}
