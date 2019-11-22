package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.TableMaster;
import com.api.treggo.repositories.TableMasterRepository;

@Service
public class TableMasterService {

	
	@Autowired
	private TableMasterRepository tableRepo;
	
	
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
			tableRepo.deleteById(id);
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
