package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.CareLog;
import com.skilldistillery.soilmates.repositories.CareLogRepository;

@Service
public class CareLogServiceImpl implements CareLogService {
	
	@Autowired
	CareLogRepository careLogRepo;

	@Override
	public List<CareLog> displayCareLogs(String username, int userPlantId) {
		if(careLogRepo.existsByUserPlant_User_UsernameAndUserPlant_Id(username, userPlantId)) {
			return careLogRepo.findByUserPlant_User_UsernameAndUserPlant_Id(username, userPlantId);
		}
		return null;
	}

}
