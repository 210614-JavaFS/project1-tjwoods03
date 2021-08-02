//package com.revature.web;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;


//NO LONGER USING THIS, TRYING TO USE FRONT CONTROLLER MODEL
//public class LogoutServlet extends HttpServlet{
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
//		
//		HttpSession session = request.getSession(true);
//		
//		if(session!=null) {
//			session.invalidate();
//		}
//		
//		response.sendRedirect("");
//	}
//	
//}
