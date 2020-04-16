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
import com.api.treggo.requests.UpdateBannerDTO;

@Service
public class BannerService {

	@Autowired
	BannerRepository bannerRepo;

	@Autowired
	ImgMasterRepository imgRepo;

	public BannerMaster createBanner(NewBannerDTO req, String tenant) {

		ImgMaster img = imgRepo.fetchByImgID(req.getImg_id());

		if (img == null) {
			return null;
		}

		else {
			BannerMaster banner = new BannerMaster();
			BeanUtils.copyProperties(req, banner);
			banner.setTenantCode(tenant);
			String localDate = req.getStart_date();
			banner.setStart_date(LocalDate.parse(localDate));
			banner.setImage(img);
			banner.setCreated_on(LocalDate.now());

			try {
				return bannerRepo.save(banner);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public boolean updateStatus(UpdateBannerDTO dto, String tenant) {

		try {
			BannerMaster banner = bannerRepo.fetchByBannerID(dto.getBanner_id(), tenant);
			banner.setIs_available(dto.getStatus());
			bannerRepo.save(banner);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public List<BannerMaster> getAllBanners(String tenant) {
		return bannerRepo.findByTenantCode(tenant);
	}

	public BannerMaster findByBannerID(Long id, String tenant) {
		return bannerRepo.fetchByBannerID(id, tenant);
	}

	public boolean deleteBanner(Long id, String tenant) {
		try {
			BannerMaster temp = bannerRepo.fetchByBannerID(id, tenant);
			imgRepo.delete(temp.getImage());
			bannerRepo.delete(temp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
