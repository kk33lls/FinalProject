package com.skilldistillery.soilmates.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.skilldistillery.soilmates.entities.User;
import com.skilldistillery.soilmates.repositories.UserRepository;

public class AuthServiceImpl implements AuthService {
	@Autowired
	private UserRepository ur;
	
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public User register(User user) {
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		return null;
	}

}
