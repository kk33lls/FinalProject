package com.skilldistillery.soilmates.repositories;

import java.util.List;

import com.skilldistillery.soilmates.entities.CareLog;

public interface CareLogRepository {
 List<CareLog> findByUser_UserPlant(String username);
}
