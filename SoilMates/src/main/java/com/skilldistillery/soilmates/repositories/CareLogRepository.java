package com.skilldistillery.soilmates.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.soilmates.entities.CareLog;

public interface CareLogRepository extends JpaRepository<CareLog, Integer>{
 boolean existsByUserPlant_User_UsernameAndUserPlant_Id(String username, int userPlantId);
 List<CareLog> findByUserPlant_User_UsernameAndUserPlant_Id(String username, int userPlantId);
}
