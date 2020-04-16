package com.api.treggo.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.treggo.entities.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {
	
	public List<Orders> findByTenantCode(String tenant);
	
	@Query("from Orders where order_id = :order_id and tenantCode = :tenant")
	public Orders fetchByOrderId(@Param("order_id") Long id, @Param("tenant") String tenant);
	
	@Query("from Orders where customer_id = :customer_id and tenantCode = :tenant")
	public List<Orders> fetchByCustomerId(@Param("customer_id") Long id, @Param("tenant") String tenant);
	
	@Query("from Orders where created_on between :start_date and :end_date and tenantCode = :tenant")
	public List<Orders> fetchOrdersByDate(@Param("start_date") LocalDate start_date, @Param("end_date") LocalDate end_date, @Param("tenant") String tenant);
	
	@Query(value = "select * from Orders where order_status = 'CLOSED' and tenant_code = :tenant order by order_id desc limit 50", nativeQuery = true)
	public List<Orders> fetchLatestOrder(@Param("tenant") String tenant);
	
}
