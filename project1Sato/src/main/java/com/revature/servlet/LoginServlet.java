package com.revature.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.pojos.Credentials;
import com.revature.service.AuthenticationService;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("login.html").forward(request, response);
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
		Credentials cred = AuthenticationService.validUser(username, password);
		if (cred != null) {
			session.setAttribute("employeeID", cred.getEmployeeId());
			session.setAttribute("problem", null);
			response.sendRedirect("home");
		} else {
			session.setAttribute("problem", "incorrect password");
			response.sendRedirect("login");
		}
	}

}
