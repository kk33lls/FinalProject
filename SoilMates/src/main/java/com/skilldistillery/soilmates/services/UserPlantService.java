package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.UserPlant;

@Service
public interface UserPlantService {
	UserPlant createUserPlant(String username, int plantSpeciesId, UserPlant newUserPlant);

	List<UserPlant> displayUserPlants(String username);
	
	UserPlant show(String username, int userPlantId);

	boolean delete(String username, int userPlantId);
	
	UserPlant update(String username, int userPlantId, UserPlant userPlant);
}
