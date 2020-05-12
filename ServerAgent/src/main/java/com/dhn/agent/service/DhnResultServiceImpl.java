package com.dhn.agent.service;

import java.util.ArrayList;
import java.util.List;

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
	public void updateByMsgidQuery(String msgid, String code, String message, String result) {
		dhnResultRepo.updateByMsgidQuery(msgid, code, message, result);
		
	}

	@Override
	public List<DhnResult> selectByUseridSendgroupQuery(String userid, String send_group) {
		List<DhnResult> dhnResult = new ArrayList<>();
		dhnResultRepo.selectByUseridSendgroupQuery(userid, send_group).forEach(e -> dhnResult.add(e));
		return dhnResult;
	}

	@Override
	public void updateByMsgidSyncQuery(String userid, String send_group) {
		dhnResultRepo.updateByMsgidSyncQuery(userid, send_group);
	}

	@Override
	public void updateSendGroupByUseridSyncQuery(String send_group) {
		dhnResultRepo.updateSendGroupByUseridSyncQuery(send_group);
		dhnResultRepo.flush();
	}

	@Override
	public void SaveAll(List<DhnResult> dhnResults) {
		dhnResultRepo.saveAll(dhnResults);
	}

	@Override
	public List<DhnResult> selectByUserid(String userid) {
		List<DhnResult> dhnResults = new ArrayList<>();
		dhnResultRepo.selectByUserid(userid).forEach(e -> dhnResults.add(e));
		return dhnResults;
	}
	
	@Override
	public List<DhnResult> selectBySendgroupQuery(String sendgroup) {
		List<DhnResult> dhnResults = new ArrayList<>();
		dhnResultRepo.selectBySendgroupQuery(sendgroup).forEach(e -> dhnResults.add(e));
		return dhnResults;
	}
}
