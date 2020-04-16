package com.api.treggo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.SubOrders;
import com.api.treggo.enums.SubOrderStatus;
import com.api.treggo.requests.NewSubOrderDTO;
import com.api.treggo.requests.UpdateSubOrderStatus;
import com.api.treggo.responses.AllSubOrdersResponse;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.services.SubOrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/subOrders")
public class SubOrdersController {

	@Autowired
	private SubOrderService subService;

	@ApiOperation(value = "Creates a new fresh sub-order")
	@PostMapping("/create")
	public ResponseEntity<?> createSubOrder(@RequestBody NewSubOrderDTO dto, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		SubOrders subOrder = subService.createSubOrder(dto, tenant);

		if (subOrder == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		} else {
			return ResponseEntity.ok(subOrder);
		}
	}
	
	
	@ApiOperation(value="Get all the available sub-orders")
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllSubOrders(@RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		return ResponseEntity.ok(subService.getAllSubOrders(tenant));
	}
	
	
	@ApiOperation(value="Get all the available sub-orders based on order id")
	@GetMapping("/getAll/{orderId}")
	public ResponseEntity<?> getAllSubOrdersBYOrderId(@PathVariable Long orderId, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		return ResponseEntity.ok(subService.getSubOrdersByOrderID(orderId, tenant));
	}
	
	
	@ApiOperation(value="Get all the available sub-orders based on order status")
	@GetMapping("/getAll/{status}")
	public ResponseEntity<?> getAllSubOrdersByOrderStatus(@PathVariable SubOrderStatus status, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		return ResponseEntity.ok(subService.getSubOrdersByOrderStatus(status, tenant));
	}
	
	@ApiOperation(value="Get all SubOrder based on suborder ID")
	@GetMapping("/getOne/{id}")
	public ResponseEntity<?> getSubOrderById(@PathVariable Long id, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		SubOrders res = subService.getSubOrderById(id, tenant);
		if(res == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		}
		else {
			return ResponseEntity.ok(res);
		}
	}
	
	
	
	@ApiOperation(value="Update the status of existing sub-order")
	@PostMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateSubOrderStatus dto, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		boolean res = subService.updateSubOrderStatus(dto.getSubOrderId(), dto.getStatus(), tenant);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	
	@ApiOperation(value="Update the status of existing sub-order")
	@GetMapping("/drillDown")
	public ResponseEntity<?> getDrillDown(@RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		List<AllSubOrdersResponse> res = subService.getAllSubOrdersAndCustomer(tenant);
		
		if(res == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		else {
			return ResponseEntity.ok(res);
		}
	}
	
}
