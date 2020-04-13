package com.dhn.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.agent.model.DhnResult;

@Repository
public interface DhnResultRepo extends JpaRepository<DhnResult, String> {
	final static String SELECT_MSGID = "select * from dhn_result where msgid = :msgid and result='Y'";
	final static String SELECT_USERID_SG = "select * from dhn_result where userid = :userid and send_group = :send_group";
	final static String SELECT_USERID = "select * from dhn_result where userid = :userid and sync='N' and result = 'Y' limit 1000";
	final static String RESULT_UPDATE = "update dhn_result set code = :code , message = :message, result='Y', res_dt = now() where msgid = :msgid";
	final static String RESULT_SYNC_UPDATE = "update dhn_result set sync='Y' where userid = :userid and send_group = :send_group";
	final static String RESULT_GET_UPDATE = "update dhn_result set send_group = :send_group where userid = :userid and result='Y' and sync='N' and send_group is null limit 1000";
	
	@Query(value = SELECT_MSGID, nativeQuery = true)
	public DhnResult selectByMsgidQuery(@Param("msgid") String msgid);

	@Query(value = SELECT_USERID, nativeQuery = true)
	public List<DhnResult> selectByUserid(@Param("userid") String userid);

	@Query(value = SELECT_USERID_SG, nativeQuery = true)
	public List<DhnResult> selectByUseridSendgroupQuery(@Param("userid") String userid, @Param("send_group") String send_group);

	@Transactional
	@Modifying
	@Query(value = RESULT_UPDATE, nativeQuery = true)
	public void updateByMsgidQuery(@Param("msgid") String msgid, @Param("code") String code, @Param("message") String message);
	
	@Transactional
	@Modifying
	@Query(value = RESULT_SYNC_UPDATE, nativeQuery = true)
	public void updateByMsgidSyncQuery(@Param("userid") String userid, @Param("send_group") String send_group);

	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Modifying
	@Query(value = RESULT_GET_UPDATE, nativeQuery = true)
	public void updateSendGroupByUseridSyncQuery(@Param("send_group") String send_group,@Param("userid") String userid);

}
