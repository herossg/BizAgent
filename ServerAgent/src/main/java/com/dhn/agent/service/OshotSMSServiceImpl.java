package com.dhn.agent.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.agent.controller.ResultSMSLog;
import com.dhn.agent.model.OshotSMS;
import com.dhn.agent.repository.DatabaseRepo;
import com.dhn.agent.repository.OshotSMSRepo;

@Service
public class OshotSMSServiceImpl implements OshotSMSService{

	@Autowired
	OshotSMSRepo oshotSMSRepo;
	
	@Autowired
	EntityManager em;
	
    @Autowired
    private DatabaseRepo dao;
	
	private static final Logger log = LoggerFactory.getLogger(ResultSMSLog.class);
	
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

	@Override
	public List<OshotSMS> selectLog(String table) {
		List<OshotSMS> oshotSMSLogs = new ArrayList<>();
		
		oshotSMSLogs = em.createNativeQuery("select * from " + table + " where proc_flag = 'Y' limit 1000", OshotSMS.class).getResultList();

		return oshotSMSLogs;
	}

	@Override
	@Transactional
	public void updateByMsgids(String table, List<Integer> msgids) {
		
		String ids = "";
		for(int i=0; i<msgids.size(); i++ ) {
			int e = msgids.get(i);
			if(ids.length() <1 ) {
				ids = ids + e;
			} else {
				ids = ids + "," + e;
			}
		}

		dao.clear("update " + table + " set proc_flag = 'N' where msgid in ( " + ids + " ) ");

	}

}
