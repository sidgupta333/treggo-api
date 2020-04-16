package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.DishCategory;

public interface DishCategoryRepository extends JpaRepository<DishCategory, Long> {

	public List<DishCategory> findByTenantCode(String tenant);
	
	@Query("from DishCategory where category_id = :category_id and tenantCode = :tenant")
	public DishCategory fetchByCategoryID(@Param("category_id") Long id, @Param("tenant") String tenant);
}
