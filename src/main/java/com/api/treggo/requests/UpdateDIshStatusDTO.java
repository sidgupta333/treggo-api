package com.api.treggo.requests;

public class UpdateDIshStatusDTO {

	private Long dish_Id;
	private String status;

	public UpdateDIshStatusDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateDIshStatusDTO(Long dish_Id, String status) {
		super();
		this.dish_Id = dish_Id;
		this.status = status;
	}

	public Long getDish_Id() {
		return dish_Id;
	}

	public void setDish_Id(Long dish_Id) {
		this.dish_Id = dish_Id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
