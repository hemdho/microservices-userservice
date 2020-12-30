package com.omniri.assignment.startup;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.omniri.assignment.bean.Role;
import com.omniri.assignment.bean.User;
import com.omniri.assignment.service.RoleService;
import com.omniri.assignment.service.UserService;
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		createRoleIfNotExist("Customer", "Customer");
		createRoleIfNotExist("Admin", "Admin");
		createRoleIfNotExist("BranchManager", "BranchManager");
		createUserIfNotExist("hemant", "hemant", "Admin");
		
		
		
		
	}

	private void createRoleIfNotExist(String roleName,String code ) {
		Role role=roleService.findByName(roleName);
		if(Objects.isNull(role)) roleService.create(roleName, code);
	}
	private void createUserIfNotExist(String username,String password,String role_) {
		User tempUser=userService.findByUsername(username);
		User user = new User();		
		user.setPassword(password);
		user.setUsername(username);
		Role role = roleService.findByName(role_);		
		user.setRole(role);
		if(Objects.isNull(tempUser)) userService.create(user);
	}
}
