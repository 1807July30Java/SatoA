package com.revature.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.revature.pojos.Employee;
import com.revature.pojos.RRequest;
import com.revature.service.AuthenticationService;
import com.revature.service.UltraService;

/**
 * Servlet implementation class NewRequestServlet
 */
@MultipartConfig
@WebServlet(name = "NewRequestServlet", urlPatterns = "/newReq")
public class NewRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewRequestServlet() {
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
		if (session != null && session.getAttribute("employeeJSON") != null) {
			request.getRequestDispatcher("request.html").forward(request, response);
		} else {
			response.sendRedirect("login");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
			String description = request.getParameter("description");
			String amountString = request.getParameter("money");
			System.out.println(description);
			System.out.println(amountString);
			double amount = Double.parseDouble(amountString);
			//double amount = Double.parseDouble();
			String desc = request.getParameter("description");
			Employee e = new Gson().fromJson((String) session.getAttribute("employeeJSON"), Employee.class);
			Part filePart = request.getPart("imageFile");
			InputStream inputStream = null;
			if (filePart != null) {
				// prints out some information for debugging
				//System.out.println(filePart.getName());
				//System.out.println(filePart.getSize());
				//System.out.println(filePart.getContentType());
				// obtains input stream of the upload file
				inputStream = filePart.getInputStream();
			}
			Calendar calendar = Calendar.getInstance();
			java.util.Date currentDate = calendar.getTime();
			RRequest newRequest = new RRequest(e.getEmployeeID(), new java.sql.Date(currentDate.getTime()), 0, desc,
					amount);
			UltraService us = new UltraService();
			RRequest newOne = us.sendRequest(newRequest, inputStream);
			if (newOne != null) {
				// can send to success page or smthin
			}
			response.sendRedirect("reimbursement");
		} else {
			response.sendRedirect("login");
		}
	}

}
