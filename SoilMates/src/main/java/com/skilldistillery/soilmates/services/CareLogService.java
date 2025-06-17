package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.CareLog;

@Service
public interface CareLogService {
List<CareLog> displayCareLogs(String username, int userPlantId);
}
