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
	public ResponseEntity<?> createTable(@RequestBody TableMaster dto){
		TableMaster tm = tableService.createTable(dto);
		if(tm!=null)
			return ResponseEntity.ok(tm);
		else
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
	}
	
	@ApiOperation(value="Get all the tables")
	@GetMapping("/getAll")
	public List<TableMaster> getAllTables() {
		
		return tableService.getAllTables();
	}
	
	
	@ApiOperation(value = "Delete existing table")
	@DeleteMapping("/delete/{table_id}")
	public ResponseEntity<?> deleteTable(@PathVariable Long table_id) {
		
		boolean res = tableService.deleteById(table_id);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
}
