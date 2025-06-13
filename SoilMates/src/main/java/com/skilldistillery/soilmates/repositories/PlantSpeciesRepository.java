package com.skilldistillery.soilmates.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.skilldistillery.soilmates.entities.PlantSpecies;

public interface PlantSpeciesRepository extends JpaRepository<PlantSpecies, Integer> {
 List<PlantSpecies> searchByCommonNamesContainsOrSpeciesContainsOrGenusContains(String commonNames, String species, String Genus);
}
