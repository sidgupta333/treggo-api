package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.Dish;

public interface DishRepository extends JpaRepository<Dish, Long> {
	
	public List<Dish> findByTenantCode(String tenant);

	@Query("from Dish where dish_id = :dish_Id and tenantCode = :tenant")
	public Dish fetchByID(@Param("dish_Id") Long id, @Param("tenant") String tenant);
	
	@Query("from Dish where category_id = :category_id and tenantCode = :tenant")
	public List<Dish> fetchByCategory(@Param("category_id") Long category_id, @Param("tenant") String tenant);
	
	@Query("from Dish where dish_name = :dish_name and tenantCode = :tenant")
	public Dish fetchByDIshName(@Param("dish_name") String dish_name, @Param("tenant") String tenant);
	


}
