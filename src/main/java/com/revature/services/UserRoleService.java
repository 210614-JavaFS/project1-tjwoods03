package com.revature.services;

import com.revature.daos.UserRoleDAO;
import com.revature.daos.UserRoleDAOImpl;
import com.revature.models.UserRole;

public class UserRoleService {

	private static UserRoleDAO userRoleDao = new UserRoleDAOImpl();
	
	public UserRole getUserRoleByUserRoleID(int userRoleID) {
		return userRoleDao.getUserRoleByUserRoleID(userRoleID);
	}
	
}
