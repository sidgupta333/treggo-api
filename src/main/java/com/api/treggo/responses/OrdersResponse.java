package com.api.treggo.responses;

import java.time.LocalDate;

public class OrdersResponse {

	private Long order_id;
	private String customerName;
	private LocalDate order_date;
	private Long total_amount;

	public OrdersResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrdersResponse(Long order_id, String customerName, LocalDate order_date, Long total_amount) {
		super();
		this.order_id = order_id;
		this.customerName = customerName;
		this.order_date = order_date;
		this.total_amount = total_amount;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public LocalDate getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}

	public Long getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Long total_amount) {
		this.total_amount = total_amount;
	}

}
