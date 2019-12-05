package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Customers;
import com.api.treggo.enums.YesNo;
import com.api.treggo.repositories.CustomersRepository;
import com.api.treggo.requests.NewCustomerDTO;

@Service
public class CustomersService {

	@Autowired
	private CustomersRepository cRepo;

	public Customers createCustomer(NewCustomerDTO dto) {

		Customers customer = new Customers();

		BeanUtils.copyProperties(dto, customer);
		customer.setCreated_on(LocalDate.now());

		try {
			cRepo.save(customer);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return customer;
	}

	public List<Customers> getAllCustomers() {
		return cRepo.findAll();
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

}
