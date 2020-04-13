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
	private String ADFLAG; //` varchar(1) null default null,

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
	private String IMAGELINK; //` longtext null default null,

	@Column(name = "IMAGE_URL", columnDefinition = "LONGTEXT")
	private String IMAGEURL; //` longtext null default null,

	@Column(name = "KIND", length = 1)
	private String KIND; //` varchar(1) null default null,

	@Column(name = "MESSAGE", columnDefinition = "LONGTEXT")
	private String MESSAGE; //` longtext null default null,

	@Column(name = "MESSAGE_TYPE", length = 2)
	private String MESSAGETYPE; //` varchar(2) null default null,

	@Column(name = "MSG", nullable = false, columnDefinition = "LONGTEXT")
	private String MSG; //` longtext not null,

	@Column(name = "MSG_SMS", columnDefinition = "LONGTEXT")
	private String MSGSMS; //` longtext null default null,

	@Column(name = "ONLY_SMS", length = 1)
	private String ONLYSMS; //` varchar(1) null default null,

	@Column(name = "P_COM", length = 2)
	private String PCOM; //` varchar(2) null default null,

	@Column(name = "P_INVOICE", length = 100)
	private String PINVOICE; //` varchar(100) null default null,

	@Column(name = "PHN", nullable = false, length = 15)
	private String PHN; //` varchar(15) not null,

	@Column(name = "PROFILE", length = 50)
	private String PROFILE; //` varchar(50) null default null,

	@Column(name = "REG_DT", nullable = false, length = 20)
	private String REGDT; //` datetime not null,

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
	private String RESDT; //` datetime null default null,

	@Column(name = "RESERVE_DT", nullable = false, length = 14)
	private String RESERVEDT; //` varchar(14) not null,

	@Column(name = "RESULT", length = 1)
	private String RESULT; //` varchar(1) null default null,

	@Column(name = "S_CODE", length = 2)
	private String SCODE; //` varchar(2) null default null,

	@Column(name = "SMS_KIND", length = 1)
	private String SMSKIND; //` varchar(1) null default null,

	@Column(name = "SMS_LMS_TIT", length = 120)
	private String SMSLMSTIT; //` varchar(120) null default null,

	@Column(name = "SMS_SENDER", length = 15)
	private String SMSSENDER; //` varchar(15) null default null,

	@Column(name = "SYNC", nullable = false, length = 1)
	private String SYNC; //` varchar(1) not null,

	@Column(name = "TMPL_ID", length = 30)
	private String TMPLID; //` varchar(30) null default null,

	@Column(name = "WIDE", length = 1)
	private String WIDE; //` char(1) null default 'n' 

	public String getMSGID() {
		return MSGID;
	}

	public void setMSGID(String mSGID) {
		MSGID = mSGID;
	}

	public String getADFLAG() {
		return ADFLAG;
	}

	public void setADFLAG(String aDFLAG) {
		ADFLAG = aDFLAG;
	}

	public String getBUTTON1() {
		return BUTTON1;
	}

	public void setBUTTON1(String bUTTON1) {
		BUTTON1 = bUTTON1;
	}

	public String getBUTTON2() {
		return BUTTON2;
	}

	public void setBUTTON2(String bUTTON2) {
		BUTTON2 = bUTTON2;
	}

	public String getBUTTON3() {
		return BUTTON3;
	}

	public void setBUTTON3(String bUTTON3) {
		BUTTON3 = bUTTON3;
	}

	public String getBUTTON4() {
		return BUTTON4;
	}

	public void setBUTTON4(String bUTTON4) {
		BUTTON4 = bUTTON4;
	}

	public String getBUTTON5() {
		return BUTTON5;
	}

	public void setBUTTON5(String bUTTON5) {
		BUTTON5 = bUTTON5;
	}

	public String getCODE() {
		return CODE;
	}

	public void setCODE(String cODE) {
		CODE = cODE;
	}

	public String getIMAGELINK() {
		return IMAGELINK;
	}

	public void setIMAGELINK(String iMAGELINK) {
		IMAGELINK = iMAGELINK;
	}

	public String getIMAGEURL() {
		return IMAGEURL;
	}

	public void setIMAGEURL(String iMAGEURL) {
		IMAGEURL = iMAGEURL;
	}

	public String getKIND() {
		return KIND;
	}

	public void setKIND(String kIND) {
		KIND = kIND;
	}

	public String getMESSAGE() {
		return MESSAGE;
	}

	public void setMESSAGE(String mESSAGE) {
		MESSAGE = mESSAGE;
	}

	public String getMESSAGETYPE() {
		return MESSAGETYPE;
	}

	public void setMESSAGETYPE(String mESSAGETYPE) {
		MESSAGETYPE = mESSAGETYPE;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}

	public String getMSGSMS() {
		return MSGSMS;
	}

	public void setMSGSMS(String mSGSMS) {
		MSGSMS = mSGSMS;
	}

	public String getONLYSMS() {
		return ONLYSMS;
	}

	public void setONLYSMS(String oNLYSMS) {
		ONLYSMS = oNLYSMS;
	}

	public String getPCOM() {
		return PCOM;
	}

	public void setPCOM(String pCOM) {
		PCOM = pCOM;
	}

	public String getPINVOICE() {
		return PINVOICE;
	}

	public void setPINVOICE(String pINVOICE) {
		PINVOICE = pINVOICE;
	}

	public String getPHN() {
		return PHN;
	}

	public void setPHN(String pHN) {
		PHN = pHN;
	}

	public String getPROFILE() {
		return PROFILE;
	}

	public void setPROFILE(String pROFILE) {
		PROFILE = pROFILE;
	}

	public String getREGDT() {
		return REGDT;
	}

	public void setREGDT(String rEGDT) {
		REGDT = rEGDT;
	}

	public String getREMARK1() {
		return REMARK1;
	}

	public void setREMARK1(String rEMARK1) {
		REMARK1 = rEMARK1;
	}

	public String getREMARK2() {
		return REMARK2;
	}

	public void setREMARK2(String rEMARK2) {
		REMARK2 = rEMARK2;
	}

	public String getREMARK3() {
		return REMARK3;
	}

	public void setREMARK3(String rEMARK3) {
		REMARK3 = rEMARK3;
	}

	public String getREMARK4() {
		return REMARK4;
	}

	public void setREMARK4(String rEMARK4) {
		REMARK4 = rEMARK4;
	}

	public String getREMARK5() {
		return REMARK5;
	}

	public void setREMARK5(String rEMARK5) {
		REMARK5 = rEMARK5;
	}

	public String getRESDT() {
		return RESDT;
	}

	public void setRESDT(String rESDT) {
		RESDT = rESDT;
	}

	public String getRESERVEDT() {
		return RESERVEDT;
	}

	public void setRESERVEDT(String rESERVEDT) {
		RESERVEDT = rESERVEDT;
	}

	public String getRESULT() {
		return RESULT;
	}

	public void setRESULT(String rESULT) {
		RESULT = rESULT;
	}

	public String getSCODE() {
		return SCODE;
	}

	public void setSCODE(String sCODE) {
		SCODE = sCODE;
	}

	public String getSMSKIND() {
		return SMSKIND;
	}

	public void setSMSKIND(String sMSKIND) {
		SMSKIND = sMSKIND;
	}

	public String getSMSLMSTIT() {
		return SMSLMSTIT;
	}

	public void setSMSLMSTIT(String sMSLMSTIT) {
		SMSLMSTIT = sMSLMSTIT;
	}

	public String getSMSSENDER() {
		return SMSSENDER;
	}

	public void setSMSSENDER(String sMSSENDER) {
		SMSSENDER = sMSSENDER;
	}

	public String getSYNC() {
		return SYNC;
	}

	public void setSYNC(String sYNC) {
		SYNC = sYNC;
	}

	public String getTMPLID() {
		return TMPLID;
	}

	public void setTMPLID(String tMPLID) {
		TMPLID = tMPLID;
	}

	public String getWIDE() {
		return WIDE;
	}

	public void setWIDE(String wIDE) {
		WIDE = wIDE;
	}
 
}
