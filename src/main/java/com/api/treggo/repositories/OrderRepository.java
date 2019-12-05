package com.api.treggo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.treggo.entities.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
	
	@Query("from Orders where order_id = :order_id")
	public Orders fetchByOrderId(@Param("order_id") Long id);

}
