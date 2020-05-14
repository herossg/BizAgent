package com.dhn.client.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhn.client.model.DhnResult;

@Service
public interface DhnResultService {
	DhnResult save(DhnResult dhnResult);
	
	List<DhnResult> findByRESULT(String res);
	
	String SaveAll(List<DhnResult> dhnResutls);
	
	public void updateByMsgidQuery(String msgid, String code, String message);

	public void updateByMsgidQuery_oracle(String msgid, String code, String message);
}
