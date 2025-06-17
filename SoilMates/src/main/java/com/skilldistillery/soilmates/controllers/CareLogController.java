package com.skilldistillery.soilmates.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.soilmates.entities.CareLog;
import com.skilldistillery.soilmates.services.CareLogService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class CareLogController {

	@Autowired
 CareLogService careLogService;
	
	
	@GetMapping("/careLogs/{userPlantId}")
	public List<CareLog> loadCareLogs(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable("userPlantId") int userPlantId) {
		return  careLogService.displayCareLogs(principal.getName());
	}
	
}