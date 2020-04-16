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

import com.api.treggo.entities.Orders;
import com.api.treggo.requests.NewOrderDTO;
import com.api.treggo.requests.OrderDatesDTO;
import com.api.treggo.requests.UpdateOrderDTO;
import com.api.treggo.responses.BillResponse;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.services.OrderService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrdersController {

	
	@Autowired
	private OrderService orderService;
	
	
	@ApiOperation(value="Creates a new fresh order")
	@PostMapping("/create")
	public ResponseEntity<?> createOrder(@RequestBody NewOrderDTO dto, @RequestHeader("x-tenant") String tenant) {

		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		Orders ord = orderService.createOrder(dto, tenant);
		
		if(ord == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		}
		else {
			return ResponseEntity.ok(ord);
		}
	}
	
	
	@ApiOperation(value="Get all the available orders")
	@GetMapping("/getAll")
	public ResponseEntity<?> getAllOrders(@RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		return ResponseEntity.ok(orderService.getAllOrders(tenant));
	}
	
	@ApiOperation(value="Get single order by ID")
	@GetMapping("/getOne/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") Long id, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		return ResponseEntity.ok(orderService.getOrderById(id, tenant));
	}
	
	
	
	@ApiOperation(value="Update the status of existing order")
	@PostMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateOrderDTO dto, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		boolean res = orderService.updateOrder(dto.getOrder_id(), dto.getStatus(), tenant);
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	@ApiOperation(value = "Get all orders of particular customer") 
	@GetMapping("/byPhone/{phone}")
	public ResponseEntity<?> getOrdersByCstId(@PathVariable("phone") String phone, @RequestHeader("x-tenant") String tenant) {
		 
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		List<Orders> orders = orderService.getOrdersByUser(phone, tenant);
		 if(orders != null) {
			 return ResponseEntity.ok(orders);
		 }
		 else {
			 return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		 }
		
	}
	
	
	@ApiOperation(value="Get Data for drawing charts")
	@GetMapping("/chart")
	public ResponseEntity<?> getChartData(@RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		return ResponseEntity.ok(orderService.getChartsData(tenant));
	}
	
	
	@ApiOperation(value="Get latest closed orders")
	@GetMapping("/latest")
	public ResponseEntity<?> getLatest(@RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		return ResponseEntity.ok(orderService.getLatestOrders(tenant));
	}
	
	
	@ApiOperation(value="Get Orders between date range")
	@PostMapping("/ordersByDate")
	public ResponseEntity<?> getOrdersByDate(@RequestBody OrderDatesDTO dto, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		return ResponseEntity.ok(orderService.getFromToOrders(dto, tenant));
	}
	
	
	@ApiOperation(value = "Get items list for billing by order id")
	@GetMapping("/bill/{order_id}")
	public ResponseEntity<?> generateBill(@PathVariable Long order_id, @RequestHeader("x-tenant") String tenant) {
		
		if (tenant == null) {
			return ResponseEntity.status(401).body(new GeneralResponse("Unauthorized"));
		}
		
		BillResponse res = orderService.generateBill(order_id, tenant);
		
		if(res == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		else {
			return ResponseEntity.ok(res);
		}
	}
	
	
	
}
