package com.dhn.client.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TBL_REQUEST_RESULT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DhnResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MSGID", nullable = false, length = 20)
	private String MSGID; //` varchar(20) not null,

	@Column(name = "AD_FLAG", length = 1)
	private String AD_FLAG; //` varchar(1) null default null,

	@Column(name = "BUTTON1", columnDefinition = "LONGTEXT")
	private String BUTTON1; //` longtext null default null,

	@Column(name = "BUTTON2", columnDefinition = "LONGTEXT")
	private String BUTTON2; //` longtext null default null,

	@Column(name = "BUTTON3", columnDefinition = "LONGTEXT")
	private String BUTTON3; //` longtext null default null,

	@Column(name = "BUTTON4", columnDefinition = "LONGTEXT")
	private String BUTTON4; //` longtext null default null,

	@Column(name = "BUTTON5", columnDefinition = "LONGTEXT")
	private String BUTTON5; //` longtext null default null,

	@Column(name = "CODE", length = 4)
	private String CODE; //` varchar(4) null default null,

	@Column(name = "IMAGE_LINK", columnDefinition = "LONGTEXT")
	private String IMAGE_LINK; //` longtext null default null,

	@Column(name = "IMAGE_URL", columnDefinition = "LONGTEXT")
	private String IMAGE_URL; //` longtext null default null,

	@Column(name = "KIND", length = 1)
	private String KIND; //` varchar(1) null default null,

	@Column(name = "MESSAGE", columnDefinition = "LONGTEXT")
	private String MESSAGE; //` longtext null default null,

	@Column(name = "MESSAGE_TYPE", length = 2)
	private String MESSAGE_TYPE; //` varchar(2) null default null,

	@Column(name = "MSG", nullable = false, columnDefinition = "LONGTEXT")
	private String MSG; //` longtext not null,

	@Column(name = "MSG_SMS", columnDefinition = "LONGTEXT")
	private String MSG_SMS; //` longtext null default null,

	@Column(name = "ONLY_SMS", length = 1)
	private String ONLY_SMS; //` varchar(1) null default null,

	@Column(name = "P_COM", length = 2)
	private String P_COM; //` varchar(2) null default null,

	@Column(name = "P_INVOICE", length = 100)
	private String P_INVOICE; //` varchar(100) null default null,

	@Column(name = "PHN", nullable = false, length = 15)
	private String PHN; //` varchar(15) not null,

	@Column(name = "PROFILE", length = 50)
	private String PROFILE; //` varchar(50) null default null,

	@Column(name = "REG_DT", nullable = false, length = 20)
	private String REG_DT; //` datetime not null,

	@Column(name = "REMARK1", length = 50)
	private String REMARK1; //` varchar(50) null default null,

	@Column(name = "REMARK2", length = 50)
	private String REMARK2; //` varchar(50) null default null,

	@Column(name = "REMARK3", length = 50)
	private String REMARK3; //` varchar(50) null default null,

	@Column(name = "REMARK4", length = 50)
	private String REMARK4; //` varchar(50) null default null,

	@Column(name = "REMARK5", length = 50)
	private String REMARK5; //` varchar(50) null default null,

	@Column(name = "RES_DT", length = 20)
	private String RES_DT; //` datetime null default null,

	@Column(name = "RESERVE_DT", nullable = false, length = 14)
	private String RESERVE_DT; //` varchar(14) not null,

	@Column(name = "RESULT", length = 1)
	private String RESULT; //` varchar(1) null default null,

	@Column(name = "S_CODE", length = 2)
	private String S_CODE; //` varchar(2) null default null,

	@Column(name = "SMS_KIND", length = 1)
	private String SMS_KIND; //` varchar(1) null default null,

	@Column(name = "SMS_LMS_TIT", length = 120)
	private String SMS_LMS_TIT; //` varchar(120) null default null,

	@Column(name = "SMS_SENDER", length = 15)
	private String SMS_SENDER; //` varchar(15) null default null,

	@Column(name = "SYNC", nullable = false, length = 1)
	private String SYNC; //` varchar(1) not null,

	@Column(name = "TMPL_ID", length = 30)
	private String TMPL_ID; //` varchar(30) null default null,

	@Column(name = "WIDE", length = 1)
	private String WIDE; //` char(1) null default 'n' 

	public String getMsgid() {
		return MSGID;
	}

	public void setMsgid(String msgid) {
		this.MSGID = msgid;
	}

	public String getAd_flag() {
		return AD_FLAG;
	}

	public void setAd_flag(String ad_flag) {
		this.AD_FLAG = ad_flag;
	}

	public String getButton1() {
		return BUTTON1;
	}

	public void setButton1(String button1) {
		this.BUTTON1 = button1;
	}

	public String getButton2() {
		return BUTTON2;
	}

	public void setButton2(String button2) {
		this.BUTTON2 = button2;
	}

	public String getButton3() {
		return BUTTON3;
	}

	public void setButton3(String button3) {
		this.BUTTON3 = button3;
	}

	public String getButton4() {
		return BUTTON4;
	}

	public void setButton4(String button4) {
		this.BUTTON4 = button4;
	}

	public String getButton5() {
		return BUTTON5;
	}

	public void setButton5(String button5) {
		this.BUTTON5 = button5;
	}

	public String getCode() {
		return CODE;
	}

	public void setCode(String code) {
		this.CODE = code;
	}

	public String getImage_link() {
		return IMAGE_LINK;
	}

	public void setImage_link(String image_link) {
		this.IMAGE_LINK = image_link;
	}

	public String getImage_url() {
		return IMAGE_URL;
	}

	public void setImage_url(String image_url) {
		this.IMAGE_URL = image_url;
	}

	public String getKind() {
		return KIND;
	}

	public void setKind(String kind) {
		this.KIND = kind;
	}

	public String getMessage() {
		return MESSAGE;
	}

	public void setMessage(String message) {
		this.MESSAGE = message;
	}

	public String getMessage_type() {
		return MESSAGE_TYPE;
	}

	public void setMessage_type(String message_type) {
		this.MESSAGE_TYPE = message_type;
	}

	public String getMsg() {
		return MSG;
	}

	public void setMsg(String msg) {
		this.MSG = msg;
	}

	public String getMsg_sms() {
		return MSG_SMS;
	}

	public void setMsg_sms(String msg_sms) {
		this.MSG_SMS = msg_sms;
	}

	public String getOnly_sms() {
		return ONLY_SMS;
	}

	public void setOnly_sms(String only_sms) {
		this.ONLY_SMS = only_sms;
	}

	public String getP_com() {
		return P_COM;
	}

	public void setP_com(String p_com) {
		this.P_COM = p_com;
	}

	public String getP_invoice() {
		return P_INVOICE;
	}

	public void setP_invoice(String p_invoice) {
		this.P_INVOICE = p_invoice;
	}

	public String getPhn() {
		return PHN;
	}

	public void setPhn(String phn) {
		this.PHN = phn;
	}

	public String getProfile() {
		return PROFILE;
	}

	public void setProfile(String profile) {
		this.PROFILE = profile;
	}

	public String getReg_dt() {
		return REG_DT;
	}

	public void setReg_dt(String reg_dt) {
		this.REG_DT = reg_dt;
	}

	public String getRemark1() {
		return REMARK1;
	}

	public void setRemark1(String remark1) {
		this.REMARK1 = remark1;
	}

	public String getRemark2() {
		return REMARK2;
	}

	public void setRemark2(String remark2) {
		this.REMARK2 = remark2;
	}

	public String getRemark3() {
		return REMARK3;
	}

	public void setRemark3(String remark3) {
		this.REMARK3 = remark3;
	}

	public String getRemark4() {
		return REMARK4;
	}

	public void setRemark4(String remark4) {
		this.REMARK4 = remark4;
	}

	public String getRemark5() {
		return REMARK5;
	}

	public void setRemark5(String remark5) {
		this.REMARK5 = remark5;
	}

	public String getRes_dt() {
		return RES_DT;
	}

	public void setRes_dt(String res_dt) {
		this.RES_DT = res_dt;
	}

	public String getReserve_dt() {
		return RESERVE_DT;
	}

	public void setReserve_dt(String reserve_dt) {
		this.RESERVE_DT = reserve_dt;
	}

	public String getResult() {
		return RESULT;
	}

	public void setResult(String result) {
		this.RESULT = result;
	}

	public String getS_code() {
		return S_CODE;
	}

	public void setS_code(String s_code) {
		this.S_CODE = s_code;
	}

	public String getSms_kind() {
		return SMS_KIND;
	}

	public void setSms_kind(String sms_kind) {
		this.SMS_KIND = sms_kind;
	}

	public String getSms_lms_tit() {
		return SMS_LMS_TIT;
	}

	public void setSms_lms_tit(String sms_lms_tit) {
		this.SMS_LMS_TIT = sms_lms_tit;
	}

	public String getSms_sender() {
		return SMS_SENDER;
	}

	public void setSms_sender(String sms_sender) {
		this.SMS_SENDER = sms_sender;
	}

	public String getSync() {
		return SYNC;
	}

	public void setSync(String sync) {
		this.SYNC = sync;
	}

	public String getTmpl_id() {
		return TMPL_ID;
	}

	public void setTmpl_id(String tmpl_id) {
		this.TMPL_ID = tmpl_id;
	}

	public String getWide() {
		return WIDE;
	}

	public void setWide(String wide) {
		this.WIDE = wide;
	}


}
