package com.api.treggo.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Customers;
import com.api.treggo.entities.TableMaster;
import com.api.treggo.enums.YesNo;
import com.api.treggo.repositories.CustomersRepository;
import com.api.treggo.repositories.TableMasterRepository;
import com.api.treggo.requests.NewCustomerDTO;
import com.api.treggo.responses.CustomerResponse;

@Service
public class CustomersService {

	@Autowired
	private CustomersRepository cRepo;
	
	@Autowired
	private TableMasterRepository tRepo;

	public Customers createCustomer(NewCustomerDTO dto) {

		Customers customer = new Customers();
		try {
			
			Customers cst = cRepo.fetchByPhone(dto.getPhone());
			TableMaster table = tRepo.fetchByTableID(dto.getTable_id());
			
			List<Customers> activeCst = cRepo.fetchByStatusTableId("Y", dto.getTable_id());
			
			// Deacticate all existing customers of current table
			for(Customers c: activeCst) {
				
				if(cRepo.fetchByPhone(c.getPhone()).getValidated() == YesNo.Y) {
					c.setValidated(YesNo.N);
					cRepo.save(c);
				}
			}
			
			if(cst != null) {
				cst.setValidated(YesNo.N);
				cst.setTable(table);
				cRepo.save(cst);
				return cst;
			}
			else {
				BeanUtils.copyProperties(dto, customer);
				customer.setTable(table);
				customer.setCreated_on(LocalDate.now());				
				cRepo.save(customer);
				return customer;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public List<CustomerResponse> getAllCustomers() {
		return customerResponse(cRepo.findAll());
	}

	public boolean deleteCustomer(Long custId) {
		boolean result = false;

		try {
			cRepo.deleteById(custId);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
			return result;
		}

		return result;
	}
	

	public boolean validateCustomer(Long custId, boolean status) {

		try {
			Customers cst = cRepo.fetchByCustomerId(custId);
			if (status) {
				cst.setValidated(YesNo.Y);
			} else {
				cst.setValidated(YesNo.N);
			}

			cRepo.save(cst);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	private List<CustomerResponse> customerResponse(List<Customers> cst) {
		
		List<CustomerResponse> res = new ArrayList<>();
		int i = 0;
		for(Customers c: cst) {
			CustomerResponse temp = new CustomerResponse();
			BeanUtils.copyProperties(c, temp);
			temp.setTable_id(c.getTable().getTable_id());
			res.set(i, temp);
			i++;
		}
		
		return res;
	}

}
