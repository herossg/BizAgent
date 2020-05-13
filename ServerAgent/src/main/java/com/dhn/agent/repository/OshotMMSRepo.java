package com.dhn.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.agent.model.OshotMMS;

@Repository
public interface OshotMMSRepo extends JpaRepository<OshotMMS, Integer> {

	@Query(value = "select * from :table where proc_flag = 'Y' limit 1000" , nativeQuery = true)
	public List<OshotMMS> selectLog(@Param("table") String table);
	
	@Modifying
	@Transactional
	@Query(value = "update :table set proc_flag = 'N' where msgid in :msgids ", nativeQuery = true)
	public void updateByMsgids(@Param("table") String table, @Param("msgids") List<Integer> msgids);
	
}
