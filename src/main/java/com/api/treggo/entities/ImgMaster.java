package com.api.treggo.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "IMG_MASTER")
public class ImgMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long img_id;

	@Column(nullable = false)
	private String img_path;

	@Column(nullable = false)
	private String file_extension;
	
	
	@Column(nullable = false)
	private String img_data;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "img")
	private Dish dish;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "image")
	private BannerMaster bannerMaster;

	public BannerMaster getBannerMaster() {
		return bannerMaster;
	}



	public void setBannerMaster(BannerMaster bannerMaster) {
		this.bannerMaster = bannerMaster;
	}



	public ImgMaster() {
		super();
	}

	

	public ImgMaster(Long img_id, String img_path, String file_extension, String img_data) {
		super();
		this.img_id = img_id;
		this.img_path = img_path;
		this.file_extension = file_extension;
		this.img_data = img_data;
	}



	public Dish getDish() {
		return dish;
	}



	public void setDish(Dish dish) {
		this.dish = dish;
	}



	public Long getImg_id() {
		return img_id;
	}

	public void setImg_id(Long img_id) {
		this.img_id = img_id;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	public String getFile_extension() {
		return file_extension;
	}

	public void setFile_extension(String file_extension) {
		this.file_extension = file_extension;
	}

	public String getImg_data() {
		return img_data;
	}

	public void setImg_data(String img_data) {
		this.img_data = img_data;
	}
	
	

	
}
