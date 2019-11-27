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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.api.treggo.enums.YesNo;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "BANNER_MASTER")
public class BannerMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long banner_id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "img_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private ImgMaster image;
	
	@Enumerated(EnumType.STRING)
	private YesNo is_available;
	
	@Column(nullable = false)
	private LocalDate start_date;

	@Column(nullable = false)
	private LocalDate created_on;
	
	public BannerMaster() {
		super();
	}

	public BannerMaster(ImgMaster image, YesNo is_available, LocalDate start_date,
			LocalDate created_on) {
		super();
		this.image = image;
		this.is_available = is_available;
		this.start_date = start_date;
		this.created_on = created_on;
	}

	public Long getBanner_id() {
		return banner_id;
	}

	public void setBanner_id(Long banner_id) {
		this.banner_id = banner_id;
	}

	public ImgMaster getImage() {
		return image;
	}

	public void setImage(ImgMaster image) {
		this.image = image;
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

	public LocalDate getCreated_on() {
		return created_on;
	}

	public void setCreated_on(LocalDate created_on) {
		this.created_on = created_on;
	}
	
	
}
