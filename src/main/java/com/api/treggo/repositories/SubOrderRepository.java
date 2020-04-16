package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.SubOrders;
import com.api.treggo.enums.SubOrderStatus;

public interface SubOrderRepository extends JpaRepository<SubOrders, Long> {

	public List<SubOrders> findByTenantCode(String tenant);
	
	@Query("from SubOrders where sub_order_id = :sub_order_id and tenantCode = :tenant")
	public SubOrders fetchByID(@Param("sub_order_id") Long id, @Param("tenant") String tenant);
	
	@Query("from SubOrders where order_id = :order_id and tenantCode = :tenant")
	public List<SubOrders> fetchByOrderId(@Param("order_id") Long id, @Param("tenant") String tenant);
	
	@Query("from SubOrders where status = :status and tenantCode = :tenant")
	public List<SubOrders> fetchByStatus(@Param("status") String status, @Param("tenant") String tenant);
	
	@Query("from SubOrders where status != :status and tenantCode = :tenant")
	public List<SubOrders> fetchByNotStatus(@Param("status") SubOrderStatus status, @Param("tenant") String tenant);
	
}
