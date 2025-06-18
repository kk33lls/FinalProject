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

import com.skilldistillery.soilmates.entities.Reminder;
import com.skilldistillery.soilmates.services.ReminderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class ReminderController {
	
	@Autowired
	ReminderService reminderService;
	
	@GetMapping("userPlants/{userPlantId}/reminders")
	public List<Reminder> loadCareLogs(Principal principal, HttpServletRequest req, HttpServletResponse res,
			@PathVariable("userPlantId") int userPlantId) {
		List<Reminder> foundReminders = reminderService.getReminders(principal.getName(), userPlantId);
		if (foundReminders == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return foundReminders;
	}
	@PostMapping("userPlants/{userPlantId}/reminders/careTypes/{careTypeId}")
	public Reminder create(Principal principal, HttpServletRequest req, HttpServletResponse res,
			@PathVariable("userPlantId") int userPlantId, @PathVariable("careTypeId") int careTypeId, @RequestBody Reminder reminder) {
		Reminder newReminder = reminderService.createReminder(principal.getName(), userPlantId, careTypeId, reminder);
		if (newReminder == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		} else {
			res.setStatus(HttpServletResponse.SC_CREATED);
			res.setHeader("Location", req.getRequestURL().append("/").append(newReminder.getId()).toString());

		}
		return newReminder;
	};
	
	@PutMapping("userPlants/{userPlantId}/reminders/{reminderId}/careTypes/{careTypeId}")
	public Reminder update(Principal principal, HttpServletRequest req, HttpServletResponse res,
			@PathVariable("userPlantId") int userPlantId, @PathVariable("careTypeId") int careTypeId, 
			@PathVariable("reminderId") int reminderId, @RequestBody Reminder updatedReminder) {
				
			try {
				 updatedReminder = reminderService.udpateReminder(principal.getName(), userPlantId, reminderId, careTypeId, updatedReminder);
				if (updatedReminder == null) {
					res.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
				
			} catch (Exception e) {
				res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				updatedReminder = null;
			}
		return updatedReminder;
				}
	@DeleteMapping("userPlants/{userPlantId}/reminders/{reminderId}/careTypes/{careTypeId}")
	public void delete(Principal principal,HttpServletRequest req, HttpServletResponse res, @PathVariable("userPlantId") int plantUserId, 
			@PathVariable("careTypeId") int careTypeId, @PathVariable("reminderId") int reminderId) {
		try {
			boolean deleted = reminderService.deleteReminder(principal.getName(), careTypeId, reminderId);
			if (deleted) {
				res.setStatus(HttpServletResponse.SC_NO_CONTENT);// 204
			} else {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);// 404
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);// 400
			e.printStackTrace();
		}

	}

}
