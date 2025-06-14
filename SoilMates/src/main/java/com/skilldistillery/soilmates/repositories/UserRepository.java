package com.skilldistillery.soilmates.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.soilmates.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);
	User findById(int id);
}
