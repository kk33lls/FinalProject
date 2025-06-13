package com.skilldistillery.soilmates.services;

import java.util.List;

import com.skilldistillery.soilmates.entities.PlantSpecies;

public interface PlantSpeciesService {
	List<PlantSpecies> keywordSearch(String keyword);
}
