package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.CareLog;

@Service
public interface CareLogService {
List<CareLog> displayCareLogs(String username, int userPlantId);

CareLog createCareLog (String username, int userPlantId, CareLog careLog);

CareLog updateCareLog(String username, int userPlantId, int careLogId, CareLog careLog);

CareLog getCareLog(String username, int userPlantId, int careLogId);
}


