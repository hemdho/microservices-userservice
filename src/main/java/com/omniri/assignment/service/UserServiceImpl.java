package com.omniri.assignment.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.omniri.assignment.bean.User;
import com.omniri.assignment.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User findUserById(String id) {
		// TODO Auto-generated method stub
			Optional<User> userOptional=userRepo.findById(id);
			if(userOptional.isPresent()) return userOptional.get();
			return null;
	}

	@Override
	public User create(User user) {
		user.setEnabled(true);
		if(Objects.isNull(user.getId())){
			user.setId(UUID.randomUUID().toString());
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepo.save(user);
	}

	@Override
	public User findByUsername(String name) {
		return userRepo.findByUsername(name);
	}

	

	@Override
	public User delete(User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<User> findAll(int page, int limit) {
		Pageable pageable =PageRequest.of(page, limit);
		return userRepo.findAll(pageable);
		
	}

}
