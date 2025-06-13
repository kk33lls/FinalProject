package com.skilldistillery.soilmates.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.skilldistillery.soilmates.entities.PlantSpecies;
import com.skilldistillery.soilmates.services.PlantSpeciesService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class PlantSpeciesController {
	@Autowired
	private PlantSpeciesService speciesService;

	@GetMapping("plantSpecies/search/{keyword}")
	List<PlantSpecies> searchSpeciesByKeyword(@PathVariable("keyword") String keyword) {
		return speciesService.keywordSearch(keyword);
	}

	@GetMapping("plantSpecies")
	List<PlantSpecies> findAll() {
		return speciesService.findAll();
	}

	@GetMapping("plantSpecies/{id}")
	PlantSpecies findById(@PathVariable("id") int id, HttpServletResponse res) {
		PlantSpecies plant = speciesService.findById(id);
		if (plant == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);

		}
		return plant;
	}
}
