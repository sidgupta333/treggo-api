package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {

	@Query("from Dish where dish_id = :dish_Id ")
	public Dish fetchByID(@Param("dish_Id") Long id);
	
	@Query("from Dish where category_id = :category_id")
	public List<Dish> fetchByCategory(@Param("category_id") Long category_id);
}
