package com.omniri.assignment.service;

import org.springframework.data.domain.Page;

public interface CRUD <T> {

	T create(T t);
	T delete (T t);
	
	
	
}
