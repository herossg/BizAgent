package com.dhn.agent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.agent.model.DhnResult;

@Repository
public interface DhnResultRepo extends JpaRepository<DhnResult, String> {
	final static String SELECT_MSGID = "select * from dhn_result1 where msgid = :msgid and result='Y'";
	final static String RESULT_UPDATE = "update dhn_result1 set code = :code , message = :message, result='Y', sync='Y' where msgid = :msgid";
	
	@Query(value = SELECT_MSGID, nativeQuery = true)
	public DhnResult selectByMsgidQuery(@Param("msgid") String msgid);
	
	@Modifying
	@Transactional
	@Query(value = RESULT_UPDATE, nativeQuery = true)
	public void updateByMsgidQuery(@Param("msgid") String msgid, @Param("code") String code, @Param("message") String message);
}
