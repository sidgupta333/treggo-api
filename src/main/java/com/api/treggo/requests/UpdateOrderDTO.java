package com.api.treggo.requests;

public class UpdateOrderDTO {

	private Long order_id;
	private String status;

	public UpdateOrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateOrderDTO(Long order_id, String status) {
		super();
		this.order_id = order_id;
		this.status = status;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
