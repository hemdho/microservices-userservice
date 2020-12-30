package com.omniri.assignment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.omniri.assignment.bean.User;
import com.omniri.assignment.dto.GenericPageResponse;
import com.omniri.assignment.dto.UserDto;
import com.omniri.assignment.dto.UserResource;
import com.omniri.assignment.mapper.UserMapper;
import com.omniri.assignment.repository.UserRepository;
import com.omniri.assignment.service.UserService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.hateoas.Link;
@CrossOrigin
@RestController
@RequestMapping(value="/users")
public class UserController {

	@Autowired
	UserMapper userMapper;
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	
	

	
	@PostMapping
	@ResponseBody
	@ResponseStatus(value = HttpStatus.CREATED)
	public UserDto registerUser(@RequestBody UserDto  user_) {
	
		 User user=userService.create(userMapper.userDtoToUser(user_));
		 return userMapper.userToUserDto(user);
		 
	}
	
	@GetMapping("/{id}")
	@ResponseBody
	public EntityModel<UserDto> getUser(@PathVariable String id) {
		User user =userService.findUserById(id);
		UserDto userDto=userMapper.userToUserDto(user);
		 EntityModel<UserDto> entityModel = EntityModel.of(userDto);
		 
	        Link link= WebMvcLinkBuilder.linkTo(
	                methodOn(this.getClass()).getUsers(1,10)).withRel("all-users");
	        Link self= WebMvcLinkBuilder.linkTo(methodOn(this.getClass()).getUser(id)).withSelfRel();
	        entityModel.add(self);
	        entityModel.add(link);
	        return entityModel;
		
	}
	
	@GetMapping
	@ResponseBody
	@PreAuthorize("hasAnyAuthority('BranchManager','Admin')")
	public GenericPageResponse<UserDto> getUsers(@RequestParam int page, @RequestParam int limit){
		Page<User> users=userService.findAll(page, limit);
		return userMapper.toPageResponse(users);
		
	}
	
}
