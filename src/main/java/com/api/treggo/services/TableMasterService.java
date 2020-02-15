package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Customers;
import com.api.treggo.entities.TableMaster;
import com.api.treggo.repositories.CustomersRepository;
import com.api.treggo.repositories.TableMasterRepository;

@Service
public class TableMasterService {

	
	@Autowired
	private TableMasterRepository tableRepo;
	
	@Autowired
	private CustomersRepository cRepo;
	
	
	public TableMaster createTable(TableMaster dto) {
		
		dto.setCreated_on(LocalDate.now());
		return tableRepo.save(dto);
	}
	
	public List<TableMaster> getAllTables() {
		return tableRepo.findAll();
	}
	
	public TableMaster getTableById(Long id) {
		return tableRepo.fetchByTableID(id);
	}
	
	public boolean deleteById(Long id) {
		try {
			List<Customers> customers = cRepo.fetchByTableId(id);
			for(Customers c : customers) {
				c.setTable(null);
				try {
					cRepo.save(c);
				}
				catch(Exception e) {
					return false;
				}
			}
			tableRepo.deleteById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	
	//Fetch table details by device id
	public TableMaster getTableByDevice(String device_id) {
		return tableRepo.fetchByDeviceID(device_id);
	}
}
