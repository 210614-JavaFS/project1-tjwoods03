package com.revature.controllers;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class UserController {

	private static UserDAO userDao = new UserDAOImpl();
	
	public int login(User user) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		user = userDao.findByUsersName(user.getUserName());
		
		String pass = encryptPass(user.getPass());
		
		//if statement for if user input equals username and pass return 1(success) else return 0(failure)
		
		return 0;
	}
	
	
	//Implementing PBKDF2 to encrypt the password
	public String encryptPass(String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
		User user = new User();
		pass = user.getPass();
		
		
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		KeySpec spec = new PBEKeySpec(pass.toCharArray(), salt, 65536, 128);
		//returns a  secret key factory that converts secret keys of the specified algorithm in this case its PBKDF2WithHmacSHA1
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		
		byte[] hash = factory.generateSecret(spec).getEncoded();
		
		return hash.toString();
		
	}
	
}
