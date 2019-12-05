package com.api.treggo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.treggo.entities.SubOrders;
import com.api.treggo.enums.SubOrderStatus;
import com.api.treggo.requests.NewSubOrderDTO;
import com.api.treggo.requests.UpdateSubOrderStatus;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.responses.SubOrderResponse;
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
	public ResponseEntity<?> createSubOrder(@RequestBody NewSubOrderDTO dto) {

		SubOrders subOrder = subService.createSubOrder(dto);

		if (subOrder == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		} else {
			return ResponseEntity.ok(subOrder);
		}
	}
	
	
	@ApiOperation(value="Get all the available sub-orders")
	@GetMapping("/getAll")
	public List<SubOrderResponse> getAllSubOrders() {
		return subService.getAllSubOrders();
	}
	
	
	@ApiOperation(value="Get all the available sub-orders based on order id")
	@GetMapping("/getAll/{orderId}")
	public List<SubOrderResponse> getAllSubOrdersBYOrderId(@PathVariable Long orderId) {
		return subService.getSubOrdersByOrderID(orderId);
	}
	
	
	@ApiOperation(value="Get all the available sub-orders based on order status")
	@GetMapping("/getAll/{status}")
	public List<SubOrderResponse> getAllSubOrdersByOrderStatus(@PathVariable SubOrderStatus status) {
		return subService.getSubOrdersByOrderStatus(status);
	}
	
	
	@ApiOperation(value="Update the status of existing sub-order")
	@PostMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateSubOrderStatus dto) {
		
		boolean res = subService.updateSubOrderStatus(dto.getSubOrderId(), dto.getStatus());
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	
}
