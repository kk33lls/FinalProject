package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.CareLog;
import com.skilldistillery.soilmates.entities.UserPlant;
import com.skilldistillery.soilmates.repositories.CareLogRepository;
import com.skilldistillery.soilmates.repositories.UserPlantRepository;

@Service
public class CareLogServiceImpl implements CareLogService {

	@Autowired
	CareLogRepository careLogRepo;
	@Autowired
	UserPlantRepository userPlantRepo;

	@Override
	public List<CareLog> displayCareLogs(String username, int userPlantId) {
		if (careLogRepo.existsByUserPlant_User_UsernameAndUserPlant_Id(username, userPlantId)) {
			return careLogRepo.findByUserPlant_User_UsernameAndUserPlant_Id(username, userPlantId);
		}
		return null;
	}

	@Override
	public CareLog createCareLog(String username, int userPlantId, CareLog careLog) {
		if (!careLogRepo.existsByUserPlant_User_UsernameAndUserPlant_Id(username, userPlantId)) {

			return null;
		}
		UserPlant plant = userPlantRepo.findByIdAndUser_Username(userPlantId, username);
		careLog.setUserPlant(plant);
		return careLogRepo.saveAndFlush(careLog);

	}
}