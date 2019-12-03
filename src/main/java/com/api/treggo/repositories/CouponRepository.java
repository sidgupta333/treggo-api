package com.api.treggo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
	
	@Query("from Coupon where coupon_id = :coupon_id")
	public Coupon fetchByCouponID(@Param("coupon_id") Long id);

}
