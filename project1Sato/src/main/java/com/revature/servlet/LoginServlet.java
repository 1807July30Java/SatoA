package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.pojos.Employee;
import com.revature.service.AuthenticationService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5998678913965369616L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null && session.getAttribute("employeeJSON")!=null) {
			response.sendRedirect("dash");
		} else {
			request.getRequestDispatcher("login.html").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		// PrintWriter pw = resp.getWriter();
		response.setContentType("text/html");
		// grab params from request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Employee e = AuthenticationService.validUser(username, password);
		if (e != null) {
			// possibly send e as a json to session
			session.setAttribute("employeeJSON", new Gson().toJson(e));
			if(e.isManager()) {
				EmployeeDao ed = new EmployeeDaoImp();
				session.setAttribute("managedJSON", new Gson().toJson(ed.getAllEmployeesManaged(e)));
			}
			session.setAttribute("problem", null);
			response.sendRedirect("dash");
		} else {
			session.setAttribute("problem", "incorrect password");
			response.sendRedirect("login");
		}
	}
}
