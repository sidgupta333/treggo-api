package com.api.treggo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Customers;
import com.api.treggo.entities.Orders;
import com.api.treggo.entities.SubOrders;
import com.api.treggo.enums.SubOrderStatus;
import com.api.treggo.repositories.CustomersRepository;
import com.api.treggo.repositories.OrderRepository;
import com.api.treggo.repositories.SubOrderRepository;
import com.api.treggo.requests.NewSubOrderDTO;
import com.api.treggo.responses.AllSubOrdersResponse;
import com.api.treggo.responses.SubOrderResponse;

@Service
public class SubOrderService {

	@Autowired
	private SubOrderRepository subRepo;

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private CustomersRepository cstRepo;

	public SubOrders createSubOrder(NewSubOrderDTO dto) {

		SubOrders res = new SubOrders();

		try {
			// Fetch Order of provided order ID:
			Orders ord = orderRepo.fetchByOrderId(dto.getOrder_id());

			// Fetch Customer of provided customer ID
			Customers cst = cstRepo.fetchByCustomerId(dto.getCustomer_id());

			// Map all details to request object
			BeanUtils.copyProperties(dto, res);

			res.setCustomer(cst);
			res.setOrder(ord);
			res.setCreated_on(LocalDate.now());

			subRepo.save(res);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Fetch all SubOrders
	public List<SubOrderResponse> getAllSubOrders() {

		try {
			List<SubOrders> subOrders = subRepo.findAll();
			List<SubOrderResponse> res = getSubResponse(subOrders);

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Fetch subOrders based on order id:
	public List<SubOrderResponse> getSubOrdersByOrderID(Long order_id) {

		try {
			List<SubOrders> subOrders = subRepo.fetchByOrderId(order_id);
			List<SubOrderResponse> res = getSubResponse(subOrders);

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Fetch subOrders based on order status:
	public List<SubOrderResponse> getSubOrdersByOrderStatus(SubOrderStatus status) {

		try {
			List<SubOrders> subOrders = subRepo.fetchByStatus(status.toString());
			List<SubOrderResponse> res = getSubResponse(subOrders);

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// Fetch subOrder by orderID:
	public SubOrders getSubOrderById(Long id) {
		return subRepo.fetchByID(id);
	}

	// Update sub-order status:
	public boolean updateSubOrderStatus(Long subOrderId, SubOrderStatus status) {

		try {
			SubOrders sb = subRepo.fetchByID(subOrderId);
			sb.setStatus(status);
			subRepo.save(sb);

			return true;
		} catch (Exception e) {
			return false;
		}
	}

	// Get all open suborders with the current status and tables:
	public List<AllSubOrdersResponse> getAllSubOrdersAndCustomer() {
		
		//Get all opened suborders
		try {
			
		
			List<SubOrders> subOrders = subRepo.fetchByNotStatus(SubOrderStatus.CLOSED);
			
			List<AllSubOrdersResponse> response = new ArrayList<>();
			
			int i = 0;
			for(SubOrders sb : subOrders) {
				
				AllSubOrdersResponse tempRes = new AllSubOrdersResponse();
				
				tempRes.setSub_order_id(sb.getSub_order_id());
				tempRes.setOrder_id(sb.getOrder().getOrder_id());
				tempRes.setSub_order_status(sb.getStatus());
				tempRes.setOrder_status(sb.getOrder().getOrder_status());
				tempRes.setCustomer_id(sb.getCustomer().getCustomer_id());
				tempRes.setCustomer_name(sb.getCustomer().getCustomer_name());
				tempRes.setDishes(sb.getDishes().split("\\|"));
				tempRes.setquantities(sb.getQuantities().split("\\|"));
				tempRes.setTable_id(sb.getCustomer().getTable().getTable_id());
				tempRes.setTable_number(sb.getCustomer().getTable().getTable_number());
				
				response.add(i, tempRes);
				i++;
			}
			
			return response;
		}
		catch(Exception e) {
			return null;
		}
		
	}

	private List<SubOrderResponse> getSubResponse(List<SubOrders> subOrders) {

		List<SubOrderResponse> res = new ArrayList<>();

		int i = 0;
		for (SubOrders sub : subOrders) {

			SubOrderResponse rs = new SubOrderResponse();
			BeanUtils.copyProperties(sub, rs);
			rs.setOrder_id(sub.getOrder().getOrder_id());
			rs.setCustomer_id(sub.getCustomer().getCustomer_id());

			res.set(i, rs);
			i++;
		}

		return res;
	}

}
