package com.revature.services;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;

import com.revature.controllers.UserController;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserService {

	private static UserDAO userDao = new UserDAOImpl();
	private static UserController userController = new UserController();
	
	public List<User> getAllUsers(){
		return userDao.findAllUsers();
	}
	
	public User getUser(String username) {
		return userDao.findByUsersName(username);
	}
	
	public boolean addUser(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		user.setPass(UserController.encryptPass(user.getPass()));
		if(user.getPass().length() > 200){
			System.out.println("Password Too Long");
			return false;
		}
		return userDao.addUser(user);
	}
	
	public User login(User user) {
		String originalPassword = UserController.encryptPass(user.getPass());
		if(originalPassword.equals(user.getPass())) {
			return userDao.findByUsersName(user.getUserName());
		}
		return null;
		
	}
	
}
