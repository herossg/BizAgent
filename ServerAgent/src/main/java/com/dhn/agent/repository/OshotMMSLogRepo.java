package com.dhn.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhn.agent.model.OshotMMS_Log;

@Repository
public interface OshotMMSLogRepo extends JpaRepository<OshotMMS_Log, Integer> {

}
