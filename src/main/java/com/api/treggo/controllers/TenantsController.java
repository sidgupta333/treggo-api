package com.api.treggo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.Tenants;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.services.TenantsService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/tenants")
public class TenantsController {

	@Autowired
	private TenantsService tenantService;

	@ApiOperation(value = "Creates a new Tenant")
	@PostMapping("/create")
	public ResponseEntity<?> createTenant(@RequestBody Tenants dto) {

		Tenants t = tenantService.createTenant(dto);
		if (t == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		} else {
			return ResponseEntity.ok(t);
		}
	}

	@ApiOperation(value = "Get all the tenants")
	@GetMapping("/getAll")
	public List<Tenants> getAllTenants() {
		return tenantService.getAllTenants();
	}
	
	@ApiOperation(value = "Get all active tenants")
	@GetMapping("/all/active")
	public List<Tenants> getAllActiveTenants() {
		return tenantService.getAllActiveTenants(); 
	}

	@ApiOperation(value = "Get single Tenant by ID")
	@GetMapping("/getById/{id}")
	public Tenants getOne(@PathVariable("id") Long id) {
		return tenantService.getTenantByTenantId(id);
	}

	@ApiOperation(value = "Get single Tenant by code")
	@GetMapping("/getByCode/{code}")
	public ResponseEntity<?> getByCode(@PathVariable("code") String code) {
		Tenants t = tenantService.getTenantByTenantCode(code);
		if (t == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		}
		else {
			return ResponseEntity.ok(t);
		}
	}

	@ApiOperation(value = "Update existing Tenant")
	@PutMapping("/update")
	public ResponseEntity<?> updateTenant(@RequestBody Tenants dto) {
		Tenants t = tenantService.updateTenant(dto);
		if (t == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		} else {
			return ResponseEntity.ok(t);
		}
	}

	@ApiOperation(value = "Delete existing Tenant by ID")
	@DeleteMapping("/remove/{id}")
	public ResponseEntity<?> deleteTenant(@PathVariable("id") Long id) {
		boolean res = tenantService.deleteTenant(id);
		if (res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		} else {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		}
	}

	@ApiOperation(value = "Delete existing Tenant by code")
	@DeleteMapping("/removeByCode/{code}")
	public ResponseEntity<?> deleteTenantByCode(@PathVariable("code") String code) {

		try {
			Tenants tenant = tenantService.getTenantByTenantCode(code);
			boolean res = tenantService.deleteTenant(tenant.getTenant_id());
			if (res) {
				return ResponseEntity.ok(new GeneralResponse("success"));
			} else {
				return ResponseEntity.status(500).body(new GeneralResponse("failed"));
			}
		} catch (Exception e) {
			return ResponseEntity.status(500).body(new GeneralResponse("not found"));
		}

	}

}
