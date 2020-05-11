package com.dhn.agent.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dhn.agent.model.DhnUser;
import com.dhn.agent.repository.DhnUserRepo;

@Component
public class DhnUserServiceImpl implements DhnUserService {

	@Autowired
	DhnUserRepo dhnUserRepo;
	
	@Override
	public DhnUser findByUserid(String userid) {
		DhnUser dhnUser = dhnUserRepo.findByUserid(userid);
		return dhnUser;
	}

	@Override
	public List<DhnUser> findAll() {
		List<DhnUser> dhnUsers = new ArrayList<DhnUser>();
		dhnUserRepo.findAll().forEach(e -> dhnUsers.add(e));
		return dhnUsers;
	}

}
