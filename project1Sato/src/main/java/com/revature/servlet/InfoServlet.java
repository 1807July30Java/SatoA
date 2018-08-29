package com.revature.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.revature.pojos.Employee;
import com.revature.util.DispatcherUtil;

/**
 * Servlet implementation class EmployeeInfoServlet
 */
public class InfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DispatcherUtil dispatch = new DispatcherUtil();
	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InfoServlet() {
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
		// check whether a Session exists
		if (session != null && session.getAttribute("employeeJSON") != null) {
			// check for desired entity employee or requests
			String entity = request.getParameter("entity");
			String get = request.getParameter("get");
			String eID = request.getParameter("id");
			//System.out.println(entity+" "+get+" "+eID);
			if (entity != null && get != null) {
				if(eID!=null) {
					int eIDasInt = Integer.parseInt(eID);
					response.setContentType("application/json");
					response.getWriter().write(dispatch.processGet(entity, get, eIDasInt));
				} else {
					int reqEmpID = new Gson().fromJson((String) session.getAttribute("employeeJSON"), Employee.class).getEmployeeID();
					response.setContentType("application/json");
					response.getWriter().write(dispatch.processGet(entity, get, reqEmpID));
				}
			}
		} else {
			response.sendError(403, "not a valid request");
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
