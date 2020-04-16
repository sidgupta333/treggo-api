package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	@Query("from Coupon where coupon_id = :coupon_id and tenantCode = :tenant")
	public Coupon fetchByCouponID(@Param("coupon_id") Long id, @Param("tenant") String tenant);
	
	@Query("from Coupon where coupon_name = :coupon_name and tenantCode = :tenant")
	public Coupon fetchByCouponName(@Param("coupon_name") String name, @Param("tenant") String tenant);
	
	public List<Coupon> findByTenantCode(String tenant);

}
