package com.api.treggo.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.api.treggo.enums.YesNo;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;

	@Enumerated(EnumType.STRING)
	private YesNo is_admin;

	@Column(nullable = false)
	private String full_name;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private LocalDate created_on;

	@Column(nullable = false)
	private String tenantCode;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Users(Long user_id, YesNo is_admin, String full_name, String username, String password, LocalDate created_on,
			String tenantCode) {
		super();
		this.user_id = user_id;
		this.is_admin = is_admin;
		this.full_name = full_name;
		this.username = username;
		this.password = password;
		this.created_on = created_on;
		this.tenantCode = tenantCode;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public YesNo getIs_admin() {
		return is_admin;
	}

	public void setIs_admin(YesNo is_admin) {
		this.is_admin = is_admin;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

	@Override
	public String toString() {
		return "Users [is_admin=" + is_admin + ", full_name=" + full_name + ", username=" + username + ", password="
				+ password + ", created_on=" + created_on + "]";
	}

}
