package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.treggo.entities.TableMaster;

@Repository
public interface TableMasterRepository extends JpaRepository<TableMaster, Long>{

	public List<TableMaster> findByTenantCode(String tenant);
	
	@Query("from TableMaster where table_id = :table_id and tenantCode = :tenant")
	public TableMaster fetchByTableID(@Param("table_id") Long id, @Param("tenant") String tenant);
	
	@Query("from TableMaster where device_id = :device_id and tenantCode = :tenant")
	public TableMaster fetchByDeviceID(@Param("device_id") String device_id, @Param("tenant") String tenant);
	
}

