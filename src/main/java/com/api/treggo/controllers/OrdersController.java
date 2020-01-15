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

import com.api.treggo.entities.Orders;
import com.api.treggo.requests.NewOrderDTO;
import com.api.treggo.requests.OrderDatesDTO;
import com.api.treggo.requests.UpdateOrderDTO;
import com.api.treggo.responses.BillResponse;
import com.api.treggo.responses.ChartsResponse;
import com.api.treggo.responses.GeneralResponse;
import com.api.treggo.responses.OrdersResponse;
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
	public ResponseEntity<?> createOrder(@RequestBody NewOrderDTO dto) {
		
		Orders ord = orderService.createOrder(dto);
		
		if(ord == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failed"));
		}
		else {
			return ResponseEntity.ok(ord);
		}
	}
	
	
	@ApiOperation(value="Get all the available orders")
	@GetMapping("/getAll")
	public List<Orders> getAllOrders() {
		
		return orderService.getAllOrders();
	}
	
	@ApiOperation(value="Get single order by ID")
	@GetMapping("/getOne/{id}")
	public Orders getOne(@PathVariable("id") Long id) {
		
		return orderService.getOrderById(id);
	}
	
	
	
	@ApiOperation(value="Update the status of existing order")
	@PostMapping("/updateStatus")
	public ResponseEntity<?> updateStatus(@RequestBody UpdateOrderDTO dto) {
		
		boolean res = orderService.updateOrder(dto.getOrder_id(), dto.getStatus());
		
		if(res) {
			return ResponseEntity.ok(new GeneralResponse("success"));
		}
		else {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
	}
	
	@ApiOperation(value = "Get all orders of particular customer") 
	@GetMapping("/byPhone/{phone}")
	public ResponseEntity<?> getOrdersByCstId(@PathVariable("phone") String phone) {
		 List<Orders> orders = orderService.getOrdersByUser(phone);
		 if(orders != null) {
			 return ResponseEntity.ok(orders);
		 }
		 else {
			 return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		 }
		
	}
	
	
	@ApiOperation(value="Get Data for drawing charts")
	@GetMapping("/chart")
	public List<ChartsResponse> getChartData() {
		return orderService.getChartsData();
	}
	
	
	@ApiOperation(value="Get latest closed orders")
	@GetMapping("/latest")
	public List<OrdersResponse> getLatest() {
		return orderService.getLatestOrders();
	}
	
	
	@ApiOperation(value="Get Orders between date range")
	@PostMapping("/ordersByDate")
	public List<OrdersResponse> getOrdersByDate(@RequestBody OrderDatesDTO dto) {
		return orderService.getFromToOrders(dto);
	}
	
	
	@ApiOperation(value = "Get items list for billing by order id")
	@GetMapping("/bill/{order_id}")
	public ResponseEntity<?> generateBill(@PathVariable Long order_id) {
		
		BillResponse res = orderService.generateBill(order_id);
		
		if(res == null) {
			return ResponseEntity.status(500).body(new GeneralResponse("failure"));
		}
		
		else {
			return ResponseEntity.ok(res);
		}
	}
	
	
	
}
