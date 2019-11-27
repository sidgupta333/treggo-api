package com.api.treggo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.BannerMaster;
import com.api.treggo.requests.NewBannerDTO;
import com.api.treggo.requests.UpdateBannerDTO;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.services.BannerService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/banners")
public class BannerController {

	@Autowired
	BannerService bannerService;

	@ApiOperation(value="Creates a new advertisement banner")
	@PostMapping("/create")
	public ResponseEntity<?> createBanner(@RequestBody NewBannerDTO req) {
		
		BannerMaster res = bannerService.createBanner(req);
		if(res != null) {
			return ResponseEntity.ok(res);
		}
		
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	
	@ApiOperation(value="Get all the advertisement banners")
	@GetMapping("/getAll")
	public List<BannerMaster> getAllBanners() {
	
		return bannerService.getAllBanners();
	}
	
	
	@ApiOperation(value="Update banner status")
	@PostMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateBannerDTO dto) {
		
		boolean result = bannerService.updateStatus(dto);
		if(result) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	
	
	@ApiOperation(value = "Delete existing banner")
	@DeleteMapping("/delete/{banner_id}")
	public ResponseEntity<?> deleteBanner(@PathVariable Long banner_id) {
	
		boolean res = bannerService.deleteBanner(banner_id);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	

}
