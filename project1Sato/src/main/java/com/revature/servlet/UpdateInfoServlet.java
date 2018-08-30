package com.revature.servlet;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.pojos.Employee;
import com.revature.service.UltraService;

/**
 * Servlet implementation class UpdateInfoServlet
 */
public class UpdateInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("employeeJSON") != null) {
			request.getRequestDispatcher("updateInfo.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			
			Employee e = new Gson().fromJson((String) session.getAttribute("employeeJSON"), Employee.class);
			UltraService us = new UltraService();			
			if(!e.getFirstName().equals(firstName)) {
				System.out.println(firstName);
				System.out.println(e.getEmployeeID());
				us.updateEmp(e.getEmployeeID(),"first", firstName);
			}
			if(!e.getLastName().equals(lastName)) {
				System.out.println(lastName);
				System.out.println(e.getEmployeeID());
				us.updateEmp(e.getEmployeeID(),"last", lastName);
			} 
			if(!e.getEmailAdd().equals(email)) {
				us.updateEmp(e.getEmployeeID(),"email", email);
			}
			e = us.getEmployee(e.getEmployeeID());
			session.setAttribute("employeeJSON", new Gson().toJson(e));
			System.out.println(session.getAttribute("employeeJSON"));
			response.sendRedirect("dash");
		} else {
			response.sendRedirect("login");
		}
	}

}
