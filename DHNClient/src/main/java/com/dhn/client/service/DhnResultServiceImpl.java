package com.dhn.client.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhn.client.controller.GetResult;
import com.dhn.client.model.DhnResult;
import com.dhn.client.repository.DhnResultRepo;

@Component
public class DhnResultServiceImpl implements DhnResultService{
	
	@Autowired
	DhnResultRepo dhnResultRepo;
	
	private static final Logger log = LoggerFactory.getLogger(GetResult.class);
	
	@Override
	public DhnResult save(DhnResult dhnResult) {
		dhnResultRepo.save(dhnResult);
		return dhnResult;
	}

	@Override
	public List<DhnResult> findByRESULT(String res) {
		List<DhnResult> dhnResults = new ArrayList<DhnResult>();
		dhnResultRepo.findByRESULT(res).forEach(e -> dhnResults.add(e));
		return dhnResults;
	}
	
	@Override
	public void updateByMsgidQuery(String msgid, String code, String message) {
		dhnResultRepo.updateByMsgidQuery(msgid, code, message);
		
	}

	@Override
	public String SaveAll(List<DhnResult> dhnResults) {
		String res = "저장 완료 - " + dhnResults.size() + " 건 ";
		try {
			dhnResultRepo.saveAll(dhnResults);
		} catch (Exception ex) {
			log.info("SaveAll 오류 발생 !! - 단건 처리 시작");

			dhnResults.forEach(d -> {
				try {
					dhnResultRepo.save(d);
					log.info("" + d.getMSGID() + " 개별 수신 결과 저장 처리 성공 !!");
				} catch(Exception ex1) {
					log.info("개별 수신 결과 저장 오류 : " + d.getMSGID() + " / " + ex1.toString());
				}
			});
		} finally {
			
		}
		return res;
	}
	

}
