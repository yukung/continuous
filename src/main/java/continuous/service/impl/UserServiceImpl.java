package continuous.service.impl;

import continuous.dao.UserDao;
import continuous.entity.User;
import continuous.service.UserService;

public class UserServiceImpl implements UserService {
	
	private UserDao userDao;
	
	
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public User findByUserName(String name) {
		return null;
	}
	
}
