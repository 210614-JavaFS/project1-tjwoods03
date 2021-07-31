package com.revature.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.controllers.ReimbursementController;
import com.revature.controllers.UserController;

public class FrontControllerServlet extends HttpServlet{

	UserController userController = new UserController();
	ReimbursementController reimbController = new ReimbursementController();
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		
		response.setStatus(404);
		
		final String URL = request.getRequestURI().replace("/project1/", "");
				
		String[] urlSections = URL.split("/");
		
		switch(urlSections[0]) {
			case "login":
				if(request.getMethod().equals("POST")) {
					userController.login(request, response);
					request.getRequestURI().replace("/project1/", "/static/Project1-Employee.html");
				}
				break;
			case "reimb":
				if(urlSections.length == 1) {
					if(request.getMethod().equals("GET")) {
						reimbController.getAllReimbursements(response);
					}else if(request.getMethod().equals("POST")) {
						reimbController.addReimbursement(request, response);
					}
				}else if(urlSections.length == 2) {
					//reimbController.getFilteredReimbursement(response, urlSections[1].toLowerCase());
				}
				break;
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
