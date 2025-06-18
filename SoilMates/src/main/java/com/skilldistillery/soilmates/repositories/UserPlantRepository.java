package com.skilldistillery.soilmates.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.soilmates.entities.UserPlant;

public interface UserPlantRepository extends JpaRepository<UserPlant, Integer> {
	List<UserPlant> findByUser_UsernameAndEnabledTrue(String username);
	UserPlant findByIdAndUser_UsernameAndEnabledTrue(int userPlantId, String username);
}
