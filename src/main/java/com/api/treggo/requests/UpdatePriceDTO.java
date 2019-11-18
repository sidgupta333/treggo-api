package com.api.treggo.requests;

public class UpdatePriceDTO {

	private Long dish_Id;
	private Long price;
	public UpdatePriceDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UpdatePriceDTO(Long dish_Id, Long price) {
		super();
		this.dish_Id = dish_Id;
		this.price = price;
	}
	public Long getDish_Id() {
		return dish_Id;
	}
	public void setDish_Id(Long dish_Id) {
		this.dish_Id = dish_Id;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	
}
