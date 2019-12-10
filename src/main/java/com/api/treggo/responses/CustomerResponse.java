package com.api.treggo.responses;

import java.time.LocalDate;

import com.api.treggo.enums.YesNo;

public class CustomerResponse {

	private Long customer_id;
	private Long table_id;
	private String customer_name;
	private String phone;
	private YesNo validated;
	private LocalDate created_on;

	public CustomerResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerResponse(Long customer_id, Long table_id, String customer_name, String phone, YesNo validated,
			LocalDate created_on) {
		super();
		this.customer_id = customer_id;
		this.table_id = table_id;
		this.customer_name = customer_name;
		this.phone = phone;
		this.validated = validated;
		this.created_on = created_on;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Long getTable_id() {
		return table_id;
	}

	public void setTable_id(Long table_id) {
		this.table_id = table_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public YesNo getValidated() {
		return validated;
	}

	public void setValidated(YesNo validated) {
		this.validated = validated;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

}
