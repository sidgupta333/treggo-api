package com.api.treggo.requests;

import org.springframework.stereotype.Component;

@Component
public class CreateUser {

	private Long user_id;
	private String full_name;
	private String is_admin;
	private String username;
	private String password;

	public CreateUser(Long user_id, String full_name, String is_admin, String username, String password) {
		super();
		this.user_id = user_id;
		this.full_name = full_name;
		this.is_admin = is_admin;
		this.username = username;
		this.password = password;
	}

	public CreateUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}

	public String getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
