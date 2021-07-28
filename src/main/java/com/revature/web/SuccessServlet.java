package com.revature.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuccessServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		//This is a flag to the browser about what format the response body is in.
		response.setContentType("text/html");
		
		PrintWriter printWriter = response.getWriter();
		
		String username = request.getParameter("userId");
		
		printWriter.print("<h2> Welcome "+username+", you successfully logged in!</h2>");
		printWriter.print("<a href='logout'>Click here to logout!</a>");
	}
	
}
