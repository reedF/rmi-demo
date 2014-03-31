package com.reed.rmi.service;

import com.reed.rmi.domain.User;

/**
 * rmi service test demo
 * 
 * @author reed
 * 
 */
public interface UserService {

	User findById(Long id);

	int save(User u);

}