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
@Table(name="TBL_REQUEST_DHN")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DhnRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "MSGID", nullable = false, length = 20)
	private String MSGID;  //` VARCHAR(20) NOT NULL,
	
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

	public String getADFLAG() {
		return ADFLAG;
	}

	public String getBUTTON1() {
		return BUTTON1;
	}

	public String getBUTTON2() {
		return BUTTON2;
	}

	public String getBUTTON3() {
		return BUTTON3;
	}

	public String getBUTTON4() {
		return BUTTON4;
	}

	public String getBUTTON5() {
		return BUTTON5;
	}

	public String getIMAGELINK() {
		return IMAGELINK;
	}

	public String getIMAGEURL() {
		return IMAGEURL;
	}

	public String getMESSAGETYPE() {
		return MESSAGETYPE;
	}

	public String getMSG() {
		return MSG;
	}

	public String getMSGSMS() {
		return MSGSMS;
	}

	public String getONLYSMS() {
		return ONLYSMS;
	}

	public String getPCOM() {
		return PCOM;
	}

	public String getPINVOICE() {
		return PINVOICE;
	}

	public String getPHN() {
		return PHN;
	}

	public String getPROFILE() {
		return PROFILE;
	}

	public String getREGDT() {
		return REGDT.substring(0, 19);
	}

	public String getREMARK1() {
		return REMARK1;
	}

	public String getREMARK2() {
		return REMARK2;
	}

	public String getREMARK3() {
		return REMARK3;
	}

	public String getREMARK4() {
		return REMARK4;
	}

	public String getREMARK5() {
		return REMARK5;
	}

	public String getRESERVEDT() {
		return RESERVEDT;
	}

	public String getSCODE() {
		return SCODE;
	}

	public String getSMSKIND() {
		return SMSKIND;
	}

	public String getSMSLMSTIT() {
		return SMSLMSTIT;
	}

	public String getSMSSENDER() {
		return SMSSENDER;
	}

	public String getTMPLID() {
		return TMPLID;
	}

	public String getWIDE() {
		return WIDE;
	}

}
