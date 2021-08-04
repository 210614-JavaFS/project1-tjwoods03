package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.models.ReimbursementType;
import com.revature.services.ReimbursementService;

public class ReimbursementController {

	private static ReimbursementService reimbService = new ReimbursementService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void getAllReimbursements(HttpServletRequest request, HttpServletResponse response) throws IOException{
		List<Reimbursement> list = reimbService.findAllReimbursements();
		
		String json = objectMapper.writeValueAsString(list);
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
	}


	public void getReimbursementType(HttpServletRequest request, HttpServletResponse response) {
		List<ReimbursementType> reimbType = reimbService.getReimbursementType();
		
		if (reimbType != null) {
			setJSONReimbType(reimbType, response);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void setJSONReimbursment(List<Reimbursement> reimb, HttpServletResponse response) {
		try {
			String json = objectMapper.writeValueAsString(reimb);
			response.getWriter().print(json);
		} catch (IOException e) {
			System.err.println("Writing Response Fail" + e.getMessage());
		}
	}

	private void setJSONReimbType(List<ReimbursementType> reimbType, HttpServletResponse response) {
		try {
			String json = objectMapper.writeValueAsString(reimbType);
			response.getWriter().print(json);
		} catch (IOException e) {
			System.err.println("Writing Response Fail" + e.getMessage());
		}
	}
	
	public Reimbursement getJSONReimbursment(HttpServletRequest request) {

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
			
			return objectMapper.readValue(body, Reimbursement.class);

		} catch(IOException e) {
			System.err.println("Readding Request Fail" + e.getMessage());
		}

		return null;
	}

	public void submitRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		Reimbursement reimb = getJSONReimbursment(request);
		
		if (reimbService.addReimbursement(reimb, session.getAttribute("userName").toString())) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}

	public void pendingEmployeeRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		List<Reimbursement> reimb = reimbService.getPastRequest(session.getAttribute("ers_username").toString());
		
		if (reimb != null) {
			setJSONReimbursment(reimb, response);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
		
	}

	public void pastRequests(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		
		List<Reimbursement> reimb = reimbService.getPastRequest(session.getAttribute("ers_username").toString());
		
		if (reimb != null) {
			setJSONReimbursment(reimb, response);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}

	public void approveReimbursement(HttpServletRequest request, HttpServletResponse response, String reimb_id) {
		HttpSession session = request.getSession(false);
		String ers_username = session.getAttribute("ers_username").toString();
		
		if (reimbService.setPendingRequest(ers_username, Integer.parseInt(reimb_id), "approved")) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}
	
	public void denyReimbursement(HttpServletRequest request, HttpServletResponse response, String reimb_id) {
		HttpSession session = request.getSession(false);
		String ers_username = session.getAttribute("ers_username").toString();

		if (reimbService.setPendingRequest(ers_username, Integer.parseInt(reimb_id), "denied")) {
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}

	public void pendingRequest(HttpServletRequest request, HttpServletResponse response, String urlSections) {
		HttpSession session = request.getSession(false);
		
		List<Reimbursement> reimb = reimbService.getPendingRequest(session.getAttribute("ers_username").toString());
		
		if (reimb != null) {
			setJSONReimbursment(reimb, response);
			response.setStatus(201);
		} else {
			response.setStatus(406);
		}
	}

	
}
