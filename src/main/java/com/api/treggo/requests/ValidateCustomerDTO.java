package com.api.treggo.requests;

public class ValidateCustomerDTO {

	private Long custId;
	private boolean status;
	
	public ValidateCustomerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ValidateCustomerDTO(Long custId, boolean status) {
		super();
		this.custId = custId;
		this.status = status;
	}
	
	public Long getCustId() {
		return custId;
	}
	public void setCustId(Long custId) {
		this.custId = custId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
