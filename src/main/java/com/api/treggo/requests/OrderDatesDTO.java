package com.api.treggo.requests;

import java.time.LocalDate;

public class OrderDatesDTO {

	LocalDate start_date;
	LocalDate end_date;

	public OrderDatesDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public OrderDatesDTO(LocalDate start_date, LocalDate end_date) {
		super();
		this.start_date = start_date;
		this.end_date = end_date;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

}
