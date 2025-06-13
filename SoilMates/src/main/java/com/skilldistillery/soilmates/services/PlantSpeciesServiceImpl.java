package com.skilldistillery.soilmates.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.PlantSpecies;
import com.skilldistillery.soilmates.repositories.PlantSpeciesRepository;

@Service
public class PlantSpeciesServiceImpl implements PlantSpeciesService {
	
	@Autowired
	private PlantSpeciesRepository speciesRepo;

	@Override
	public List<PlantSpecies> keywordSearch(String keyword) {
		return speciesRepo.searchByCommonNamesContainsOrSpeciesContainsOrGenusContains(keyword, keyword, keyword);
	}

}
