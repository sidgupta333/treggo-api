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
public class Tenants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tenant_id;

	@Column(nullable = false)
	private String tenant_code;

	@Column(nullable = false)
	private String tenant_name;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String sec_question;

	@Column(nullable = false)
	private String sec_answer;

	@Enumerated(EnumType.STRING)
	private YesNo is_activated;

	@Column(nullable = false)
	private LocalDate created_on;

	public Tenants() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Tenants(Long tenant_id, String tenant_code, String tenant_name, String phone, String address,
			String sec_question, String sec_answer, YesNo is_activated, LocalDate created_on) {
		super();
		this.tenant_id = tenant_id;
		this.tenant_code = tenant_code;
		this.tenant_name = tenant_name;
		this.phone = phone;
		this.address = address;
		this.sec_question = sec_question;
		this.sec_answer = sec_answer;
		this.is_activated = is_activated;
		this.created_on = created_on;
	}

	public Long getTenant_id() {
		return tenant_id;
	}

	public void setTenant_id(Long tenant_id) {
		this.tenant_id = tenant_id;
	}

	public String getTenant_code() {
		return tenant_code;
	}

	public void setTenant_code(String tenant_code) {
		this.tenant_code = tenant_code;
	}

	public String getTenant_name() {
		return tenant_name;
	}

	public void setTenant_name(String tenant_name) {
		this.tenant_name = tenant_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSec_question() {
		return sec_question;
	}

	public void setSec_question(String sec_question) {
		this.sec_question = sec_question;
	}

	public String getSec_answer() {
		return sec_answer;
	}

	public void setSec_answer(String sec_answer) {
		this.sec_answer = sec_answer;
	}

	public YesNo getIs_activated() {
		return is_activated;
	}

	public void setIs_activated(YesNo is_activated) {
		this.is_activated = is_activated;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

}
