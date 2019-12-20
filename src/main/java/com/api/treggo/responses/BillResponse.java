package com.api.treggo.responses;

import java.time.LocalDate;
import java.util.List;


public class BillResponse {

	private Long order_id;
	private String name;
	private String phone;
	private LocalDate order_date;
	private Long total_amount;
	private List<BillItemResponse> items;

	public BillResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public BillResponse(Long order_id, String name, String phone, LocalDate order_date, Long total_amount,
			List<BillItemResponse> items) {
		super();
		this.order_id = order_id;
		this.name = name;
		this.phone = phone;
		this.order_date = order_date;
		this.total_amount = total_amount;
		this.items = items;
	}



	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
	

	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<BillItemResponse> getItems() {
		return items;
	}

	public void setItems(List<BillItemResponse> items) {
		this.items = items;
	}

}
