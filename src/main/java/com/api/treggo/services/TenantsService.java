package com.api.treggo.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.treggo.entities.Tenants;
import com.api.treggo.enums.YesNo;
import com.api.treggo.repositories.TenantsRepository;

@Service
public class TenantsService {

	@Autowired
	private TenantsRepository repo;

	public Tenants createTenant(Tenants dto) {
		dto.setCreated_on(LocalDate.now());
		dto.setTenant_code(dto.getTenant_code().toUpperCase());
		try {
			Tenants tenant = repo.fetchByTenantCode(dto.getTenant_code());
			if (tenant == null) {
				return repo.save(dto);
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	public List<Tenants> getAllTenants() {
		return repo.findAll();
	}

	public Tenants getTenantByTenantId(Long tenantId) {
		return repo.fetchByTenantID(tenantId);
	}

	public Tenants getTenantByTenantCode(String tenantCode) {
		Tenants t = repo.fetchByTenantCode(tenantCode.toUpperCase());
		if(t.getIs_activated() == YesNo.Y) {
			return t;
		}
		else {
			return null;
		}
	}

	public Tenants updateTenant(Tenants dto) {
		return repo.save(dto);
	}
	
	public boolean deleteTenant(Long tenantId) {
		try {
			repo.deleteById(tenantId);
		}
		catch(Exception e) {
			return false;
		}
		return true;
	}
	
	public List<Tenants> getAllActiveTenants() {
		return repo.fetchActiveTenants(YesNo.Y);
	}

}
