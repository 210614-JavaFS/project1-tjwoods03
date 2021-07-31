package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;
import com.revature.services.UserService;

public class UserController {

	private static UserDAO userDao = new UserDAOImpl();
	private static UserService userService = new UserService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		
		if(userService.login(user) == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUserName());
			response.setStatus(201);
		}else {
			response.setStatus(406);
		}
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.getSession().invalidate();
		response.setStatus(404);
	}
	
	

	public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException{
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while(line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		User user = objectMapper.readValue(body, User.class);
		
		
		if(userService.addUser(user)) {
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getUserName());
			response.setStatus(201);
		}else {
			response.setStatus(406);
		}
	}
	
	
	//Implementing PBKDF2 to encrypt the password
	public static String encryptPass(String pass) throws NoSuchAlgorithmException, InvalidKeySpecException {
		
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
