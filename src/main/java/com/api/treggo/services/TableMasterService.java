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
	
	
	public TableMaster createTable(TableMaster dto, String tenant) {
		
		dto.setTenantCode(tenant);
		dto.setCreated_on(LocalDate.now());
		return tableRepo.save(dto);
	}
	
	public List<TableMaster> getAllTables(String tenant) {
		return tableRepo.findByTenantCode(tenant);
	}
	
	public TableMaster getTableById(Long id, String tenant) {
		return tableRepo.fetchByTableID(id, tenant);
	}
	
	public boolean deleteById(Long id, String tenant) {
		try {
			List<Customers> customers = cRepo.fetchByTableId(id, tenant);
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
	public TableMaster getTableByDevice(String device_id, String tenant) {
		return tableRepo.fetchByDeviceID(device_id, tenant);
	}
}
