package com.omniri.assignment.service;

import com.omniri.assignment.bean.Role;

public interface RoleService extends CRUD<Role> {

	Role create(String roleName,String roleCode);
	Role findByName(String roleName);
	
}
