package com.api.treggo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

	@Query("from Customers where customer_id = :customer_id")
	public Customers fetchByCustomerId(@Param("customer_id") Long id);
}
