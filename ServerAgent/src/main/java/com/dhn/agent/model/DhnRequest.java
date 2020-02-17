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
@Table(name="DHN_REQUEST1")
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
	private String AD_FLAG;  //` VARCHAR(1) NULL DEFAULT NULL,
	
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
	private String IMAGE_LINK;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "IMAGE_URL", nullable = true, columnDefinition = "LONGTEXT")
	private String IMAGE_URL;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "MESSAGE_TYPE", nullable = true, length = 2)
	private String MESSAGE_TYPE;  //` VARCHAR(2) NULL DEFAULT NULL,
	
	@Column(name = "MSG", nullable = false, columnDefinition = "LONGTEXT")
	private String MSG;  //` LONGTEXT NOT NULL,
	
	@Column(name = "MSG_SMS", nullable = true, columnDefinition = "LONGTEXT")
	private String MSG_SMS;  //` LONGTEXT NULL DEFAULT NULL,
	
	@Column(name = "ONLY_SMS", nullable = true, length = 1)
	private String ONLY_SMS;  //` VARCHAR(1) NULL DEFAULT NULL,
	
	@Column(name = "P_COM", nullable = true, length = 2)
	private String P_COM;  //` VARCHAR(2) NULL DEFAULT NULL,
	
	@Column(name = "P_INVOICE", nullable = true, length = 100)
	private String P_INVOICE;  //` VARCHAR(100) NULL DEFAULT NULL,
	
	@Column(name = "PHN", nullable = false, length = 15)
	private String PHN;  //` VARCHAR(15) NOT NULL,
	
	@Column(name = "PROFILE", nullable = true, length = 50)
	private String PROFILE;  //` VARCHAR(50) NULL DEFAULT NULL,
	
	@Column(name = "REG_DT", nullable = false, length = 20)
	private String REG_DT;  //` DATETIME NOT NULL,
	
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
	private String RESERVE_DT;  //` VARCHAR(14) NOT NULL,
	
	@Column(name = "S_CODE", nullable = true, length = 2)
	private String S_CODE;  //` VARCHAR(2) NULL DEFAULT NULL,
	
	@Column(name = "SMS_KIND", nullable = true, length = 1)
	private String SMS_KIND;  //` VARCHAR(1) NULL DEFAULT NULL,
	
	@Column(name = "SMS_LMS_TIT", nullable = true, length = 120)
	private String SMS_LMS_TIT;  //` VARCHAR(120) NULL DEFAULT NULL,
	
	@Column(name = "SMS_SENDER", nullable = true, length = 15)
	private String SMS_SENDER;  //` VARCHAR(15) NULL DEFAULT NULL,
	
	@Column(name = "TMPL_ID", nullable = true, length = 30)
	private String TMPL_ID;  //` VARCHAR(30) NULL DEFAULT NULL,
	
	@Column(name = "WIDE", nullable = true, length = 1)
	private String WIDE;  //` CHAR(1) NULL DEFAULT 'N'

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

	public String getAD_FLAG() {
		return AD_FLAG;
	}

	public void setAD_FLAG(String aD_FLAG) {
		AD_FLAG = aD_FLAG;
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

	public String getIMAGE_LINK() {
		return IMAGE_LINK;
	}

	public void setIMAGE_LINK(String iMAGE_LINK) {
		IMAGE_LINK = iMAGE_LINK;
	}

	public String getIMAGE_URL() {
		return IMAGE_URL;
	}

	public void setIMAGE_URL(String iMAGE_URL) {
		IMAGE_URL = iMAGE_URL;
	}

	public String getMESSAGE_TYPE() {
		return MESSAGE_TYPE;
	}

	public void setMESSAGE_TYPE(String mESSAGE_TYPE) {
		MESSAGE_TYPE = mESSAGE_TYPE;
	}

	public String getMSG() {
		return MSG;
	}

	public void setMSG(String mSG) {
		MSG = mSG;
	}

	public String getMSG_SMS() {
		return MSG_SMS;
	}

	public void setMSG_SMS(String mSG_SMS) {
		MSG_SMS = mSG_SMS;
	}

	public String getONLY_SMS() {
		return ONLY_SMS;
	}

	public void setONLY_SMS(String oNLY_SMS) {
		ONLY_SMS = oNLY_SMS;
	}

	public String getP_COM() {
		return P_COM;
	}

	public void setP_COM(String p_COM) {
		P_COM = p_COM;
	}

	public String getP_INVOICE() {
		return P_INVOICE;
	}

	public void setP_INVOICE(String p_INVOICE) {
		P_INVOICE = p_INVOICE;
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

	public String getREG_DT() {
		return REG_DT;
	}

	public void setREG_DT(String rEG_DT) {
		REG_DT = rEG_DT;
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

	public String getRESERVE_DT() {
		return RESERVE_DT;
	}

	public void setRESERVE_DT(String rESERVE_DT) {
		RESERVE_DT = rESERVE_DT;
	}

	public String getS_CODE() {
		return S_CODE;
	}

	public void setS_CODE(String s_CODE) {
		S_CODE = s_CODE;
	}

	public String getSMS_KIND() {
		return SMS_KIND;
	}

	public void setSMS_KIND(String sMS_KIND) {
		SMS_KIND = sMS_KIND;
	}

	public String getSMS_LMS_TIT() {
		return SMS_LMS_TIT;
	}

	public void setSMS_LMS_TIT(String sMS_LMS_TIT) {
		SMS_LMS_TIT = sMS_LMS_TIT;
	}

	public String getSMS_SENDER() {
		return SMS_SENDER;
	}

	public void setSMS_SENDER(String sMS_SENDER) {
		SMS_SENDER = sMS_SENDER;
	}

	public String getTMPL_ID() {
		return TMPL_ID;
	}

	public void setTMPL_ID(String tMPL_ID) {
		TMPL_ID = tMPL_ID;
	}

	public String getWIDE() {
		return WIDE;
	}

	public void setWIDE(String wIDE) {
		WIDE = wIDE;
	}
	
}
