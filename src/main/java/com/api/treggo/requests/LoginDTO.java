package com.api.treggo.requests;

import org.springframework.stereotype.Component;

@Component
public class LoginDTO {

	private String username;
	private String password;
	private String tenant;

	public LoginDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginDTO(String username, String password, String tenant) {
		super();
		this.username = username;
		this.password = password;
		this.tenant = tenant;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

}
