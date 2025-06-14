package com.skilldistillery.soilmates.services;

import org.springframework.stereotype.Service;

import com.skilldistillery.soilmates.entities.User;
@Service
public interface UserService {
	User update(String username, User user, int userId);
	User findUserById(int userId);
}
