package com.api.treggo.responses;

public class BillItemResponse {

	private String dish;
	private Long quantity;
	private Long base_price;

	public BillItemResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public BillItemResponse(String dish, Long quantity, Long base_price) {
		super();
		this.dish = dish;
		this.quantity = quantity;
		this.base_price = base_price;
	}

	public String getDish() {
		return dish;
	}

	public void setDish(String dish) {
		this.dish = dish;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getBase_price() {
		return base_price;
	}

	public void setBase_price(Long base_price) {
		this.base_price = base_price;
	}

}
