package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.CareType;
@Service
public interface CareTypeService {
	List<CareType> getCareTypes();
	

}
