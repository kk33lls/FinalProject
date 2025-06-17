package com.skilldistillery.soilmates.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	@GetMapping("userPlants/{userPlantId}/careLogs")
	public List<CareLog> loadCareLogs(Principal principal, HttpServletRequest req, HttpServletResponse res,
			@PathVariable("userPlantId") int userPlantId) {
		List<CareLog> foundCareLogs = careLogService.displayCareLogs(principal.getName(), userPlantId);
		if (foundCareLogs == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return foundCareLogs;
	}

	@PostMapping("userPlants/{userPlantId}/careLogs")
	public CareLog create(Principal principal, HttpServletRequest req, HttpServletResponse res,
			@PathVariable("userPlantId") int userPlantId, @RequestBody CareLog careLog) {
		CareLog createdLog = careLogService.createCareLog(principal.getName(), userPlantId, careLog);
		if (createdLog == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else {
			res.setStatus(HttpServletResponse.SC_CREATED);
			res.setHeader("Location", req.getRequestURL().append("/").append(createdLog.getId()).toString());

		}
		return createdLog;
	};
	@PutMapping("userPlants/{userPlantId}/careLogs/{careLogId}")
	public CareLog update(Principal principal, HttpServletRequest req, HttpServletResponse res,
			@PathVariable("userPlantId") int userPlantId, @PathVariable("careLogId") int careLogId, @RequestBody CareLog careLog) {
				
			try {
				careLogService.updateCareLog(principal.getName(), userPlantId, careLogId, careLog);
				if (careLog == null) {
					res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
				
			} catch (Exception e) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				careLog = null;
			}
		return careLog;
				}
}
