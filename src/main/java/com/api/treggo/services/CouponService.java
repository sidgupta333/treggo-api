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
	
	public Coupon createCoupon(CouponDTO dto, String tenant) {
		
		Coupon coupon = new Coupon();
		BeanUtils.copyProperties(dto, coupon);
		coupon.setTenantCode(tenant);
		
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
	public List<Coupon> getAllCoupons(String tenant) {
		return couponRepo.findByTenantCode(tenant);
	}
	
	
	public boolean deleteCoupon(Long couponId, String tenant) {
		
		boolean res = false;
		Coupon coupon = couponRepo.fetchByCouponID(couponId, tenant);
		
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
	
	
	//Calculate amount based on applied coupon
	public Long calculateDiscountAmount(String couponName, Long total, String tenant) {
		Long amount = null;
		
		try {
			Coupon coupon = couponRepo.fetchByCouponName(couponName, tenant);
			
			if(coupon != null) {
				
				//Check if some percentage amount is present
				if(coupon.getPercentage_discount() != null) {
					
					double percentageAmount = ((double)coupon.getPercentage_discount() / 100) * total;
					if(percentageAmount > coupon.getMax_discount()) {
						amount = total - coupon.getMax_discount();
					}
					else {
						amount = total - (long)percentageAmount;
					}
				}
				else {
					amount = total - coupon.getMax_discount();
				}
			}
			
			return amount;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
