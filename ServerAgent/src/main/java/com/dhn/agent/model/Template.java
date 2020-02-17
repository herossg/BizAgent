package com.dhn.agent.model;

public class Template {
	private String senderKey;
	private String templateCode;
	private String templateName;
	private String templateContent;
	private String templateAdditionalContent;
	private String templateTitle;
	private String templateAdditionalTitle;
	private String senderKeyType;
	private String pcFlag;
	private TemButton []buttons;
	
	public String getSenderKey() {
		return senderKey;
	}
	public void setSenderKey(String senderKey) {
		this.senderKey = senderKey;
	}
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getTemplateContent() {
		return templateContent;
	}
	public void setTemplateContent(String templateContent) {
		this.templateContent = templateContent;
	}
	public String getTemplateAdditionalContent() {
		return templateAdditionalContent;
	}
	public void setTemplateAdditionalContent(String templateAdditionalContent) {
		this.templateAdditionalContent = templateAdditionalContent;
	}
	public String getTemplateTitle() {
		return templateTitle;
	}
	public void setTemplateTitle(String templateTitle) {
		this.templateTitle = templateTitle;
	}
	public String getTemplateAdditionalTitle() {
		return templateAdditionalTitle;
	}
	public void setTemplateAdditionalTitle(String templateAdditionalTitle) {
		this.templateAdditionalTitle = templateAdditionalTitle;
	}
	public String getSenderKeyType() {
		return senderKeyType;
	}
	public void setSenderKeyType(String senderKeyType) {
		this.senderKeyType = senderKeyType;
	}
	public String getPcFlag() {
		return pcFlag;
	}
	public void setPcFlag(String pcFlag) {
		this.pcFlag = pcFlag;
	}
	public TemButton[] getButtons() {
		return buttons;
	}
	public void setButtons(TemButton[] buttons) {
		this.buttons = buttons;
	}
}
