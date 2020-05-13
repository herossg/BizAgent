package com.dhn.agent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhn.agent.model.OshotMMS;
import com.dhn.agent.repository.OshotMMSRepo;

@Service
public class OshotMMSServiceImpl implements OshotMMSService{

	@Autowired
	OshotMMSRepo oshotMMSRepo;
	
	@Override
	public List<OshotMMS> findAll() {
		List<OshotMMS> oshotMMSs = new ArrayList<>();
		oshotMMSRepo.findAll().forEach(e -> oshotMMSs.add(e));
		return oshotMMSs;
	}

	@Override
	public String save(List<OshotMMS> oshotMMSs) {
		oshotMMSRepo.saveAll(oshotMMSs);
		return null;
	}

	@Override
	public List<OshotMMS> selectLog(String table) {
		List<OshotMMS> oshotMMSLogs = new ArrayList<>();
		oshotMMSRepo.selectLog(table).forEach(e -> oshotMMSLogs.add(e));
		return oshotMMSLogs;
	}

	@Override
	public void updateByMsgids(String table, List<Integer> msgids) {
		oshotMMSRepo.updateByMsgids(table, msgids);
	}

}
