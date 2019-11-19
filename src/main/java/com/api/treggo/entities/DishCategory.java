package com.api.treggo.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DISH_CATEGORY")
public class DishCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long category_id;

	@Column(nullable = false)
	private String category_name;

	@Column(nullable = false)
	private LocalDate created_on;


	public DishCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DishCategory(Long category_id, String category_name, LocalDate created_on) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.created_on = created_on;
	}

	public Long getCategory_id() {
		return category_id;
	}

	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

	
}
