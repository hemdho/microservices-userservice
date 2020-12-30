package com.omniri.assignment.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.omniri.assignment.bean.Role;
import com.omniri.assignment.repository.RoleRepository;

@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	RoleRepository roleRepo;
	
	
	
	
	
	@Override
	public Role create(Role role) {
		role.setId(UUID.randomUUID().toString());
		
		return roleRepo.save(role);
	}

	@Override
	public Role delete(Role t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Role create(String roleName, String roleCode) {
        Role role = new Role();
        role.setName(roleName);
        role.setRoleCode(roleCode);
		return create(role);
	}

	@Override
	public Role findByName(String roleName) {
		return roleRepo.findByName(roleName);
	}
	

}
