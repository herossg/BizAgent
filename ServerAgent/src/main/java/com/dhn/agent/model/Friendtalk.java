package com.dhn.agent.model;

public class Friendtalk {
	private String message_type;
	private String serial_number;
	private String sender_key;
	private String phone_number;
	private String app_user_id;
	private String user_key;
	private String message;
	private String ad_flag;
	private String wide;
	private Attachment attachment;
 
	public String getMessage_type() {
		return message_type;
	}
	public void setMessage_type(String message_type) {
		this.message_type = message_type;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public String getSender_key() {
		return sender_key;
	}
	public void setSender_key(String sender_key) {
		this.sender_key = sender_key;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public String getApp_user_id() {
		return app_user_id;
	}
	public void setApp_user_id(String app_user_id) {
		this.app_user_id = app_user_id;
	}
	public String getUser_key() {
		return user_key;
	}
	public void setUser_key(String user_key) {
		this.user_key = user_key;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAd_flag() {
		return ad_flag;
	}
	public void setAd_flag(String ad_flag) {
		this.ad_flag = ad_flag;
	}
	public String getWide() {
		return wide;
	}
	public void setWide(String wide) {
		this.wide = wide;
	}
	public Attachment getAttachment() {
		return attachment;
	}
	
	public void setAttachment(Attachment att) {
		this.attachment = att;
	}
}
