package com.omniri.assignment.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class AuthTokenFilter extends OncePerRequestFilter {

	@Autowired
  private JwtUtils jwtUtils;

	
  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  
  @Autowired
  AuthenticationManager authenticationManager;
  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

 
  
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
    	System.out.println(" Readed heer");
      String jwt = parseJwt(request);
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
       
        System.out.println(jwt);
        System.out.println(username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        jwtUtils.validateToken(username,userDetails,jwt);
        	
        	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
        			userDetails.getAuthorities());
        	
        	
        		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        		System.out.println("    ,,,,,,,,,,,,,,,,,,,,,,,,,,");
        		SecurityContextHolder.getContext().setAuthentication(authentication);
        		System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        
      }
    } catch (Exception e) {
    	e.printStackTrace();
    	throw e;
     // logger.error("Cannot set user authentication: {}", e);
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");
    System.out.println(" Auth Header " + headerAuth);
    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7, headerAuth.length());
    }

    return null;
  }
 
}
