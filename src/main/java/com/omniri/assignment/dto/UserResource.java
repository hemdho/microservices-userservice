package com.omniri.assignment.dto;



//import org.springframework.hateoas.ResourceSupport;

import com.omniri.assignment.bean.User;
import com.omniri.assignment.controller.UserController;

public class UserResource {
   private final User user;
   
   public UserResource(User user) {
	   this.user=user;
	   //add(linkTo(UserController.class).withRel("user"));
	  // add(linkTo(methodOn(UserController.class).get(user.getId()).withSelfRel()));
	   
   }

public User getUser() {	
	return user;
}
}
