//package com.revature.web;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.revature.models.User;
//


////NO LONGER USING THIS, TRYING TO USE FRONT CONTROLLER MODEL
//public class SuccessServlet extends HttpServlet{
//
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		HttpSession session = request.getSession(false);
//		response.setContentType("text/html");
//		PrintWriter pw = response.getWriter();
//		
//		if(session != null) {
//			new LogoutServlet().doGet(request, response);
//			String username = request.getParameter("username");
//			session.setAttribute("username", username);
//			
//			doPost(request, response);
//		}else {
//			response.sendRedirect("/static/Project1-Employee.html");
//		}
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
//			throws ServletException, IOException{
//		HttpSession session = request.getSession(false);
//		response.setContentType("text/html");
//		
//		String username = request.getParameter("username");
//		
//		RequestDispatcher requestDispatcher = null;
//		PrintWriter printWriter = response.getWriter();
//		User user = new User();
//		
//		if(user.getUserRoleID() == 1) {
//			session.setAttribute("username", username);
//			requestDispatcher = request.getRequestDispatcher("/static");
//			requestDispatcher.forward(request, response);
//		}
//	}
//	
//}
