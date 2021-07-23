package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontControllerServlet {
	

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("application/json");
	
		response.setStatus(404);//Override tomcats 200 default for malformed requests
		
		final String URL = request.getRequestURI().replace("/project1-tjwoods03/", "");//strips out the base URL info
		
		System.out.println(URL);
		
		String[] urlSections = URL.split("/");
		
		switch(urlSections[0]) {
			case "user":
				
				break;
			case "userRole":
				
				break;
			case "reimburement":
				
				break;
			case "reimburementStatus":
				
				break;
			case "reimburementType":
				
				break;
		}
		
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		doGet(request, response);
	}
	

}
