package com.api.treggo.requests;

import com.api.treggo.enums.YesNo;

public class NewCustomerDTO {

	private Long customer_id;
	private String deviceId;
	private String customer_name;
	private String phone;
	private YesNo validated;

	public NewCustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewCustomerDTO(Long customer_id, String deviceId, String customer_name, String phone, YesNo validated) {
		super();
		this.customer_id = customer_id;
		this.deviceId = deviceId;
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

	public String getDeviceId() {
		return deviceId;
	}

	public void setTable_id(String  deviceId) {
		this.deviceId = deviceId;
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
