package com.api.treggo.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.api.treggo.entities.ImgMaster;
import com.api.treggo.repositories.ImgMasterRepository;
import com.api.treggo.responses.GeneralResponse;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/images")
public class ImageController {

	@Autowired
	private ImgMasterRepository imgRepo;

	@ApiOperation(value = "Upload image to server")
	@PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {

		// Create instance of Image_Master entity and save it to DB:
		ImgMaster imgMaster = new ImgMaster();

		imgMaster.setImg_path(file.getOriginalFilename());
		imgMaster.setFile_extension(file.getContentType());

		try {
			imgMaster.setImg_data(file.getBytes());
			imgRepo.save(imgMaster);
		}
		
		catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		return ResponseEntity.ok(imgMaster);

		
	}
}
