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
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.api.treggo.enums.SubOrderStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "SUB_ORDERS")
public class SubOrders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long sub_order_id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "customer_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Customers customer;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Orders order;

	@Column(nullable = false)
	private LocalDate order_date;

	@Enumerated(EnumType.STRING)
	private SubOrderStatus status;

	@Column(nullable = false)
	private String dishes;

	@Column(nullable = false)
	private String quantities;

	@Column(nullable = false)
	private LocalDate created_on;

	@Column(nullable = false)
	private String tenant_code;

	public SubOrders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SubOrders(Long sub_order_id, Customers customer, Orders order, LocalDate order_date, SubOrderStatus status,
			String dishes, String quantities, LocalDate created_on, String tenant_code) {
		super();
		this.sub_order_id = sub_order_id;
		this.customer = customer;
		this.order = order;
		this.order_date = order_date;
		this.status = status;
		this.dishes = dishes;
		this.quantities = quantities;
		this.created_on = created_on;
		this.tenant_code = tenant_code;
	}

	public Long getSub_order_id() {
		return sub_order_id;
	}

	public void setSub_order_id(Long sub_order_id) {
		this.sub_order_id = sub_order_id;
	}

	public Customers getCustomer() {
		return customer;
	}

	public void setCustomer(Customers customer) {
		this.customer = customer;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
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

	public String getTenant_code() {
		return tenant_code;
	}

	public void setTenant_code(String tenant_code) {
		this.tenant_code = tenant_code;
	}

}
