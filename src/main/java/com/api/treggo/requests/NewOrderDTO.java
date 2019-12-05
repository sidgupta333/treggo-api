package com.api.treggo.requests;

public class NewOrderDTO {

	private Long order_id;
	private Long customer_id;
	private String orderDate;
	private Long total_amount;
	
	public NewOrderDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewOrderDTO(Long order_id, Long customer_id, String orderDate, Long total_amount) {
		super();
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.orderDate = orderDate;
		this.total_amount = total_amount;
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

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	public Long getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Long total_amount) {
		this.total_amount = total_amount;
	}
	
	
}
