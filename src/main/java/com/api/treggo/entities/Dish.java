package com.api.treggo.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.api.treggo.enums.YesNo;

@Entity
@Table(name = "DISH")
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long dish_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private DishCategory category_id;

	@Column(nullable = false)
	private String dish_name;

	@Column(nullable = false)
	private Long base_price;

	@Enumerated(EnumType.STRING)
	private YesNo is_available;

	@Lob
	@Column
	private byte[] img_data;

	@Column(nullable = false)
	private LocalDate created_on;

	public Dish() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dish(Long dish_id, DishCategory category_id, String dish_name, Long base_price, YesNo is_available,
			byte[] img_data, LocalDate created_on) {
		super();
		this.dish_id = dish_id;
		this.category_id = category_id;
		this.dish_name = dish_name;
		this.base_price = base_price;
		this.is_available = is_available;
		this.img_data = img_data;
		this.created_on = created_on;
	}

	public Long getDish_id() {
		return dish_id;
	}

	public void setDish_id(Long dish_id) {
		this.dish_id = dish_id;
	}

	public DishCategory getCategory_id() {
		return category_id;
	}

	public void setCategory_id(DishCategory category_id) {
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

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}


}
