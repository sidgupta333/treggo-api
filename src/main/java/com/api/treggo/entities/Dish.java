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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.api.treggo.enums.YesNo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DISH")
public class Dish {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dish_id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "category_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private DishCategory category;

	@Column(nullable = false)
	private String dish_name;

	@Column(nullable = false)
	private Long base_price;

	@Enumerated(EnumType.STRING)
	private YesNo is_available;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "img_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private ImgMaster img;

	@Column(nullable = false)
	private LocalDate created_on;

	@Column(nullable = false)
	private String tenant_code;

	public Dish() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Dish(Long dish_id, DishCategory category, String dish_name, Long base_price, YesNo is_available,
			LocalDate created_on, String tenant_code) {
		super();
		this.dish_id = dish_id;
		this.category = category;
		this.dish_name = dish_name;
		this.base_price = base_price;
		this.is_available = is_available;
		this.created_on = created_on;
		this.tenant_code = tenant_code;
	}

	public Long getDish_id() {
		return dish_id;
	}

	public void setDish_id(Long dish_id) {
		this.dish_id = dish_id;
	}

	public DishCategory getCategory() {
		return category;
	}

	public void setCategory(DishCategory category) {
		this.category = category;
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

	public ImgMaster getImg() {
		return img;
	}

	public void setImg(ImgMaster img) {
		this.img = img;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

	public String getTenant_code() {
		return tenant_code;
	}

	public void setTenant_code(String tenant_code) {
		this.tenant_code = tenant_code;
	}

}
