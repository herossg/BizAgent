package com.dhn.agent.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name="DHN_RESULT")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DhnResult implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "msgid", nullable = false, length = 20)
	private String msgid; //` varchar(20) not null,
	
	@Column(name = "userid", nullable = false, length = 20)
	private String userid;

	@Column(name = "ad_flag", length = 1)
	private String adflag; //` varchar(1) null default null,

	@Column(name = "button1", columnDefinition = "LONGTEXT")
	private String button1; //` longtext null default null,

	@Column(name = "button2", columnDefinition = "LONGTEXT")
	private String button2; //` longtext null default null,

	@Column(name = "button3", columnDefinition = "LONGTEXT")
	private String button3; //` longtext null default null,

	@Column(name = "button4", columnDefinition = "LONGTEXT")
	private String button4; //` longtext null default null,

	@Column(name = "button5", columnDefinition = "LONGTEXT")
	private String button5; //` longtext null default null,

	@Column(name = "code", length = 4)
	private String code; //` varchar(4) null default null,

	@Column(name = "image_link", columnDefinition = "LONGTEXT")
	private String imagelink; //` longtext null default null,

	@Column(name = "image_url", columnDefinition = "LONGTEXT")
	private String imageurl; //` longtext null default null,

	@Column(name = "kind", length = 1)
	private String kind; //` varchar(1) null default null,

	@Column(name = "message", columnDefinition = "LONGTEXT")
	private String message; //` longtext null default null,

	@Column(name = "message_type", length = 2)
	private String messagetype; //` varchar(2) null default null,

	@Column(name = "msg", nullable = false, columnDefinition = "LONGTEXT")
	private String msg; //` longtext not null,

	@Column(name = "msg_sms", columnDefinition = "LONGTEXT")
	private String msgsms; //` longtext null default null,

	@Column(name = "only_sms", length = 1)
	private String onlysms; //` varchar(1) null default null,

	@Column(name = "p_com", length = 2)
	private String pcom; //` varchar(2) null default null,

	@Column(name = "p_invoice", length = 100)
	private String pinvoice; //` varchar(100) null default null,

	@Column(name = "phn", nullable = false, length = 15)
	private String phn; //` varchar(15) not null,

	@Column(name = "profile", length = 50)
	private String profile; //` varchar(50) null default null,

	@Column(name = "reg_dt", nullable = false, length = 20)
	private String regdt; //` datetime not null,

	@Column(name = "remark1", length = 50)
	private String remark1; //` varchar(50) null default null,

	@Column(name = "remark2", length = 50)
	private String remark2; //` varchar(50) null default null,

	@Column(name = "remark3", length = 50)
	private String remark3; //` varchar(50) null default null,

	@Column(name = "remark4", length = 50)
	private String remark4; //` varchar(50) null default null,

	@Column(name = "remark5", length = 50)
	private String remark5; //` varchar(50) null default null,

	@Column(name = "res_dt", length = 20)
	private String resdt; //` datetime null default null,

	@Column(name = "reserve_dt", nullable = false, length = 14)
	private String reservedt; //` varchar(14) not null,

	@Column(name = "result", length = 1)
	private String result; //` varchar(1) null default null,

	@Column(name = "s_code", length = 2)
	private String scode; //` varchar(2) null default null,

	@Column(name = "sms_kind", length = 1)
	private String smskind; //` varchar(1) null default null,

	@Column(name = "sms_lms_tit", length = 120)
	private String smslmstit; //` varchar(120) null default null,

	@Column(name = "sms_sender", length = 15)
	private String smssender; //` varchar(15) null default null,

	@Column(name = "sync", nullable = false, length = 1)
	private String sync; //` varchar(1) not null,

	@Column(name = "tmpl_id", length = 30)
	private String tmplid; //` varchar(30) null default null,

	@Column(name = "wide", length = 1)
	private String wide; //` char(1) null default 'n' 

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getAdflag() {
		return adflag;
	}

	public void setAdflag(String adflag) {
		this.adflag = adflag;
	}

	public String getButton1() {
		return button1;
	}

	public void setButton1(String button1) {
		this.button1 = button1;
	}

	public String getButton2() {
		return button2;
	}

	public void setButton2(String button2) {
		this.button2 = button2;
	}

	public String getButton3() {
		return button3;
	}

	public void setButton3(String button3) {
		this.button3 = button3;
	}

	public String getButton4() {
		return button4;
	}

	public void setButton4(String button4) {
		this.button4 = button4;
	}

	public String getButton5() {
		return button5;
	}

	public void setButton5(String button5) {
		this.button5 = button5;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImagelink() {
		return imagelink;
	}

	public void setImagelink(String imagelink) {
		this.imagelink = imagelink;
	}

	public String getImageurl() {
		return imageurl;
	}

	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsgsms() {
		return msgsms;
	}

	public void setMsgsms(String msgsms) {
		this.msgsms = msgsms;
	}

	public String getOnlysms() {
		return onlysms;
	}

	public void setOnlysms(String onlysms) {
		this.onlysms = onlysms;
	}

	public String getPcom() {
		return pcom;
	}

	public void setPcom(String pcom) {
		this.pcom = pcom;
	}

	public String getPinvoice() {
		return pinvoice;
	}

	public void setPinvoice(String pinvoice) {
		this.pinvoice = pinvoice;
	}

	public String getPhn() {
		return phn;
	}

	public void setPhn(String phn) {
		this.phn = phn;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getRegdt() {
		return regdt;
	}

	public void setRegdt(String regdt) {
		this.regdt = regdt;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	public String getRemark4() {
		return remark4;
	}

	public void setRemark4(String remark4) {
		this.remark4 = remark4;
	}

	public String getRemark5() {
		return remark5;
	}

	public void setRemark5(String remark5) {
		this.remark5 = remark5;
	}

	public String getResdt() {
		return resdt;
	}

	public void setResdt(String resdt) {
		this.resdt = resdt;
	}

	public String getReservedt() {
		return reservedt;
	}

	public void setReservedt(String reservedt) {
		this.reservedt = reservedt;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getScode() {
		return scode;
	}

	public void setScode(String scode) {
		this.scode = scode;
	}

	public String getSmskind() {
		return smskind;
	}

	public void setSmskind(String smskind) {
		this.smskind = smskind;
	}

	public String getSmslmstit() {
		return smslmstit;
	}

	public void setSmslmstit(String smslmstit) {
		this.smslmstit = smslmstit;
	}

	public String getSmssender() {
		return smssender;
	}

	public void setSmssender(String smssender) {
		this.smssender = smssender;
	}

	public String getSync() {
		return sync;
	}

	public void setSync(String sync) {
		this.sync = sync;
	}

	public String getTmplid() {
		return tmplid;
	}

	public void setTmplid(String tmplid) {
		this.tmplid = tmplid;
	}

	public String getWide() {
		return wide;
	}

	public void setWide(String wide) {
		this.wide = wide;
	}

}
