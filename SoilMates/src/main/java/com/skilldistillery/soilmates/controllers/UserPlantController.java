package com.skilldistillery.soilmates.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.soilmates.entities.UserPlant;
import com.skilldistillery.soilmates.services.UserPlantService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class UserPlantController  {
  
	@Autowired
	UserPlantService userPlantService;
	
	@GetMapping("/plants")
	public List<UserPlant> loadUserPlants(Principal principal, HttpServletRequest req, HttpServletResponse res) {
		return userPlantService.displayUserPlants(principal.getName());
	}
	
	@GetMapping("/plants/{userPlantId}")
	public UserPlant show(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable("userPlantId") int userPlantId) {
		UserPlant showPlant = userPlantService.show(principal.getName(), userPlantId);
		if(showPlant == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return showPlant;
	}
	
	@PostMapping("plants/plantSpecies/{plantSpeciesId}")
	public UserPlant create(Principal principal, HttpServletRequest req, 
			HttpServletResponse res, @RequestBody UserPlant userPlant, 
			@PathVariable("plantSpeciesId") int speciesId) {

		try {
			userPlant = userPlantService.createUserPlant(principal.getName(), speciesId, userPlant);

			if (userPlant == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			} else {
				res.setStatus(HttpServletResponse.SC_CREATED);
				res.setHeader("Location", req.getRequestURL().append("/").append(userPlant.getId()).toString());
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
			userPlant = null;

		}
		return userPlant;
	}
	
	@DeleteMapping("plants/{userPlantId}")
	public void delete(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable("userPlantId") int userPlantId) {
		if (userPlantService.delete(principal.getName(), userPlantId)) {
			res.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204
		} else {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
		}
	}

	
	@PutMapping("plants/{userPlantId}")
	public UserPlant update(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable("userPlantId") int userPlantId, @RequestBody UserPlant userPlant) {
		try {
			userPlant = userPlantService.update(principal.getName(), userPlantId, userPlant);
			if (userPlant == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST); // 400
			userPlant = null;
		}
		return userPlant;
	}

}
