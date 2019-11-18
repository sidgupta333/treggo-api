package com.api.treggo.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DISH_CATEGORY")
public class DishCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long category_id;

	@Column(nullable = false)
	private String category_name;

	@Column(nullable = false)
	private LocalDate created_on;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "category_id")
	private List<Dish> dishes = new ArrayList<>();

	public DishCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DishCategory(Long category_id, String category_name, LocalDate created_on, List<Dish> dishes) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
		this.created_on = created_on;
		this.dishes = dishes;
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

	public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

}
