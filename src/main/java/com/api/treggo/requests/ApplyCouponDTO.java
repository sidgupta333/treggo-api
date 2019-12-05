package com.api.treggo.requests;

public class ApplyCouponDTO {

	String coupon_name;
	Long amount;

	public ApplyCouponDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplyCouponDTO(String coupon_name, Long amount) {
		super();
		this.coupon_name = coupon_name;
		this.amount = amount;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
