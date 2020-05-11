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
	
	@Column(name = "MsgGroupID", nullable = false, length = 20)
	private String msggroupid;
	
	@Column(name = "Sender", nullable = false, length = 15)
	private String sender;
	
	@Column(name = "Receiver", nullable = false, length = 15)
	private String receiver;
	
	@Column(name = "Subject", nullable = true, length = 120)
	private String subject;
	
	@Column(name = "Msg", nullable = false, length = 4000)
	private String msg;
	
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
	
	@Column(name = "File_Path1", nullable = true, length = 128)
	private String file_path1;

	@Column(name = "File_Path2", nullable = true, length = 128)
	private String file_path2;

	@Column(name = "File_Path3", nullable = true, length = 128)
	private String file_path3;
	
	@Column(name = "File_Path4", nullable = true, length = 128)
	private String file_path4;

	@Column(name = "InsertDT", nullable = false, length = 20)
	private LocalDateTime insertdt;

	@Column(name = "mst_id", nullable = true)
	private int mst_id;

	@Column(name = "proc_flag", nullable = false, length = 1)
	private String proc_flag;

	@Column(name = "cb_msg_Id", nullable = false, length = 20)
	private String cb_msg_Id;

	
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