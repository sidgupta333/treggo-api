package com.api.treggo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.api.treggo.entities.TableMaster;

@Repository
public interface TableMasterRepository extends JpaRepository<TableMaster, Long>{

	@Query("from TableMaster where table_id = :table_id")
	public TableMaster fetchByTableID(@Param("table_id") Long id);
}

