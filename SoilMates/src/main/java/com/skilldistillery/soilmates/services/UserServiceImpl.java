package com.skilldistillery.soilmates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.User;
import com.skilldistillery.soilmates.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	@Override
	public User update(String username, User user, int userId) {
		User updateUser = userRepo.findByUsername(username);

		if (user != null) {
			User managedUser = userRepo.findById(userId);
			managedUser.setFirstName(user.getFirstName());
			managedUser.setLastName(user.getLastName());
			managedUser.setBiography(user.getBiography());
			managedUser.setEmail(user.getEmail());
			managedUser.setImageUrl(user.getImageUrl());

			userRepo.saveAndFlush(managedUser);
			return managedUser;
		}
		return null;
	}
	@Override
	public User findUserById(int userId) {
		return userRepo.findById(userId);
	}
}
