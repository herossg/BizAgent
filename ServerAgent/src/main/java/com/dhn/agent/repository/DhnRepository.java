package com.dhn.agent.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhn.agent.model.DhnRequest;

@Repository
public interface DhnRepository extends JpaRepository<DhnRequest, Integer> {
	final static String DEL_MSGID = "delete from dhn_request where msgid = :msgid";
	
	final static String SEND_GROUP = "update dhn_request set send_group = :sendgroup where send_group is null limit 1000";

	final static String MSGID_SEND_GROUP = "update dhn_request set send_group = :sendgroup where msgid in :msgids";

	final static String SELECT_SEND_GROUP = "select * from dhn_request where send_group = :sendgroup";

	final static String SEL_SEND_GROUP = "select * from dhn_request where send_group is null limit 1000";
	
	@Modifying
	@Transactional
	@Query(value = DEL_MSGID, nativeQuery = true)
	public void deleteByMsgidQuery(@Param("msgid") String msgid);
	
	@Modifying
	@Transactional
	@Query(value = SEND_GROUP, nativeQuery = true)
	public void updateBySendgroupQuery(@Param("sendgroup") String sendgroup);

	@Query(value = SELECT_SEND_GROUP, nativeQuery = true)
	public List<DhnRequest> findBySendgroupQuery(@Param("sendgroup") String sendgroup);

	@Query(value = SEL_SEND_GROUP, nativeQuery = true)
	public List<DhnRequest> findByNullSendgroupQuery();

	@Modifying
	@Transactional
	@Query(value = MSGID_SEND_GROUP, nativeQuery = true)
	public void updateByMsgidsSendgroupQuery(@Param("sendgroup") String sendgroup, @Param("msgids") List<String> msgids);
	
}
