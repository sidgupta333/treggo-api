package com.api.treggo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.treggo.entities.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

	public Users findByUsername(String username);
	
}
