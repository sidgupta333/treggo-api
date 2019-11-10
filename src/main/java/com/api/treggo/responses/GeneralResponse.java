package com.api.treggo.responses;

import org.springframework.stereotype.Component;

@Component
public class GeneralResponse {

	private String message;

	public GeneralResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GeneralResponse(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
