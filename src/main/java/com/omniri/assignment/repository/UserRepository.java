package com.omniri.assignment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.omniri.assignment.bean.User;

public interface UserRepository extends PagingAndSortingRepository<User, String>{

	User findByUsername(String name);
}
