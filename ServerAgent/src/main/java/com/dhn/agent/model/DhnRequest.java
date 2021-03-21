package com.dhn.agent.model;

import java.io.Serializable;

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
@Table(name="DHN_REQUEST")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DhnRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer ID;
	
	@Column(name = "MSGID", nullable = false, length = 20)
	private String MSGID;  //` VARCHAR(20) NOT NULL,
	
	@Column(name = "userid", nullable = false, length = 20)
	private String userid;
	
	@Column(name = "AD_FLAG", nullable = true, length = 1)
	private String ADFLAG;  //` VARCHAR(1) NULL DEFAULT NULL,
	
	@Column(name = "BUTTON1", nullable = true, columnDefinition = "LONGTEXT")
	private String BUTTON1; //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "BUTTON2", nullable = true, columnDefinition = "LONGTEXT")
	private String BUTTON2;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "BUTTON3", nullable = true, columnDefinition = "LONGTEXT")
	private String BUTTON3;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "BUTTON4", nullable = true, columnDefinition = "LONGTEXT")
	private String BUTTON4;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "BUTTON5", nullable = true, columnDefinition = "LONGTEXT")
	private String BUTTON5;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "IMAGE_LINK", nullable = true, columnDefinition = "LONGTEXT")
	private String IMAGELINK;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "IMAGE_URL", nullable = true, columnDefinition = "LONGTEXT")
	private String IMAGEURL;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "MESSAGE_TYPE", nullable = true, length = 2)
	private String MESSAGETYPE;  //` VARCHAR(2) NULL DEFAULT NULL,
	
	@Column(name = "MSG", nullable = false, columnDefinition = "LONGTEXT")
	private String MSG;  //` LONGTEXT NOT NULL,
	
	@Column(name = "MSG_SMS", nullable = true, columnDefinition = "LONGTEXT")
	private String MSGSMS;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "ONLY_SMS", nullable = true, length = 1)
	private String ONLYSMS;  //` VARCHAR(1) NULL DEFAULT NULL,
	
	@Column(name = "P_COM", nullable = true, length = 2)
	private String PCOM;  //` VARCHAR(2) NULL DEFAULT NULL,
	
	@Column(name = "P_INVOICE", nullable = true, length = 100)
	private String PINVOICE;  //` VARCHAR(100) NULL DEFAULT NULL,
	
	@Column(name = "PHN", nullable = false, length = 15)
	private String PHN;  //` VARCHAR(15) NOT NULL,
	
	@Column(name = "PROFILE", nullable = true, length = 50)
	private String PROFILE;  //` VARCHAR(50) NULL DEFAULT NULL,
	
	@Column(name = "REG_DT", nullable = false, length = 20)
	private String REGDT;  //` DATETIME NOT NULL,
	
	@Column(name = "REMARK1", nullable = true, length = 50)
	private String REMARK1;  //` VARCHAR(50) NULL DEFAULT NULL,
	
	@Column(name = "REMARK2", nullable = true, length = 50)
	private String REMARK2;  //` VARCHAR(50) NULL DEFAULT NULL,
	
	@Column(name = "REMARK3", nullable = true, length = 50)
	private String REMARK3;  //` VARCHAR(50) NULL DEFAULT NULL,
	
	@Column(name = "REMARK4", nullable = true, length = 50)
	private String REMARK4;  //` VARCHAR(50) NULL DEFAULT NULL,
	
	@Column(name = "REMARK5", nullable = true, length = 50)
	private String REMARK5;  //` VARCHAR(50) NULL DEFAULT NULL,
	
	@Column(name = "RESERVE_DT", nullable = false, length = 14)
	private String RESERVEDT;  //` VARCHAR(14) NOT NULL,
	
	@Column(name = "S_CODE", nullable = true, length = 2)
	private String SCODE;  //` VARCHAR(2) NULL DEFAULT NULL,
	
	@Column(name = "SMS_KIND", nullable = true, length = 1)
	private String SMSKIND;  //` VARCHAR(1) NULL DEFAULT NULL,
	
	@Column(name = "SMS_LMS_TIT", nullable = true, length = 120)
	private String SMSLMSTIT;  //` VARCHAR(120) NULL DEFAULT NULL,
	
	@Column(name = "SMS_SENDER", nullable = true, length = 15)
	private String SMSSENDER;  //` VARCHAR(15) NULL DEFAULT NULL,
	
	@Column(name = "TMPL_ID", nullable = true, length = 30)
	private String TMPLID;  //` VARCHAR(30) NULL DEFAULT NULL,
	
	@Column(name = "WIDE", nullable = true, length = 1)
	private String WIDE;  //` CHAR(1) NULL DEFAULT 'N'

	@Column(name = "SUPPLEMENT", nullable = true, columnDefinition = "LONGTEXT")
	private String SUPPLEMENT;  //` CHAR(1) NULL DEFAULT 'N'

	@Column(name = "PRICE", nullable = true)
	private Integer PRICE;  //` CHAR(1) NULL DEFAULT 'N'

	@Column(name = "CURRENCY_TYPE", nullable = true, length = 3)
	private String CURRENCY_TYPE;  //` CHAR(1) NULL DEFAULT 'N'

	
	public String getSUPPLEMENT() {
		return SUPPLEMENT;
	}

	public void setSUPPLEMENT(String sUPPLEMENT) {
		SUPPLEMENT = sUPPLEMENT;
	}

	public int getPRICE() {
		return PRICE;
	}

	public void setPRICE(Integer pRICE) {
		PRICE = pRICE;
	}

	public String getCURRENCY_TYPE() {
		return CURRENCY_TYPE;
	}

	public void setCURRENCY_TYPE(String cURRENCY_TYPE) {
		CURRENCY_TYPE = cURRENCY_TYPE;
	}

	public String getMSGID() {
		return MSGID;
	}
	
	public void setMSGID(String mSGID) {
		MSGID = mSGID;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
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

	public String getRESERVEDT() {
		return RESERVEDT;
	}

	public void setRESERVEDT(String rESERVEDT) {
		RESERVEDT = rESERVEDT;
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
