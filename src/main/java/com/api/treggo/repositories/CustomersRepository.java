package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.Customers;
import com.api.treggo.enums.YesNo;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

	public List<Customers> findByTenantCode(String tenant);
	
	@Query("from Customers where customer_id = :customer_id and tenantCode = :tenant")
	public Customers fetchByCustomerId(@Param("customer_id") Long id, @Param("tenant") String tenant);
	
	@Query("from Customers where phone = :phone and tenantCode = :tenant")
	public Customers fetchByPhone(@Param("phone") String phone, @Param("tenant") String tenant);
	
	@Query("from Customers where table_id = :table_id and tenantCode = :tenant")
	public List<Customers> fetchByTableId(@Param("table_id") Long table_id, @Param("tenant") String tenant);
	
	@Query("from Customers where validated = :status and table_id = :table_id and tenantCode = :tenant")
	public List<Customers> fetchByStatusTableId(@Param("status") YesNo status, @Param("table_id") Long table_id, @Param("tenant") String tenant);
}
