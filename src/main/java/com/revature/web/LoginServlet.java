package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.UserController;
import com.revature.models.User;

public class LoginServlet extends HttpServlet{
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		User user = new User();
		
		String username = request.getParameter("username");
		String pass = request.getParameter("password");
		
		user.setUserName(username);
		user.setPass(pass);
		

		RequestDispatcher requestDispatcher = null;
		PrintWriter printWriter = response.getWriter();
		
		try {
			if(UserController.login(user) == 1) {
				HttpSession session = request.getSession();
				session.setAttribute("username", username);
				
				requestDispatcher = request.getRequestDispatcher("success");
				requestDispatcher.forward(request, response);
			}else {
				requestDispatcher = request.getRequestDispatcher("Project1-Login.html");
				requestDispatcher.include(request, response);
				printWriter.print("<span style='color:red; text-align:center'>Invalid Username or Password</span>");
			}
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}