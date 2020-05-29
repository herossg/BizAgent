package com.dhn.client.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.client.model.DhnRequest;

@Repository
public interface DhnRequestRepo extends JpaRepository<DhnRequest, String>, JpaSpecificationExecutor<DhnRequest> {

	final static String DEL_MSGID = "delete from DHN_REQUEST where MSGID = :msgid";

	final static String DELIN_MSGID = "delete from DHN_REQUEST where MSGID in :msgid";

	final static String REQ_SEND = "select * from DHN_REQUEST where RESERVE_DT < date_format(NOW(), '%Y%m%d%H%i%s') limit 0, 1000";

	final static String REQ_SEND_ORACLE = "select * from DHN_REQUEST where RESERVE_DT < to_char(sysdate, 'YYYYMMDDHH24MISS') and rownum <=1000";
	
	
	@Modifying
	@Transactional
	@Query(value = DEL_MSGID, nativeQuery = true)
	public void deleteByMsgidQuery(@Param("msgid") String msgid);
	
	@Query(value = REQ_SEND, nativeQuery = true)
	public List<DhnRequest> selectByReserveQuery();

	@Query(value = REQ_SEND_ORACLE, nativeQuery = true)
	public List<DhnRequest> selectByReserveQuery_oracle();

	@Modifying
	@Transactional
	@Query(value = DELIN_MSGID, nativeQuery = true)
	public void deleteByInMsgidQuery(@Param("msgid") List<String> msgid);
	
}
