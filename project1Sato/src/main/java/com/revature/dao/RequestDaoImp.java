package com.revature.dao;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojos.Employee;
import com.revature.pojos.RRequest;
import com.revature.util.ConnectionUtil;

public class RequestDaoImp implements RequestDao {

	private String propertiesFile = "connection.properties";

	@Override
	public List<RRequest> getRequestsFromEmployee(Employee e) {
		List<RRequest> requestsFromEmployee = new ArrayList<>();
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectReqs = "SELECT R_ID,REQ_DATE,APPROVED,REQ_DESC,IMAGE FROM REQUESTS WHERE E_ID = ?";
			prepped = con.prepareStatement(selectReqs);
			prepped.setInt(1, e.getEmployeeID());
			ResultSet results = prepped.executeQuery();
			while (results.next()) {
				int id = results.getInt("R_ID");
				Date requestDate = results.getDate("REQ_DATE");
				int approvalStat = results.getInt("APPROVED");
				String description = results.getString("REQ_DESC");
				Blob image = results.getBlob("IMAGE");
				requestsFromEmployee.add(new RRequest(id, e.getEmployeeID(), requestDate, approvalStat, description, image));
			}
			con.close();
		} catch (SQLException se) {
			// do what
		} catch (IOException e1) {
			// do what
		}
		return requestsFromEmployee;
	}

	@Override
	public List<RRequest> getRequestsForManager(Employee manager) {
		List<RRequest> requestsForManager = new ArrayList<>();
		PreparedStatement prepped = null;
		EmployeeDao ed = new EmployeeDaoImp();
		List<Employee> empsManaged = ed.getAllEmployeesManaged(manager);
		for(Employee emp:empsManaged) {
			try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
				String selectReqs = "SELECT R_ID,REQ_DATE,APPROVED,REQ_DESC,IMAGE FROM REQUESTS WHERE E_ID = ?";
				prepped = con.prepareStatement(selectReqs);
				prepped.setInt(1, emp.getEmployeeID());
				ResultSet results = prepped.executeQuery();
				while (results.next()) {
					int id = results.getInt("R_ID");
					Date requestDate = results.getDate("REQ_DATE");
					int approvalStat = results.getInt("APPROVED");
					String description = results.getString("REQ_DESC");
					Blob image = results.getBlob("IMAGE");
					requestsForManager.add(new RRequest(id, emp.getEmployeeID(), requestDate, approvalStat, description, image));
				}
				con.close();
			} catch (SQLException se) {
				// do what
			} catch (IOException e1) {
				// do what
			}
		}
		return requestsForManager;
	}


	@Override
	public boolean submitRequest(RRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRequest(RRequest req) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<RRequest> getResolvedRequests() {
		// TODO Auto-generated method stub
		return null;
	}

}
