package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.BannerMaster;
import com.api.treggo.entities.ImgMaster;
import com.api.treggo.repositories.BannerRepository;
import com.api.treggo.repositories.ImgMasterRepository;
import com.api.treggo.requests.NewBannerDTO;

@Service
public class BannerService {

	@Autowired
	BannerRepository bannerRepo;
	
	@Autowired
	ImgMasterRepository imgRepo;
	
	
	public BannerMaster createBanner(NewBannerDTO req) {
		
		ImgMaster img = imgRepo.fetchByImgID(req.getImg_id());
		
		if(img == null) {
			return null;
		}
		
		else {
			BannerMaster banner = new BannerMaster();
			BeanUtils.copyProperties(req, banner);
			String localDate = req.getStart_date();
			banner.setStart_date(LocalDate.parse(localDate));
			banner.setImage(img);
			banner.setCreated_on(LocalDate.now());
			
			try {
				return bannerRepo.save(banner);
			}
			catch(Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}
	
	
	public List<BannerMaster> getAllBanners() {
		return bannerRepo.findAll();
	}
	
	
	public BannerMaster findByBannerID(Long id) {
		return bannerRepo.fetchByBannerID(id);
	}
	
	
	public boolean deleteBanner(Long id) {
		try {
			BannerMaster temp = bannerRepo.fetchByBannerID(id);
			imgRepo.delete(temp.getImage());
			bannerRepo.delete(temp);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
	
}
