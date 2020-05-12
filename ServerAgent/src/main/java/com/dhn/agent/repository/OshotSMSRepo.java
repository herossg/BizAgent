package com.dhn.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhn.agent.model.OshotSMS;

@Repository
public interface OshotSMSRepo extends JpaRepository<OshotSMS, Integer> {

}
