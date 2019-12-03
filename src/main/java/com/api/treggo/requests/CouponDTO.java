package com.api.treggo.requests;

public class CouponDTO {

	private Long coupon_id;
	private String coupon_name;
	private String desc;
	private Long percentage_discount;
	private Long max_discount;
	
	public CouponDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CouponDTO(Long coupon_id, String coupon_name, String desc, Long percentage_discount, Long max_discount) {
		super();
		this.coupon_id = coupon_id;
		this.coupon_name = coupon_name;
		this.desc = desc;
		this.percentage_discount = percentage_discount;
		this.max_discount = max_discount;
	}

	public Long getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(Long coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCoupon_name() {
		return coupon_name;
	}

	public void setCoupon_name(String coupon_name) {
		this.coupon_name = coupon_name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Long getPercentage_discount() {
		return percentage_discount;
	}

	public void setPercentage_discount(Long percentage_discount) {
		this.percentage_discount = percentage_discount;
	}

	public Long getMax_discount() {
		return max_discount;
	}

	public void setMax_discount(Long max_discount) {
		this.max_discount = max_discount;
	}
	
	
	
}
