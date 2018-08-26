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

/**
 * Servlet implementation class dashManCardServlet
 */
public class EmployeesManagedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeesManagedServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			Employee user = new Gson().fromJson((String) session.getAttribute("employeeJSON"), Employee.class);
			if (user.isManager()) {
				EmployeeDao ed = new EmployeeDaoImp();
				Employee e = new Gson().fromJson((String) session.getAttribute("employeeJSON"), Employee.class);
				response.setContentType("application/json");
				String managedJSON = new Gson().toJson(ed.getAllEmployeesManaged(e));
				response.getWriter().write(managedJSON);
			} else {
				response.getWriter().write("{null}");
			}
		} else {
			response.setContentType("application/json");
			response.getWriter().write("{null}");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
