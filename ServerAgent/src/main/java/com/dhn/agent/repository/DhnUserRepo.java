package com.dhn.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhn.agent.model.DhnUser;

@Repository
public interface DhnUserRepo extends JpaRepository<DhnUser, String>{
	DhnUser findByUserid(String userid);
}
