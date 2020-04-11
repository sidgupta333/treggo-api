package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.Tenants;
import com.api.treggo.enums.YesNo;

public interface TenantsRepository extends JpaRepository<Tenants, Long> {

	@Query("from Tenants where tenant_id = :tenant_id")
	public Tenants fetchByTenantID(@Param("tenant_id") Long id);
	
	@Query("from Tenants where tenant_code = :tenant_code")
	public Tenants fetchByTenantCode(@Param("tenant_code") String tenantCode);
	
	@Query("from Tenants where is_activated = :is_activated")
	public List<Tenants> fetchActiveTenants(@Param("is_activated") YesNo is_activated);
	
}
