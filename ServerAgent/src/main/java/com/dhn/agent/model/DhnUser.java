package com.dhn.agent.model;

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
@Table(name="DHN_CLIENT_LIST")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DhnUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "user_id")
	private String userid;
	
	@Column(name = "ip")
	private String ip;
	
	@Column(name = "use_flag")
	private String useflag;

	@Column(name = "port")
	private String port;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUseflag() {
		return useflag;
	}

	public void setUseflag(String useflag) {
		this.useflag = useflag;
	}
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}	
}
