package com.api.treggo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.Customers;
import com.api.treggo.requests.NewCustomerDTO;
import com.api.treggo.requests.ValidateCustomerDTO;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.services.CustomersService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomersController {

	@Autowired
	private CustomersService cService;
	
	@ApiOperation(value="Creates a new Customer")
	@PostMapping("/create")
	public ResponseEntity<?> createCustomer(@RequestBody NewCustomerDTO dto, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		Customers cst = cService.createCustomer(dto, tenant);
		if(cst == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		else {
			return ResponseEntity.ok(cst);
		}
	}
	
	
	@ApiOperation(value="Get all the existing customers")
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllCustomers(@RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		return ResponseEntity.ok(cService.getAllCustomers(tenant));
	}
	
	
	@ApiOperation(value = "Update activation status of customer")
	@PostMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody ValidateCustomerDTO dto, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		boolean res = cService.validateCustomer(dto.getCustId(), dto.isStatus(), tenant);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
}
