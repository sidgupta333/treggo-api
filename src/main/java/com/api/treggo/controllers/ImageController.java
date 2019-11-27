package com.api.treggo.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.treggo.entities.BannerMaster;
import com.api.treggo.entities.Dish;
import com.api.treggo.entities.ImgMaster;
import com.api.treggo.repositories.BannerRepository;
import com.api.treggo.repositories.DishRepository;
import com.api.treggo.repositories.ImgMasterRepository;
import com.api.treggo.responses.GeneralResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImgMasterRepository imgRepo;

	@Autowired
	private DishRepository dishRepo;

	@Autowired
	private BannerRepository bannerRepo;

	@ApiOperation(value = "Upload image to server")
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {

		// Create instance of Image_Master entity and save it to DB:
		ImgMaster imgMaster = new ImgMaster();

		imgMaster.setImg_path(file.getOriginalFilename());
		imgMaster.setFile_extension(file.getContentType());
		String base64String;
		try {
			base64String = Base64.getEncoder().encodeToString(file.getBytes());
			imgMaster.setImg_data(base64String);
			imgRepo.save(imgMaster);
		}

		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}

		return ResponseEntity.ok(imgMaster);

	}

	@ApiOperation(value = "Download image from server based on dish ID")
	@GetMapping("/download/dish/{dishId}")
	public ResponseEntity<?> downloadImage(@PathVariable Long dishId) {

		// Get Dish from dish ID:
		Dish dish = dishRepo.fetchByID(dishId);

		ImgMaster img = dish.getImg();

		if (img == null) {
			return ResponseEntity.status(404).body(new GeneralResponse("not found"));
		} else {

			byte[] byteData;
			try {
				byteData = Base64.getDecoder().decode(new String(img.getImg_data()).getBytes("UTF-8"));
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(byteData);
			}

			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ResponseEntity.status(404).body(new GeneralResponse("failure"));
			}

		}
	}

	@ApiOperation(value = "Download image from server based on Banner ID")
	@GetMapping("/download/banner/{bannerId}")
	public ResponseEntity<?> downloadImageBanner(@PathVariable Long bannerId) {

		// Get the banner from banner ID:
		BannerMaster banner = bannerRepo.fetchByBannerID(bannerId);
		ImgMaster img = banner.getImage();

		if (img == null) {
			return ResponseEntity.status(404).body(new GeneralResponse("not found"));
		} else {

			byte[] byteData;
			try {
				byteData = Base64.getDecoder().decode(new String(img.getImg_data()).getBytes("UTF-8"));
				return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(byteData);
			}

			catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return ResponseEntity.status(404).body(new GeneralResponse("failure"));
			}

		}
	}
}
