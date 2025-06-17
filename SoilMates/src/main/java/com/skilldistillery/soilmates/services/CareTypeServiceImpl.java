package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.CareType;
import com.skilldistillery.soilmates.repositories.CareTypeRepository;
@Service
public class CareTypeServiceImpl implements CareTypeService {
	@Autowired
	CareTypeRepository careTypeRepo;

	@Override
	public List<CareType> getCareTypes() {
		return careTypeRepo.findAll();
	}

}
