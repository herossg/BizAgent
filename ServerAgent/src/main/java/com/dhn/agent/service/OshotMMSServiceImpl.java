package com.dhn.agent.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.agent.model.OshotMMS;
import com.dhn.agent.model.OshotSMS;
import com.dhn.agent.repository.DatabaseRepo;
import com.dhn.agent.repository.OshotMMSRepo;

@Service
public class OshotMMSServiceImpl implements OshotMMSService{

	@Autowired
	OshotMMSRepo oshotMMSRepo;
	
	@Autowired
	EntityManager em;
	
    @Autowired
    private DatabaseRepo dao;
    
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

		oshotMMSLogs = em.createNativeQuery("select * from " + table + " where proc_flag = 'Y' limit 1000", OshotMMS.class).getResultList();

		return oshotMMSLogs;
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
