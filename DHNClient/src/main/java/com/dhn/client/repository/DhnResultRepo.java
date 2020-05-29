package com.dhn.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.client.model.DhnResult;

@Repository
public interface DhnResultRepo extends JpaRepository<DhnResult, String> {
	
	final static String RESULT_UPDATE = "update DHN_REQUEST_RESULT set CODE = :code , MESSAGE = :message, RESULT='Y', SYNC='Y' , RES_DT = now() where MSGID = :msgid";

	final static String RESULT_UPDATE_ORACLE = "update DHN_REQUEST_RESULT set CODE = :code , MESSAGE = :message, RESULT='Y', SYNC='Y' , RES_DT = sysdate where MSGID = :msgid";

	List<DhnResult> findByRESULT(String result);
	
	@Modifying
	@Transactional
	@Query(value = RESULT_UPDATE, nativeQuery = true)
	public void updateByMsgidQuery(@Param("msgid") String msgid, @Param("code") String code, @Param("message") String message);
	
	@Modifying
	@Transactional
	@Query(value = RESULT_UPDATE, nativeQuery = true)
	public void updateByMsgidQuery_oracle(@Param("msgid") String msgid, @Param("code") String code, @Param("message") String message);


}
