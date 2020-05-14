package com.dhn.agent.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhn.agent.model.OshotMMS;
import com.dhn.agent.repository.OshotMMSRepo;

@Service
public class OshotMMSServiceImpl implements OshotMMSService{

	@Autowired
	OshotMMSRepo oshotMMSRepo;
	
	@Autowired
	EntityManager em;
	
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
		String query = "select * from " + table + " where proc_flag = 'Y' limit 1000";
		oshotMMSLogs = em.createNativeQuery(query, OshotMMS.class).getResultList();
		//oshotMMSRepo.selectLog(table).forEach(e -> oshotMMSLogs.add(e));
		return oshotMMSLogs;
	}

	@Override
	public void updateByMsgids(String table, List<Integer> msgids) {
		String ids = "";
		for(int i=0; i < msgids.size(); i++) {
			ids = ids + "" + msgids.get(i);
			if(i < msgids.size() - 1) {
				ids = ids + ",";
			}
		}
		String query = "update " + table + " set proc_flag = 'N' where msgid in (" + ids + ")";
		em.createNativeQuery(query);
		//oshotMMSRepo.updateByMsgids(table, msgids);
	}

}
