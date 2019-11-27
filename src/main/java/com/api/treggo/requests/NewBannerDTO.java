package com.api.treggo.requests;

import java.time.LocalDate;

import com.api.treggo.enums.YesNo;

public class NewBannerDTO {

	private Long banner_id;
	private Long img_id;
	private YesNo is_available;
	private LocalDate start_date;
	
	public NewBannerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NewBannerDTO(Long banner_id, Long img_id, YesNo is_available, LocalDate start_date
			) {
		super();
		this.banner_id = banner_id;
		this.img_id = img_id;
		this.is_available = is_available;
		this.start_date = start_date;
	}

	public Long getBanner_id() {
		return banner_id;
	}

	public void setBanner_id(Long banner_id) {
		this.banner_id = banner_id;
	}

	public Long getImg_id() {
		return img_id;
	}

	public void setImg_id(Long img_id) {
		this.img_id = img_id;
	}

	public YesNo getIs_available() {
		return is_available;
	}

	public void setIs_available(YesNo is_available) {
		this.is_available = is_available;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}	
}

