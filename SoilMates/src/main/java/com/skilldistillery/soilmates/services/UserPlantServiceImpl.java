package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.PlantSpecies;
import com.skilldistillery.soilmates.entities.User;
import com.skilldistillery.soilmates.entities.UserPlant;
import com.skilldistillery.soilmates.repositories.PlantSpeciesRepository;
import com.skilldistillery.soilmates.repositories.UserPlantRepository;
import com.skilldistillery.soilmates.repositories.UserRepository;

@Service
public class UserPlantServiceImpl implements UserPlantService {
	@Autowired
	UserPlantRepository userPlantRepo;

	@Autowired
	UserRepository userRepo;

	@Autowired
	PlantSpeciesRepository plantSpeciesRepo;

	@Override
	public UserPlant createUserPlant(String username, int plantSpeciesId, UserPlant newUserPlant) {
		User user = userRepo.findByUsername(username);
		PlantSpecies plantSpecies = plantSpeciesRepo.findById(plantSpeciesId);
		if (user != null && plantSpecies != null) {
			newUserPlant.setUser(user);
			newUserPlant.setPlantSpecies(plantSpecies);
			return userPlantRepo.saveAndFlush(newUserPlant);
		}
		return null;
	}

	@Override
	public List<UserPlant> displayUserPlants(String username) {
		return userPlantRepo.findByUser_UsernameAndEnabledTrue(username);
	}

	@Override
	public boolean delete(String username, int userPlantId) {
		UserPlant plantToDelete = userPlantRepo.findByIdAndUser_UsernameAndEnabledTrue(userPlantId, username);
		if (plantToDelete != null) {
			plantToDelete.setEnabled(false);
			userPlantRepo.saveAndFlush(plantToDelete);
			return true;
		}
		return false;
	}

	@Override
	public UserPlant show(String username, int userPlantId) {
		return userPlantRepo.findByIdAndUser_UsernameAndEnabledTrue(userPlantId, username);
	}

	@Override
	public UserPlant update(String username, int userPlantId, UserPlant userPlant) {
		UserPlant managedPlant = userPlantRepo.findByIdAndUser_UsernameAndEnabledTrue(userPlantId, username);
		if (managedPlant != null) {
			managedPlant.setAcquiredDate(userPlant.getAcquiredDate());
			managedPlant.setAlive(userPlant.getAlive());
			managedPlant.setImageUrl(userPlant.getImageUrl());
			managedPlant.setLocation(userPlant.getLocation());
			managedPlant.setName(userPlant.getName());
			managedPlant.setNotes(userPlant.getNotes());

			userPlantRepo.saveAndFlush(managedPlant);

			return managedPlant;
		}
		return null;
	}
}
