//
//package com.revature.web;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import com.revature.controllers.UserController;
//import com.revature.daos.UserDAO;
//import com.revature.daos.UserDAOImpl;
//import com.revature.models.User;


////NO LONGER USING THIS, TRYING TO USE FRONT CONTROLLER MODEL
//public class RegisterServlet extends HttpServlet{
//
//	private static UserDAO userDao = new UserDAOImpl();
//	private UserController userController = new UserController();
//	
//		@Override
//		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//			HttpSession session = request.getSession(false);
//			response.setContentType("text/html");
//			PrintWriter pw = response.getWriter();
//			
//			if(session != null) {
//				String userIdentifier = request.getParameter("username");
//				session.setAttribute("username", userIdentifier);
//				
//				doPost(request, response);
//			}else {
//				response.sendRedirect("/static/Project1-Employee.html");
//			}
//		}
//	
//		@Override
//	    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//	            throws ServletException, IOException {
//		
//	        	
//				response.setContentType("text/html;charset=UTF-8");
//			    PrintWriter out = response.getWriter();
//			
//			    User user = new User();
//			    //objectMapper
//			    
//			    userDao.addUser(user);
//	        
//	    }
//	
//	
//}
