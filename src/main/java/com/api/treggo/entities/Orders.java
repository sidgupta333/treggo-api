package com.api.treggo.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.api.treggo.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long order_id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Customers customer;

	@Column(nullable = false)
	private LocalDate order_date;

	@Enumerated(EnumType.STRING)
	private OrderStatus order_status;

	@Column(nullable = false)
	private Long total_amount;

	@Column(nullable = false)
	private LocalDate created_on;

	@Column(nullable = false)
	private String tenant_code;

	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Long order_id, Customers customer, LocalDate order_date, OrderStatus order_status, Long total_amount,
			LocalDate created_on, String tenant_code) {
		super();
		this.order_id = order_id;
		this.customer = customer;
		this.order_date = order_date;
		this.order_status = order_status;
		this.total_amount = total_amount;
		this.created_on = created_on;
		this.tenant_code = tenant_code;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public LocalDate getOrder_date() {
		return order_date;
	}

	public void setOrder_date(LocalDate order_date) {
		this.order_date = order_date;
	}

	public OrderStatus getOrder_status() {
		return order_status;
	}

	public void setOrder_status(OrderStatus order_status) {
		this.order_status = order_status;
	}

	public Long getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(Long total_amount) {
		this.total_amount = total_amount;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

	public String getTenant_code() {
		return tenant_code;
	}

	public void setTenant_code(String tenant_code) {
		this.tenant_code = tenant_code;
	}

}
