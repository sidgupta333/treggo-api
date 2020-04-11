package com.api.treggo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.treggo.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	public Users findByUsernameAndTenantCode(String username, String tenantCode);
	public List<Users> findByTenantCode(String tenant);
	
}
