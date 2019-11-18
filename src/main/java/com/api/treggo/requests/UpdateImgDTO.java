package com.api.treggo.requests;

public class UpdateImgDTO {

	private Long dish_Id;
	private byte[] img;

	public UpdateImgDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateImgDTO(Long dish_Id, byte[] img) {
		super();
		this.dish_Id = dish_Id;
		this.img = img;
	}

	public Long getDish_Id() {
		return dish_Id;
	}

	public void setDish_Id(Long dish_Id) {
		this.dish_Id = dish_Id;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

}
