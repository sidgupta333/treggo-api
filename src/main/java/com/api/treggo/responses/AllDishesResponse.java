package com.api.treggo.responses;

import java.util.List;

import com.api.treggo.entities.Dish;

public class AllDishesResponse {

	private Long category_id;
	private String category_name;
	private List<Dish> dishes;
	
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
	
	

	public List<Dish> getDishes() {
		return dishes;
	}
	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}
	public AllDishesResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}

