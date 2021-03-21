package com.dhn.agent.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Attachment {
	public List<Button> button = new ArrayList<Button>();
	public Image image;
	
	public void addButton(Map<String, String> btn) {
		Button temp = new Button ();
		temp.setType(btn.get("type"));
		temp.setName(btn.get("name"));
		temp.setScheme_android(btn.get("scheme_android"));
		temp.setScheme_ios(btn.get("scheme_ios"));
		temp.setUrl_mobile(btn.get("url_mobile"));
		temp.setUrl_pc(btn.get("url_pc"));
		temp.setChat_extra(btn.get("chat_extra"));
		temp.setChat_event(btn.get("chat_event"));
		temp.setPlugin_id(btn.get("plugin_id"));
		temp.setRelay_id(btn.get("relay_id"));
		temp.setOneclick_id(btn.get("oneclick_id"));
		temp.setProduct_id(btn.get("product_id"));
		temp.setBiz_form_key(btn.get("biz_form_key"));
		
		this.button.add(temp);
	}
	
	public void addImage(String img_link, String img_url) {
		image = new Image();
		image.setImg_link(img_link);
		image.setImg_url(img_url);
	}
	
	public String toJson() {
		
		if(this.button.size()> 0) {
			String json = "{\"button\":[";
				for(int i=0; i<this.button.size(); i++) {
					json = json + button.get(i);
					if(i < this.button.size()-1)
						json = json + ",";
				}
				json = json + "]}";
			
			return json; 
		} else {
			return null;
		}
	}
}
