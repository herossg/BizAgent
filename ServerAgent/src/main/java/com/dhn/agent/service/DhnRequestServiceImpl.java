package com.dhn.agent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhn.agent.exception.ResourceNotFoundException;
import com.dhn.agent.model.DhnRequest;
import com.dhn.agent.repository.DhnRepository;

@Service
public class DhnRequestServiceImpl implements DhnRequestService {

	@Autowired
	private DhnRepository dhnRequestRepo;
	
	@Override
	public List<DhnRequest> findAll() {
		List<DhnRequest> dhnRequests = new ArrayList<>();
		dhnRequestRepo.findAll().forEach(e -> dhnRequests.add(e));
		return dhnRequests;
	}

	@Override
	public DhnRequest findById(Integer id) {
		DhnRequest dhnRequest = dhnRequestRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("DhnReqeust", "MSG ID", id));
		return dhnRequest;
	}

	@Override
	public void deleteById(Integer id) {
		dhnRequestRepo.deleteById(id);
	}

	@Override
	public String save(DhnRequest dhnRequest) {
		String result = "{'code':':code', 'message':':message'}";
		try {
			dhnRequestRepo.save(dhnRequest);
			result = "{'code':'0000', 'message':'서버전송 성공'}";
		} catch(Exception ex) {
			result = "{'code':'9999', 'message':'서버 저장 중 오류 발생'}";
		}
		return result;
	}

	@Override
	public void deleteByMsgidQeury(String msgid) {
		dhnRequestRepo.deleteByMsgidQuery(msgid);
	}

	
}
