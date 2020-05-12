package com.dhn.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhn.agent.model.OshotSMS_Log;

@Repository
public interface OshotSMSLogRepo extends JpaRepository<OshotSMS_Log, Integer> {

}
