package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.BannerMaster;

public interface BannerRepository extends JpaRepository<BannerMaster, Long> {

	@Query("from BannerMaster where banner_id = :banner_id and tenantCode = :tenant")
	public BannerMaster fetchByBannerID(@Param("banner_id") Long id, @Param("tenant") String tenant);
	
	public List<BannerMaster> findByTenantCode(String tenant);
}
