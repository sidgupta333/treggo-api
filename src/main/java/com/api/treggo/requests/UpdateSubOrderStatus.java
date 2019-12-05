package com.api.treggo.requests;

import com.api.treggo.enums.SubOrderStatus;

public class UpdateSubOrderStatus {

	private Long subOrderId;
	private SubOrderStatus status;

	public UpdateSubOrderStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateSubOrderStatus(Long subOrderId, SubOrderStatus status) {
		super();
		this.subOrderId = subOrderId;
		this.status = status;
	}

	public Long getSubOrderId() {
		return subOrderId;
	}

	public void setSubOrderId(Long subOrderId) {
		this.subOrderId = subOrderId;
	}

	public SubOrderStatus getStatus() {
		return status;
	}

	public void setStatus(SubOrderStatus status) {
		this.status = status;
	}

}
