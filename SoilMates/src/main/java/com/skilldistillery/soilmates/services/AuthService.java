package com.skilldistillery.soilmates.services;

import com.skilldistillery.soilmates.entities.User;

public interface AuthService {
	public User register(User user);
	public User getUserByUsername(String username);
}
