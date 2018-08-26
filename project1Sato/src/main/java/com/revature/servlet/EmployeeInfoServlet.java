package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.pojos.Employee;

/**
 * Servlet implementation class SessionServlet
 * 
 */
public class EmployeeInfoServlet extends HttpServlet {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -720743516083268190L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInfoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			response.setContentType("application/json");
			String empJSON= new Gson().toJson(session.getAttribute("employeeJSON"));
			response.getWriter().write(empJSON);
		} else {
			response.setContentType("application/json");
			response.getWriter().write("{null}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
