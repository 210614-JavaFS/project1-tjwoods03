package com.revature.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimbursement;
import com.revature.services.ReimbursementService;

public class ReimbursementController {

	private static ReimbursementService reimbService = new ReimbursementService();
	private ObjectMapper objectMapper = new ObjectMapper();
	
	public void getAllReimbursements(HttpServletResponse response) throws IOException{
		List<Reimbursement> list = reimbService.findAllReimbursements();
		
		String json = objectMapper.writeValueAsString(list);
		
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(json);
		
		response.setStatus(200);
	}

	public void addReimbursement(HttpServletRequest request, HttpServletResponse response) throws IOException {
		BufferedReader reader = request.getReader();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		String line = reader.readLine();
		
		while (line != null) {
			stringBuilder.append(line);
			line = reader.readLine();
		}
		
		String body = new String(stringBuilder);
		
		Reimbursement reimb = objectMapper.readValue(body, Reimbursement.class);
		
		if(reimbService.addReimbursement(reimb)) {
			response.setStatus(201);
		}else {
			response.setStatus(406);
		}
		
	}
	
}
