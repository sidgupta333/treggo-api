package com.api.treggo.responses;

import org.springframework.stereotype.Component;

@Component
public class CreateUserResponse {

	private String message;
	private String full_name;
	private String username;
	private boolean isValid;

	public CreateUserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CreateUserResponse(String message, String full_name, String username, boolean isValid) {
		super();
		this.message = message;
		this.full_name = full_name;
		this.username = username;
		this.isValid = isValid;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
