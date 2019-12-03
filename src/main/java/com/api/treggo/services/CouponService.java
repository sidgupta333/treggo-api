package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Coupon;
import com.api.treggo.repositories.CouponRepository;
import com.api.treggo.requests.CouponDTO;

@Service
public class CouponService {

	@Autowired
	private CouponRepository couponRepo;
	
	public Coupon createCoupon(CouponDTO dto) {
		
		Coupon coupon = new Coupon();
		BeanUtils.copyProperties(dto, coupon);
		
		if(dto.getMax_discount() == null) {
			return null;
		}
		
		// Case 1: When only maximum amount case is there
		else if(dto.getPercentage_discount() == null) {
			coupon.setPercentage_discount(null);
		}
		
		//Save entity to database:
		try {
			coupon.setCreated_on(LocalDate.now());
			couponRepo.save(coupon);
		}
		catch(Exception e) {
			return null;
		}
		
		return coupon;
	}
	
	
	//Get all existing coupon:
	public List<Coupon> getAllCoupons() {
		return couponRepo.findAll();
	}
	
	
	public boolean deleteCoupon(Long couponId) {
		
		boolean res = false;
		Coupon coupon = couponRepo.fetchByCouponID(couponId);
		
		if(coupon != null) {
			try {
				couponRepo.delete(coupon);
				res = true;
			}
			catch(Exception e) {
				res = false;
			}
		}
		
		return res;
	}
}
