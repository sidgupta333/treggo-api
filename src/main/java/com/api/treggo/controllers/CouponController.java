package com.api.treggo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.Coupon;
import com.api.treggo.requests.ApplyCouponDTO;
import com.api.treggo.requests.CouponDTO;
import com.api.treggo.responses.AmountResponse;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.services.CouponService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/coupons")
public class CouponController {

	@Autowired
	private CouponService couponService;
	
	@ApiOperation(value="Creates a new Discount coupon")
	@PostMapping("/create")
	public ResponseEntity<?> createCoupon(@RequestBody CouponDTO dto) {
		
		Coupon res = couponService.createCoupon(dto);
		if(res == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		}
		else {
			return ResponseEntity.ok(res);
		}
	}
	
	
	@ApiOperation(value="Get all the discount coupons")
	@GetMapping("/getAll")
	public List<Coupon> getAllCoupons() {
		return couponService.getAllCoupons();
	}
	
	
	@ApiOperation(value = "Delete existing advertisement coupon")
	@DeleteMapping("/delete/{coupon_id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable Long coupon_id) {
		
		boolean res = couponService.deleteCoupon(coupon_id);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	
	@ApiOperation(value = "Calculate total amount after applying coupon code")
	@PostMapping("/apply")
	public ResponseEntity<?> calculateAmount(@RequestBody ApplyCouponDTO dto) {
		
		Long amount = couponService.calculateDiscountAmount(dto.getCoupon_name(), dto.getAmount());
		if(amount == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		else {
			return ResponseEntity.ok(new AmountResponse(amount));
		}
	}
}
