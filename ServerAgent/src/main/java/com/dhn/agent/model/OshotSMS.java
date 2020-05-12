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
@Table(name="OShotSMS")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OshotSMS  implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer MsgID;
	
	@Column(name = "Sender", nullable = false, length = 15)
	private String sender;
	
	@Column(name = "Receiver", nullable = false, length = 15)
	private String receiver;
	
	@Column(name = "Msg", nullable = false, length = 120)
	private String msg;

	@Column(name = "URL", nullable = true, length = 80)
	private String url;

	@Column(name = "ReserveDT", nullable = true, length = 20)
	private LocalDateTime reservedt;
	
	@Column(name = "TimeoutDT", nullable = true, length = 20)
	private LocalDateTime timeoutdt;
	
	@Column(name = "SendDT", nullable = true, length = 20)
	private LocalDateTime senddt;
	
	@Column(name = "SendResult", nullable = false, length = 20)
	private int sendresult;
	
	@Column(name = "Telecom", nullable = true, length = 7)
	private String telecom;
	
	@Column(name = "InsertDT", nullable = false, length = 20)
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
CREATE TABLE `OShotSMS` (
`MsgID` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
`Sender` VARCHAR(15) NOT NULL COLLATE 'latin1_swedish_ci',
`Receiver` VARCHAR(15) NOT NULL COLLATE 'latin1_swedish_ci',
`Msg` VARCHAR(120) NOT NULL COLLATE 'euckr_korean_ci',
`URL` VARCHAR(80) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
`ReserveDT` DATETIME NULL DEFAULT NULL,
`TimeoutDT` DATETIME NULL DEFAULT NULL,
`SendDT` DATETIME NULL DEFAULT NULL,
`SendResult` SMALLINT(6) NOT NULL DEFAULT '0',
`Telecom` VARCHAR(7) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
`InsertDT` TIMESTAMP NOT NULL DEFAULT current_timestamp(),
`mst_id` INT(11) NULL DEFAULT NULL,
`proc_flag` CHAR(1) NOT NULL DEFAULT 'Y' COLLATE 'latin1_swedish_ci',
`cb_msg_id` VARCHAR(20) NULL DEFAULT NULL COLLATE 'latin1_swedish_ci',
PRIMARY KEY (`MsgID`) USING BTREE,
INDEX `IDX_OShotSMS` (`SendResult`, `ReserveDT`) USING BTREE,
INDEX `SendResult_mst_id_proc_flag` (`SendResult`, `mst_id`, `proc_flag`) USING BTREE
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
*/