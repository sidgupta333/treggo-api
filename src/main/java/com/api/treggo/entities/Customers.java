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

import com.api.treggo.enums.YesNo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Customers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customer_id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "table_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private TableMaster table;

	@Column(nullable = false)
	private String customer_name;

	@Column(nullable = false)
	private String phone;

	@Enumerated(EnumType.STRING)
	private YesNo validated;

	@Column(nullable = false)
	private LocalDate created_on;

	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customers(Long customer_id, TableMaster table, String customer_name, String phone, YesNo validated,
			LocalDate created_on) {
		super();
		this.customer_id = customer_id;
		this.table = table;
		this.customer_name = customer_name;
		this.phone = phone;
		this.validated = validated;
		this.created_on = created_on;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public TableMaster getTable() {
		return table;
	}

	public void setTable(TableMaster table) {
		this.table = table;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public YesNo getValidated() {
		return validated;
	}

	public void setValidated(YesNo validated) {
		this.validated = validated;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}

}
