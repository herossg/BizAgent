package com.dhn.agent.model;

public class Alimtalk {
	private String message_type;
	private String serial_number;
	private String sender_key;
	private String phone_number;
	private String app_user_id;
	private String template_code;
	private String message;
	private String response_method;
	private int timeout;
	private Attachment attachment;
	private String channel_key;
	private int price;
	private String currency_type;
	private Supplement supplement;
	
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCurrency_type() {
		return currency_type;
	}
	public void setCurrency_type(String currency_type) {
		this.currency_type = currency_type;
	}
	public Supplement getSupplement() {
		return supplement;
	}
	public void setSupplement(Supplement supplement) {
		this.supplement = supplement;
	}
	
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
	public String getTemplate_code() {
		return template_code;
	}
	public void setTemplate_code(String template_code) {
		this.template_code = template_code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getResponse_method() {
		return response_method;
	}
	public void setResponse_method(String response_method) {
		this.response_method = response_method;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public Attachment getAttachment() {
		return attachment;
	}
	
	public void setAttachment(Attachment att) {
		this.attachment = att;
	}
	
	public String getChannel_key() {
		return channel_key;
	}
	public void setChannel_key(String channel_key) {
		this.channel_key = channel_key;
	}
	
	
	
}
