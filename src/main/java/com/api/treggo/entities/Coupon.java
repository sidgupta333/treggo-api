package com.api.treggo.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COUPON")
public class Coupon {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long coupon_id;

	@Column(nullable = false)
	private String coupon_name;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private Long percentage_discount;

	@Column(nullable = false)
	private Long max_discount;

	@Column(nullable = false)
	private LocalDate created_on;

	@Column(nullable = false)
	private String tenantCode;

	public Coupon() {
		super();
	}

	public Coupon(Long coupon_id, String coupon_name, String desc, Long percentage_discount, Long max_discount,
			LocalDate created_on, String tenantCode) {
		super();
		this.coupon_id = coupon_id;
		this.coupon_name = coupon_name;
		this.description = desc;
		this.percentage_discount = percentage_discount;
		this.max_discount = max_discount;
		this.created_on = created_on;
		this.tenantCode = tenantCode;
	}

	public String getDesc() {
		return description;
	}

	public void setDesc(String desc) {
		this.description = desc;
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

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

	public String getTenantCode() {
		return tenantCode;
	}

	public void setTenantCode(String tenantCode) {
		this.tenantCode = tenantCode;
	}

}
