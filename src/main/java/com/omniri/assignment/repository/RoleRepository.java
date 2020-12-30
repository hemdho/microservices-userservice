package com.omniri.assignment.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.omniri.assignment.bean.Role;

public interface RoleRepository extends PagingAndSortingRepository<Role, String> {

	Role findByName(String name);
}
