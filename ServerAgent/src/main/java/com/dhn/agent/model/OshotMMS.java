package com.dhn.agent.model;
import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="OShotMMS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OshotMMS implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer MsgID;
	
	@Column(name = "msggroupid", nullable = false, length = 20)
	private String msggroupid;
	
	@Column(name = "sender", nullable = false, length = 15)
	private String sender;
	
	@Column(name = "receiver", nullable = false, length = 15)
	private String receiver;
	
	@Column(name = "subject", nullable = true, length = 120)
	private String subject;
	
	@Column(name = "msg", nullable = false, length = 4000)
	private String msg;
	
	@Column(name = "reservedt", nullable = true, length = 20)
	private LocalDateTime reservedt;
	
	@Column(name = "timeoutdt", nullable = true, length = 20)
	private LocalDateTime timeoutdt;
	
	@Column(name = "senddt", nullable = true, length = 20)
	private LocalDateTime senddt;
	
	@Column(name = "sendresult", nullable = false, length = 20)
	private int sendresult;
	
	@Column(name = "telecom", nullable = true, length = 7)
	private String telecom;
	
	@Column(name = "file_path1", nullable = true, length = 128)
	private String file_path1;

	@Column(name = "file_path2", nullable = true, length = 128)
	private String file_path2;

	@Column(name = "file_path3", nullable = true, length = 128)
	private String file_path3;
	
	@Column(name = "file_path4", nullable = true, length = 128)
	private String file_path4;

	@Column(name = "insertdt", nullable = false, length = 20)
	private LocalDateTime insertdt;

	@Column(name = "mst_id", nullable = true)
	private int mst_id;

	@Column(name = "proc_flag", nullable = false, length = 1)
	private String proc_flag;

	@Column(name = "cb_msg_Id", nullable = false, length = 20)
	private String cb_msg_Id;

	public Integer getMsgID() {
		return MsgID;
	}

	public void setMsgID(Integer msgID) {
		MsgID = msgID;
	}

	public String getMsggroupid() {
		return msggroupid;
	}

	public void setMsggroupid(String msggroupid) {
		this.msggroupid = msggroupid;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public LocalDateTime getReservedt() {
		return reservedt;
	}

	public void setReservedt(LocalDateTime reservedt) {
		this.reservedt = reservedt;
	}

	public LocalDateTime getTimeoutdt() {
		return timeoutdt;
	}

	public void setTimeoutdt(LocalDateTime timeoutdt) {
		this.timeoutdt = timeoutdt;
	}

	public LocalDateTime getSenddt() {
		return senddt;
	}

	public void setSenddt(LocalDateTime senddt) {
		this.senddt = senddt;
	}

	public int getSendresult() {
		return sendresult;
	}

	public void setSendresult(int sendresult) {
		this.sendresult = sendresult;
	}

	public String getTelecom() {
		return telecom;
	}

	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}

	public String getFile_path1() {
		return file_path1;
	}

	public void setFile_path1(String file_path1) {
		this.file_path1 = file_path1;
	}

	public String getFile_path2() {
		return file_path2;
	}

	public void setFile_path2(String file_path2) {
		this.file_path2 = file_path2;
	}

	public String getFile_path3() {
		return file_path3;
	}

	public void setFile_path3(String file_path3) {
		this.file_path3 = file_path3;
	}

	public String getFile_path4() {
		return file_path4;
	}

	public void setFile_path4(String file_path4) {
		this.file_path4 = file_path4;
	}

	public LocalDateTime getInsertdt() {
		return insertdt;
	}

	public void setInsertdt(LocalDateTime insertdt) {
		this.insertdt = insertdt;
	}

	public int getMst_id() {
		return mst_id;
	}

	public void setMst_id(int mst_id) {
		this.mst_id = mst_id;
	}

	public String getProc_flag() {
		return proc_flag;
	}

	public void setProc_flag(String proc_flag) {
		this.proc_flag = proc_flag;
	}

	public String getCb_msg_Id() {
		return cb_msg_Id;
	}

	public void setCb_msg_Id(String cb_msg_Id) {
		this.cb_msg_Id = cb_msg_Id;
	}

	
}
/*
CREATE TABLE `OShotMMS` (
		`MsgID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
		`MsgGroupID` VARCHAR(20) NOT NULL COLLATE 'latin1_swedish_ci',
		`Sender` VARCHAR(15) NOT NULL COLLATE 'latin1_swedish_ci',
		`Receiver` VARCHAR(15) NOT NULL COLLATE 'latin1_swedish_ci',
		`Subject` VARCHAR(120) NULL DEFAULT NULL COLLATE 'euckr_korean_ci',
		`Msg` VARCHAR(4000) NOT NULL COLLATE 'euckr_korean_ci',
		`ReserveDT` DATETIME NULL DEFAULT NULL,
		`TimeoutDT` DATETIME NULL DEFAULT NULL,
		`SendDT` DATETIME NULL DEFAULT NULL,
		`SendResult` SMALLINT(6) NOT NULL DEFAULT '0',
		`Telecom` VARCHAR(7) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
		`File_Path1` VARCHAR(128) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
		`File_Path2` VARCHAR(128) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
		`File_Path3` VARCHAR(128) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
		`File_Path4` VARCHAR(128) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
		`InsertDT` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
		`mst_id` INT(11) NULL DEFAULT NULL,
		`proc_flag` CHAR(1) NOT NULL DEFAULT 'Y' COLLATE 'latin1_swedish_ci',
		`cb_msg_Id` VARCHAR(20) NOT NULL COLLATE 'latin1_swedish_ci',
		PRIMARY KEY (`MsgID`) USING BTREE,
		INDEX `IDX_OShotMMS` (`SendResult`, `ReserveDT`, `MsgGroupID`) USING BTREE,
		INDEX `SendResult_mst_id_proc_flag` (`SendResult`, `mst_id`, `proc_flag`) USING BTREE
	)
	COLLATE='latin1_swedish_ci'
	ENGINE=InnoDB
	AUTO_INCREMENT=1
	;
*/