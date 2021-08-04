package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
		User user = getJsonEmployee(request);
		user = userService.login(user);
		if (user != null) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userRoleID", user.getUserRoleID());
			String json = objectMapper.writeValueAsString(user);
			response.getWriter().print(json);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
		
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession(false);
		if(session!=null) {
			session.invalidate();
			response.setStatus(404);
		}
	}
	
	

	public void addUser(HttpServletRequest request, HttpServletResponse response) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException{
		User user = getJsonEmployee(request);
		
		if (userService.addUser(user)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("userName", user.getUserName());
			session.setAttribute("userRoleID", "Employee");
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	
	private User getJsonEmployee(HttpServletRequest request) {
		String body;
		
		try {
			BufferedReader reader = request.getReader();
		
			StringBuilder stringBuilder = new StringBuilder();
		
			String line = reader.readLine();
		
			while (line != null) {
				stringBuilder.append(line);
				line = reader.readLine();
			}
		
			body = new String(stringBuilder);
			
			return objectMapper.readValue(body, User.class);

		} catch(IOException e) {
			System.err.println("Readding Request Fail" + e.getMessage());
		}

		return null;
	}

	public static String encryptPass(String pass){
		StringBuilder hexadecimalString = null;
		byte[] byteArray;
		
		try {
			//convert input string into a SHA encrypted byte array
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byteArray = messageDigest.digest(pass.getBytes(StandardCharsets.UTF_8));
			
			//Now convert that byte array into hexadecimal
			hexadecimalString = new StringBuilder(2 * byteArray.length);
			for(byte i : byteArray) {
				//Hex value of a byte
				String hexValue = Integer.toHexString(0xff & i);
				
				//If the hex value is only a single digit, add a 0
				if(hexValue.length() == 1) 
					hexadecimalString.append('0');
				
				//Add the hex value to the entire string of hex values
				hexadecimalString.append(hexValue);
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return hexadecimalString.toString();
		
	}

	public boolean validatePassword(String pass, String password) {
		// TODO Auto-generated method stub
		return false;
	}
}
