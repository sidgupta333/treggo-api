package com.api.treggo.responses;

import com.api.treggo.enums.OrderStatus;
import com.api.treggo.enums.SubOrderStatus;

public class AllSubOrdersResponse {

	private Long sub_order_id;
	private Long order_id;
	private Long customer_id;
	private Long table_id;
	private SubOrderStatus sub_order_status;
	private OrderStatus order_status;
	private String[] dishes;
	private String[] quantities;
	private String customer_name;
	private String table_number;

	public AllSubOrdersResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AllSubOrdersResponse(Long sub_order_id, Long order_id, Long customer_id, Long table_id,
			SubOrderStatus sub_order_status, OrderStatus order_status, String[] dishes, String[] quantities,
			String customer_name, String table_number) {
		super();
		this.sub_order_id = sub_order_id;
		this.order_id = order_id;
		this.customer_id = customer_id;
		this.table_id = table_id;
		this.sub_order_status = sub_order_status;
		this.order_status = order_status;
		this.dishes = dishes;
		this.quantities = quantities;
		this.customer_name = customer_name;
		this.table_number = table_number;
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

	public Long getTable_id() {
		return table_id;
	}

	public void setTable_id(Long table_id) {
		this.table_id = table_id;
	}

	public SubOrderStatus getSub_order_status() {
		return sub_order_status;
	}

	public void setSub_order_status(SubOrderStatus sub_order_status) {
		this.sub_order_status = sub_order_status;
	}

	public OrderStatus getOrder_status() {
		return order_status;
	}

	public void setOrder_status(OrderStatus order_status) {
		this.order_status = order_status;
	}

	public String[] getDishes() {
		return dishes;
	}

	public void setDishes(String[] dishes) {
		this.dishes = dishes;
	}

	public String[] getquantities() {
		return quantities;
	}

	public void setquantities(String[] quantities) {
		this.quantities = quantities;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getTable_number() {
		return table_number;
	}

	public void setTable_number(String table_number) {
		this.table_number = table_number;
	}

}
