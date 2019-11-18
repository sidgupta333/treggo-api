package com.api.treggo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.DishCategory;

public interface DishCategoryRepository extends JpaRepository<DishCategory, Long> {

	@Query("from DishCategory where category_id = :category_id ")
	public DishCategory fetchByCategoryID(@Param("category_id") Long id);
}
