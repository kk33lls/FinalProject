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
	public List<CareLog> displayCareLogs(String username) {
		return careLogRepo.findByUser_UserPlant(username);
	}

}
