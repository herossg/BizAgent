package com.dhn.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dhn.agent.model.OshotMMS;

@Repository
public interface OshotMMSRepo extends JpaRepository<OshotMMS, Integer> {

}
