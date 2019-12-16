package com.api.treggo.responses;

import org.springframework.stereotype.Component;

import com.api.treggo.enums.YesNo;

@Component
public class LoginResponse {

	private String username;
	private String fullName;
	private String message;
	private boolean status;
	private YesNo is_admin;

	public LoginResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginResponse(String username, String fullName, String message, boolean status, YesNo is_admin) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.message = message;
		this.status = status;
		this.is_admin = is_admin;
	}

	public YesNo getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(YesNo is_admin) {
		this.is_admin = is_admin;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
