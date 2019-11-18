package com.api.treggo.requests;

import com.api.treggo.enums.YesNo;

public class NewDishDTO {

	private Long dish_id;
	private Long category_id;
	private String dish_name;
	private Long base_price;
	private YesNo is_available;
	private byte[] img_data;

	public NewDishDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewDishDTO(Long dish_id, Long category_id, String dish_name, Long base_price, YesNo is_available,
			byte[] img_data) {
		super();
		this.dish_id = dish_id;
		this.category_id = category_id;
		this.dish_name = dish_name;
		this.base_price = base_price;
		this.is_available = is_available;
		this.img_data = img_data;
	}

	public Long getDish_id() {
		return dish_id;
	}

	public void setDish_id(Long dish_id) {
		this.dish_id = dish_id;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getDish_name() {
		return dish_name;
	}

	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}

	public Long getBase_price() {
		return base_price;
	}

	public void setBase_price(Long base_price) {
		this.base_price = base_price;
	}

	public YesNo getIs_available() {
		return is_available;
	}

	public void setIs_available(YesNo is_available) {
		this.is_available = is_available;
	}

	public byte[] getImg_data() {
		return img_data;
	}

	public void setImg_data(byte[] img_data) {
		this.img_data = img_data;
	}
}
