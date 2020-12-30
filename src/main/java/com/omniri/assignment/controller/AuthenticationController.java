package com.omniri.assignment.controller;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.omniri.assignment.config.JwtUtils;
import com.omniri.assignment.dto.JwtRequest;
import com.omniri.assignment.dto.JwtResponse;

@CrossOrigin
@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtTokenUtil;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/ping")
	@ResponseBody
	public String ping() {
		return " You have reached to us " + OffsetDateTime.now() ;
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)	
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception 
	{
		authenticate(authenticationRequest.getUsername(), 
		authenticationRequest.getPassword());
		final UserDetails userDetails = 
		 userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateJwtToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			e.printStackTrace();
			throw e;
		} catch (BadCredentialsException e) {
			e.printStackTrace();
			throw e;
		}
	}	
}
