package com.api.treggo.services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Customers;
import com.api.treggo.entities.Dish;
import com.api.treggo.entities.Orders;
import com.api.treggo.entities.SubOrders;
import com.api.treggo.enums.OrderStatus;
import com.api.treggo.repositories.CustomersRepository;
import com.api.treggo.repositories.DishRepository;
import com.api.treggo.repositories.OrderRepository;
import com.api.treggo.repositories.SubOrderRepository;
import com.api.treggo.requests.NewOrderDTO;
import com.api.treggo.requests.OrderDatesDTO;
import com.api.treggo.responses.BillItemResponse;
import com.api.treggo.responses.BillResponse;
import com.api.treggo.responses.ChartsResponse;
import com.api.treggo.responses.OrdersResponse;
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;

	@Autowired
	private CustomersRepository cRepo;
	
	@Autowired
	private SubOrderRepository subRepo;
	
	@Autowired
	private DishRepository dishRepo;

	public Orders createOrder(NewOrderDTO dto, String tenant) {

		Orders ord = new Orders();
		BeanUtils.copyProperties(dto, ord);

		try {
			Customers cst = cRepo.fetchByCustomerId(dto.getCustomer_id(), tenant);
			ord.setCustomer(cst);
			ord.setOrder_date(LocalDate.parse(dto.getOrderDate()));
			ord.setOrder_status(OrderStatus.OPEN);
			ord.setTenantCode(tenant);
			ord.setCreated_on(LocalDate.now());
			orderRepo.save(ord);
		} catch (Exception e) {
			return null;
		}

		return ord;
	}

	public List<Orders> getAllOrders(String tenant) {
		return orderRepo.findByTenantCode(tenant);
	}

	public Orders getOrderById(Long id, String tenant) {
		return orderRepo.fetchByOrderId(id, tenant);
	}

	public boolean updateOrder(Long order_id, String status, String tenant) {

		try {
			Orders ord = orderRepo.fetchByOrderId(order_id, tenant);
			if (status.equalsIgnoreCase("open")) {
				ord.setOrder_status(OrderStatus.OPEN);
			} else {
				ord.setOrder_status(OrderStatus.CLOSED);
			}

			orderRepo.save(ord);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	//Get all orders based on customer id:
	public List<Orders> getOrdersByUser(String phone, String tenant) {
		try {
			Customers cst = cRepo.fetchByPhone(phone, tenant);
			return orderRepo.fetchByCustomerId(cst.getCustomer_id(), tenant);
		}
		catch(Exception e) {
			return null;
		}
		
	}

	// Daily view of chart:
	// Used to calculate charts data for daily(7 days), monthly(12 months) and
	// yearly(10 years)
	public List<ChartsResponse> getChartsData(String tenant) {

		List<ChartsResponse> response = new ArrayList<>();

		response.add(getDaysResponse(tenant));
		response.add(getMonthsResponse(tenant));
		response.add(getYearsResponse(tenant));

		return response;
	}

	// Calculate daily chart data:
	private ChartsResponse getDaysResponse(String tenant) {

		LocalDate today = LocalDate.now();
		ChartsResponse days = new ChartsResponse();
		days.setType("Days");

		LocalDate[] temp = new LocalDate[7];
		Long[] amounts = new Long[7];

		temp[6] = today;
		amounts[6] = calculateAmountInDate(today, today, tenant);

		for (int i = 6; i > 0; i--) {
			temp[6 - i] = today.minus(i, ChronoUnit.DAYS);
			amounts[6 - i] = calculateAmountInDate(temp[6 - i], temp[6 - i], tenant);
		}

		days.setLabels(temp);
		days.setData(amounts);
		return days;
	}

	// Calculate monthly chart data:
	private ChartsResponse getMonthsResponse(String tenant) {

		LocalDate today = LocalDate.now();
		ChartsResponse days = new ChartsResponse();
		days.setType("Months");

		LocalDate[] temp = new LocalDate[12];
		Long[] amounts = new Long[12];

		LocalDate prevDate = today.minus(12, ChronoUnit.MONTHS);
		for (int i = 12; i > 0; i--) {
			temp[12 - i] = today.minus(i - 1, ChronoUnit.MONTHS);
			amounts[12 - i] = calculateAmountInDate(prevDate, temp[12 - i], tenant);
			prevDate = temp[12 - i];
		}

		days.setLabels(temp);
		days.setData(amounts);
		return days;
	}
	
	
	// Calculate yearly chart data:
	private ChartsResponse getYearsResponse(String tenant) {

		LocalDate today = LocalDate.now();
		ChartsResponse days = new ChartsResponse();
		days.setType("Years");

		LocalDate[] temp = new LocalDate[10];
		Long[] amounts = new Long[10];

		LocalDate prevDate = today.minus(10, ChronoUnit.YEARS);
		for (int i = 10; i > 0; i--) {
			temp[10 - i] = today.minus(i - 1, ChronoUnit.YEARS);
			amounts[10 - i] = calculateAmountInDate(prevDate, temp[10 - i], tenant);
			prevDate = temp[10 - i];
		}

		days.setLabels(temp);
		days.setData(amounts);
		return days;
	}
	
	

	private Long calculateAmountInDate(LocalDate start_date, LocalDate end_date, String tenant) {
		Long amount = (long) 0;

		List<Orders> orders = orderRepo.fetchOrdersByDate(start_date, end_date, tenant);
		for (Orders ord : orders) {
			amount = amount + ord.getTotal_amount();
		}

		return amount;

	}
	
	
	public List<OrdersResponse> getLatestOrders(String tenant) {
		List<Orders> orders = orderRepo.fetchLatestOrder(tenant);
		return convertToResponse(orders);
	}
	
	
	public List<OrdersResponse> getFromToOrders(OrderDatesDTO dto, String tenant) {
		List<Orders> orders = orderRepo.fetchOrdersByDate(dto.getStart_date(), dto.getEnd_date(), tenant);
		return convertToResponse(orders);
	}
	
	
	private List<OrdersResponse> convertToResponse(List<Orders> orders) {
		List<OrdersResponse> res = new ArrayList<>();
		
		for(Orders ord: orders) {
			OrdersResponse temp = new OrdersResponse();
			BeanUtils.copyProperties(ord, temp);
			temp.setCustomerName(ord.getCustomer().getCustomer_name());
			res.add(temp);
		}
		
		return res;
	}
	
	
	public BillResponse generateBill(Long order_id, String tenant) {
		BillResponse res = new BillResponse();
		try {
			Orders order = orderRepo.fetchByOrderId(order_id, tenant);
			res.setName(order.getCustomer().getCustomer_name());
			res.setOrder_id(order.getOrder_id());
			res.setOrder_date(order.getCreated_on());
			res.setPhone(order.getCustomer().getPhone());
			res.setTotal_amount(order.getTotal_amount());
			
			List<BillItemResponse> items = new ArrayList<>();
			List<SubOrders> subOrders = subRepo.fetchByOrderId(res.getOrder_id(), tenant);
			
			for(SubOrders sub: subOrders) {
				
				String[] dishes = sub.getDishes().split("\\|");
				String[] quantities = sub.getQuantities().split("\\|");
				
				for(int i = 0; i < dishes.length - 1; i++) {
					
					BillItemResponse temp = new BillItemResponse();
					
					Dish dish = dishRepo.fetchByDIshName(dishes[i].trim(), tenant);
					temp.setDish(dish.getDish_name());
					temp.setBase_price(dish.getBase_price());
					temp.setQuantity(Long.parseLong(quantities[i].trim()));
					
					items.add(temp);
				}
			}
			
			res.setItems(items);
			
			return res;
		}
		catch(Exception e) {
			e.printStackTrace();
			return null;
			
		}
		
	}

}
