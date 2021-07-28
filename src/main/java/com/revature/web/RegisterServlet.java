
package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.daos.UserDAO;
import com.revature.daos.UserDAOImpl;
import com.revature.models.User;

public class RegisterServlet extends HttpServlet{

	private static UserDAO userDao = new UserDAOImpl();
	
	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        response.setContentType("text/html;charset=UTF-8");
	        PrintWriter out = response.getWriter();
		
	        User user = new User();
	        
	        user.setFirstName(request.getParameter("first-name"));
	        user.setLastName(request.getParameter("last-name"));
	        user.setUserName(request.getParameter("username"));
	        user.setEmail(request.getParameter("email"));
	        user.setPass(request.getParameter("password"));
	        user.setUserRole(1);
	        
	        userDao.addUser(user);
	        
	    }
	
	
}
