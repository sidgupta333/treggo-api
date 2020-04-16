package com.api.treggo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public ResponseEntity<?> createBanner(@RequestBody NewBannerDTO req, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		BannerMaster res = bannerService.createBanner(req, tenant);
		if(res != null) {
			return ResponseEntity.ok(res);
		}
		
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	
	@ApiOperation(value="Get all the advertisement banners")
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllBanners(@RequestHeader("x-tenant") String tenant) {
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		return ResponseEntity.ok(bannerService.getAllBanners(tenant));
	}
	
	
	@ApiOperation(value="Update banner status")
	@PostMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateBannerDTO dto, @RequestHeader("x-tenant") String tenant) {
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		boolean result = bannerService.updateStatus(dto, tenant);
		if(result) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	
	
	@ApiOperation(value = "Delete existing banner")
	@DeleteMapping("/delete/{banner_id}")
	public ResponseEntity<?> deleteBanner(@PathVariable Long banner_id, @RequestHeader("x-tenant") String tenant) {
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		boolean res = bannerService.deleteBanner(banner_id, tenant);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	

}
