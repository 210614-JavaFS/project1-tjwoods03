package com.revature.daos;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {

	List<User> findAllUsers();

	User findByUsersName(String name);

	boolean updateUser(User user);

	boolean addUser(User user);
	
	int checkValidAccount(User user);

	
}
