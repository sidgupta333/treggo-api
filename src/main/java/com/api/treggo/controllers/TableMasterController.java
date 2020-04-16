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

import com.api.treggo.entities.TableMaster;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.services.TableMasterService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin 
@RequestMapping("/tables")
public class TableMasterController {

	@Autowired
	private TableMasterService tableService;

	@ApiOperation(value="Creates a new table of the system")
	@PostMapping("/create")
	public ResponseEntity<?> createTable(@RequestBody TableMaster dto, @RequestHeader("x-tenant") String tenant){
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		TableMaster tm = tableService.createTable(dto, tenant);
		if(tm!=null)
			return ResponseEntity.ok(tm);
		else
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
	}
	
	@ApiOperation(value="Get all the tables")
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllTables(@RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		return ResponseEntity.ok(tableService.getAllTables(tenant));
	}
	
	@ApiOperation(value="Get table based on device id")
	@GetMapping("/get/device/{device_id}")
	public ResponseEntity<?> getTableByDevice(@PathVariable String device_id, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		TableMaster res = tableService.getTableByDevice(device_id, tenant);
		
		if(res == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		else {
			return ResponseEntity.ok(res);
		}
	}
	
	
	@ApiOperation(value = "Delete existing table")
	@DeleteMapping("/delete/{table_id}")
	public ResponseEntity<?> deleteTable(@PathVariable Long table_id, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		boolean res = tableService.deleteById(table_id, tenant);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
}
