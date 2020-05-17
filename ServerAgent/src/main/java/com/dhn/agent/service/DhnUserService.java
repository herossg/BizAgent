package com.dhn.agent.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dhn.agent.model.DhnUser;

@Service
public interface DhnUserService {
	DhnUser findByUserid(String userid);
	
	List<DhnUser> findAll();
	
	void save(DhnUser du);
}
