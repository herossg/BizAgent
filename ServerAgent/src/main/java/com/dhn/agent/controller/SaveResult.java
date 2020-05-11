package com.dhn.agent.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhn.agent.model.DhnResult;
import com.dhn.agent.service.DhnResultService;

@Component
public class SaveResult {

	private static DhnResultService dhnResService;
	
	@Autowired
	public SaveResult(DhnResultService dhnResService) {
		SaveResult.dhnResService = dhnResService;
	}
	
	public static void Save(DhnResult dhnResult) {
		dhnResService.save(dhnResult);
	}
	
	public static void UpdateResult(String msgid, String code, String message, String result) {
		dhnResService.updateByMsgidQuery(msgid, code, message, result);
	}
}
