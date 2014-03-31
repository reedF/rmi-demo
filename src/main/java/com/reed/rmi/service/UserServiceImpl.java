package com.reed.rmi.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.reed.rmi.domain.User;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	/* (non-Javadoc)
	 * @see com.reed.rmi.service.UserService#findById(java.lang.Long)
	 */
	public User findById(Long id) {
		User u = new User();
		u.setId(id);
		u.setName("中文");
		return u;
	}

	/* (non-Javadoc)
	 * @see com.reed.rmi.service.UserService#save(com.reed.rmi.domain.User)
	 */
	public int save(User u) {
		int r = 0;
		if (u != null) {
			logger.info(u.getName());
		}
		return r;
	}
}
