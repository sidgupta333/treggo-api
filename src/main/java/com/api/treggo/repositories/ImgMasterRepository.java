package com.api.treggo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.treggo.entities.ImgMaster;

public interface ImgMasterRepository extends JpaRepository<ImgMaster, Long> {

	@Query("from ImgMaster where img_id = :img_id")
	public ImgMaster fetchByImgID(@Param("img_id") Long id);
}
