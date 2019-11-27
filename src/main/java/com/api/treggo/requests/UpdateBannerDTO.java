package com.api.treggo.requests;

import com.api.treggo.enums.YesNo;

public class UpdateBannerDTO {

	private Long banner_id;
	private YesNo status;
	
	public UpdateBannerDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UpdateBannerDTO(Long banner_id, YesNo status) {
		super();
		this.banner_id = banner_id;
		this.status = status;
	}

	public Long getBanner_id() {
		return banner_id;
	}

	public void setBanner_id(Long banner_id) {
		this.banner_id = banner_id;
	}

	public YesNo getStatus() {
		return status;
	}

	public void setStatus(YesNo status) {
		this.status = status;
	}
	
	
}
