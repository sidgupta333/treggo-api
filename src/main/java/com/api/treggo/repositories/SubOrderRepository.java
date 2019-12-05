package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.SubOrders;

public interface SubOrderRepository extends JpaRepository<SubOrders, Long> {

	@Query("from SubOrders where sub_order_id = :sub_order_id")
	public SubOrders fetchByID(@Param("sub_order_id") Long id);
	
	@Query("from SubOrders where order_id = :order_id")
	public List<SubOrders> fetchByOrderId(@Param("order_id") Long id);
	
	@Query("from SubOrders where status = :status")
	public List<SubOrders> fetchByStatus(@Param("status") String status);
}
