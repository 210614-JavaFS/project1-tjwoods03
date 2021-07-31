package com.revature.services;

import java.util.List;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserService {

	private static UserDAO userDao = new UserDAOImpl();
	
	public List<User> getAllUsers(){
		return userDao.findAllUsers();
	}
	
	public User getUser(String username) {
		return userDao.findByUsersName(username);
	}
	
	public boolean addUser(User user) {
		return userDao.addUser(user);
	}
	
	public int login(User user) {
		return userDao.checkValidAccount(user);
	}
	
}
