package com.skilldistillery.soilmates.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.soilmates.entities.CareType;
import com.skilldistillery.soilmates.services.CareTypeService;



@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class CareTypeController {
	@Autowired
	CareTypeService careTypeService;
	
	@GetMapping("careTypes")
	public List<CareType> loadCareLogs() {
		return careTypeService.getCareTypes();
	}

}
