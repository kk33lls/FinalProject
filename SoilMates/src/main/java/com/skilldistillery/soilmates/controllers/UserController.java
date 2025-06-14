package com.skilldistillery.soilmates.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.skilldistillery.soilmates.entities.PlantSpecies;
import com.skilldistillery.soilmates.entities.User;
import com.skilldistillery.soilmates.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin({ "*", "http://localhost/" })
@RequestMapping("api")
public class UserController {
	@Autowired
	UserService userService;
	
	
	@PutMapping("users/{id}")
	public User update(Principal principal, HttpServletRequest req, HttpServletResponse res, @PathVariable("id") int userId,
			@RequestBody User user) {

		try {
			user = userService.update(principal.getName(), user, userId);

			if (user == null) {
				res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			}
		} catch (Exception e) {
			res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			e.printStackTrace();
		}
		return user;
	}
	

	@GetMapping("users/{id}")
	User findById(@PathVariable("id") int id, HttpServletResponse res) {
		User user = userService.findUserById(id);
		if (user == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
		}
		return user;
	}

}
