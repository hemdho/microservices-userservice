package com.omniri.assignment.service;

import org.springframework.data.domain.Page;

import com.omniri.assignment.bean.User;

public interface UserService extends CRUD<User> {

	
	User findUserById(String id);
	User findByUsername(String username);
	Page<User> findAll(int page, int limit);
}
