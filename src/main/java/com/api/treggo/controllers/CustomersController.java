package com.api.treggo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	public ResponseEntity<?> createCustomer(@RequestBody NewCustomerDTO dto) {
		
		Customers cst = cService.createCustomer(dto);
		if(cst == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		else {
			return ResponseEntity.ok(cst);
		}
	}
	
	
	@ApiOperation(value="Get all the existing customers")
	@GetMapping("/getAll")
	public List<Customers> getAllCustomers() {
		return cService.getAllCustomers();
	}
	
	
	@ApiOperation(value = "Update activation status of customer")
	@PostMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody ValidateCustomerDTO dto) {
		
		boolean res = cService.validateCustomer(dto.getCustId(), dto.isStatus());
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
}
