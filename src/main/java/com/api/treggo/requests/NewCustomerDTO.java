package com.api.treggo.requests;

import com.api.treggo.enums.YesNo;

public class NewCustomerDTO {

	private Long customer_id;
	private Long table_id;
	private String customer_name;
	private String phone;
	private YesNo validated;

	public NewCustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewCustomerDTO(Long customer_id, Long table_id, String customer_name, String phone, YesNo validated) {
		super();
		this.customer_id = customer_id;
		this.table_id = table_id;
		this.customer_name = customer_name;
		this.phone = phone;
		this.validated = validated;
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

}
