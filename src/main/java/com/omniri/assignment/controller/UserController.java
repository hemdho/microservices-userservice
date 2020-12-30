package com.omniri.assignment.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.omniri.assignment.bean.User;
import com.omniri.assignment.dto.GenericPageResponse;
import com.omniri.assignment.dto.UserDto;
import com.omniri.assignment.mapper.UserMapper;
import com.omniri.assignment.repository.UserRepository;
import com.omniri.assignment.service.UserService;

@CrossOrigin
@RestController
public class UserController {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	
	

	
	@PostMapping("/users")
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public UserDto registerUser(@RequestBody UserDto  user_) {
	
		 User user=userService.create(userMapper.userDtoToUser(user_));
		 return userMapper.userToUserDto(user);
		 
	}
	
	@GetMapping("/users/{id}")
	@ResponseBody
	public UserDto getUser(@PathVariable String id) {
		User user =userService.findUserById(id);
		return userMapper.userToUserDto(user);
		
	}
	
	@GetMapping("/users")
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('BranchManager','Admin')")
	public GenericPageResponse<UserDto> getUsers(@RequestParam int page, @RequestParam int limit){
		Page<User> users=userService.findAll(page, limit);
		return userMapper.toPageResponse(users);
		
	}
	
}
