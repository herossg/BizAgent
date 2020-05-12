package com.dhn.agent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhn.agent.model.OshotSMS;
import com.dhn.agent.repository.OshotSMSRepo;

@Service
public class OshotSMSServiceImpl implements OshotSMSService{

	@Autowired
	OshotSMSRepo oshotSMSRepo;
	
	@Override
	public List<OshotSMS> findAll() {
		List<OshotSMS> oshotSMSs = new ArrayList<>();
		oshotSMSRepo.findAll().forEach(e -> oshotSMSs.add(e));
		return oshotSMSs;
	}

	@Override
	public String save(List<OshotSMS> oshotSMSs) {
		oshotSMSRepo.saveAll(oshotSMSs);
		return null;
	}

}
