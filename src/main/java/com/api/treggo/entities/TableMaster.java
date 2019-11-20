package com.api.treggo.entities;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="TABLE_MASTER")
public class TableMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long table_id;
	
	private String device_id;
	
	private String table_number;
	
	private LocalDate created_on;

	public TableMaster() {
		super();
	}

	public TableMaster(String device_id, String table_number, LocalDate created_on) {
		super();
		this.device_id = device_id;
		this.table_number = table_number;
		this.created_on = created_on;
	}

	public Long getTable_id() {
		return table_id;
	}

	public void setTable_id(Long table_id) {
		this.table_id = table_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getTable_number() {
		return table_number;
	}

	public void setTable_number(String table_number) {
		this.table_number = table_number;
	}

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}
	
}
