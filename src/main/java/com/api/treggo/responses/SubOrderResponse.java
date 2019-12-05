package com.api.treggo.responses;

import java.time.LocalDate;

import com.api.treggo.enums.SubOrderStatus;

public class SubOrderResponse {

	private Long sub_order_id;
	private Long order_id;
	private Long customer_id;
	private LocalDate order_date;
	private SubOrderStatus status;
	private String dishes;
	private String quantities;
	private LocalDate created_on;

	public SubOrderResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubOrderResponse(Long sub_order_id, Long order_id, Long customer_id, LocalDate order_date,
			SubOrderStatus status, String dishes, String quantities, LocalDate created_on) {
		super();
		this.sub_order_id = sub_order_id;
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.order_date = order_date;
		this.status = status;
		this.dishes = dishes;
		this.quantities = quantities;
		this.created_on = created_on;
	}

	public Long getSub_order_id() {
		return sub_order_id;
	}

	public void setSub_order_id(Long sub_order_id) {
		this.sub_order_id = sub_order_id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public LocalDate getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}

	public SubOrderStatus getStatus() {
		return status;
	}

	public void setStatus(SubOrderStatus status) {
		this.status = status;
	}

	public String getDishes() {
		return dishes;
	}

	public void setDishes(String dishes) {
		this.dishes = dishes;
	}

	public String getQuantities() {
		return quantities;
	}

	public void setQuantities(String quantities) {
		this.quantities = quantities;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

}
