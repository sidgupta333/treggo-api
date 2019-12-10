package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

	@Query("from Customers where customer_id = :customer_id")
	public Customers fetchByCustomerId(@Param("customer_id") Long id);
	
	@Query("from Customers where phone = :phone")
	public Customers fetchByPhone(@Param("phone") String phone);
	
	@Query("from Customers where validated = :status and table_id = :table_id")
	public List<Customers> fetchByStatusTableId(@Param("status") String status, @Param("table_id") Long table_id);
}
