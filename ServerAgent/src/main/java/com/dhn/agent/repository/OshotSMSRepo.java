package com.dhn.agent.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.agent.model.OshotSMS;

@Repository
public interface OshotSMSRepo extends JpaRepository<OshotSMS, Integer> {
	
	@Query(value = "select * from :table where proc_flag = 'Y' limit 1000" , nativeQuery = true)
	public List<OshotSMS> selectLog(@Param("table") String table);
	
	@Modifying
	@Transactional
	@Query(value = "update :table set proc_flag = 'N' where msgid in :msgids ", nativeQuery = true)
	public void updateByMsgids(@Param("table") String table, @Param("msgids") List<Integer> msgids);
	

}
