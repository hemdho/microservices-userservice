package com.omniri.assignment.mapper;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.omniri.assignment.bean.Role;
import com.omniri.assignment.bean.User;
import com.omniri.assignment.dto.GenericPageResponse;
import com.omniri.assignment.dto.UserDto;
import com.omniri.assignment.service.RoleService;

@Service
public class UserMapper {

	@Autowired
	RoleService roleService;
	
	public User userDtoToUser(UserDto userDto) {
		User user= new User();
		user.setDateOfBirth(userDto.getDateOfBirth());
		user.setEnabled(userDto.isEnabled());
		user.setGender(userDto.getGender());
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setPhoneNumber(userDto.getPhoneNumber());
		Role role = roleService.findByName(userDto.getRole());
		user.setRole(role);
		user.setUsername(userDto.getUsername());
		return user;
	}
	
	public GenericPageResponse<UserDto> toPageResponse(Page<User> page){
		GenericPageResponse<UserDto> res=new GenericPageResponse<>();
		if(page.getTotalPages()>0) {
			res.setData(page.getContent().stream().map(user->userToUserDto(user)).collect(Collectors.toList()));
			res.setLimit(page.getSize());
			res.setPage(page.getNumber());
			res.setTotalPages(page.getTotalPages());
			res.setTotalRecords(page.getTotalElements());
			
		}
		
		return res;
	}
	
	public UserDto userToUserDto(User user) {
		UserDto userDto= new UserDto();
		userDto.setDateOfBirth(user.getDateOfBirth());
		userDto.setEnabled(user.isEnabled());
		userDto.setGender(user.getGender());
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		//userDto.setPassword(user.getPassword());
		userDto.setPhoneNumber(user.getPhoneNumber());
		userDto.setRole(user.getRole().getName());
		userDto.setUsername(user.getUsername());
		return userDto;
		
	}
}
