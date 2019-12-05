package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Customers;
import com.api.treggo.entities.Orders;
import com.api.treggo.enums.OrderStatus;
import com.api.treggo.repositories.CustomersRepository;
import com.api.treggo.repositories.OrderRepository;
import com.api.treggo.requests.NewOrderDTO;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private CustomersRepository cRepo;

	public Orders createOrder(NewOrderDTO dto) {
		
		Orders ord = new Orders();
		BeanUtils.copyProperties(dto, ord);
		
		try {		
			Customers cst = cRepo.fetchByCustomerId(dto.getCustomer_id());
			ord.setCustomer(cst);
			ord.setOrder_date(LocalDate.parse(dto.getOrderDate()));
			ord.setOrder_status(OrderStatus.OPEN);
			ord.setCreated_on(LocalDate.now());
			orderRepo.save(ord);
		}
		catch(Exception e) {
			return null;
		}
		
		return ord;
	}
	
	public List<Orders> getAllOrders() {
		return orderRepo.findAll();
	}
	
	
	public boolean updateOrder(Long order_id, String status) {
		
		try {
			Orders ord = orderRepo.fetchByOrderId(order_id);
			if(status.equalsIgnoreCase("open")) {
				ord.setOrder_status(OrderStatus.OPEN);
			}
			else {
				ord.setOrder_status(OrderStatus.CLOSED);
			}
			
			orderRepo.save(ord);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
