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
			return careLogRepo.findByUserPlant_User_UsernameAndUserPlant_IdAndEnabledTrue(username, userPlantId);
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
		careLog.setEnabled(true);
		return careLogRepo.saveAndFlush(careLog);

	}

	@Override
	public CareLog updateCareLog(String username, int userPlantId, int careLogId, CareLog careLog) {
		CareLog managedCareLog = careLogRepo.findByUserPlant_User_UsernameAndUserPlant_IdAndIdAndEnabledTrue(username,
				userPlantId, careLogId);

		if (managedCareLog != null) {
			managedCareLog.setCareType(careLog.getCareType());
			managedCareLog.setNotes(careLog.getNotes());
			managedCareLog.setCareDate(careLog.getCareDate());

			careLogRepo.saveAndFlush(managedCareLog);
			return managedCareLog;
		}
		return null;
	}

	@Override
	public CareLog getCareLog(String username, int userPlantId, int careLogId) {
		return careLogRepo.findByUserPlant_User_UsernameAndUserPlant_IdAndIdAndEnabledTrue(username, userPlantId,
				careLogId);
	}

	@Override
	public boolean delete(String username, int userPlantId, int careLogId) {

		CareLog careLog = careLogRepo.findByUserPlant_User_UsernameAndUserPlant_IdAndIdAndEnabledTrue(username,
				userPlantId, careLogId);
		if (careLog == null) {
			return false;
		}
		careLog.setEnabled(false);
		careLogRepo.saveAndFlush(careLog);
		return true;
	}
}