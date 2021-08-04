package com.revature.web;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("application/json");
		
		response.setStatus(404);
		
		final String URL = request.getRequestURI().replace("/project1/", "");
		System.out.println(URL);
		
		String[] urlSections = URL.split("/");
		
		if(request.getMethod().equals("POST")) {
			switch(urlSections[0]) {
			case "signUp":
				try {
					userController.addUser(request, response);
				} catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException e) {
					e.printStackTrace();
				}
				break;
			case "login":
				if(urlSections.length == 1) {
					userController.login(request, response);
				}
				break;
			case "signOut":
				userController.logout(request, response);
				break;
			case "employee":
				if(urlSections[1].equals("submit")) {
					if(urlSections.length > 2) {
						reimbController.getReimbursementType(request, response);
					}else {
						reimbController.submitRequest(request, response);
					}
				}else if(urlSections[1].equals("pending")) {
					reimbController.pendingEmployeeRequest(request, response);
				}else if(urlSections[1].equals("past")) {
					reimbController.pastRequests(request,response);
				}
				break;
			case "manager":
				if(urlSections[1].equals("all")) {
					reimbController.getAllReimbursements(request, response);
				}else if(urlSections[1].equals("approve")) {
					reimbController.approveReimbursement(request, response, urlSections[3]);
				}else if(urlSections[1].equals("deny")) {
					reimbController.denyReimbursement(request, response, urlSections[3]);
				}else if(urlSections[1].equals("pending")) {
					reimbController.pendingRequest(request, response, urlSections[3]);
				}
				break;
			}
		}
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
}
