package com.api.treggo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public ResponseEntity<?> createCoupon(@RequestBody CouponDTO dto, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		Coupon res = couponService.createCoupon(dto, tenant);
		if(res == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		}
		else {
			return ResponseEntity.ok(res);
		}
	}
	
	
	@ApiOperation(value="Get all the discount coupons")
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCoupons(@RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		return ResponseEntity.ok(couponService.getAllCoupons(tenant));
	}
	
	
	@ApiOperation(value = "Delete existing advertisement coupon")
	@DeleteMapping("/delete/{coupon_id}")
	public ResponseEntity<?> deleteCoupon(@PathVariable Long coupon_id, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		boolean res = couponService.deleteCoupon(coupon_id, tenant);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	
	@ApiOperation(value = "Calculate total amount after applying coupon code")
	@PostMapping("/apply")
	public ResponseEntity<?> calculateAmount(@RequestBody ApplyCouponDTO dto, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		Long amount = couponService.calculateDiscountAmount(dto.getCoupon_name(), dto.getAmount(), tenant);
		if(amount == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		else {
			return ResponseEntity.ok(new AmountResponse(amount));
		}
	}
}
