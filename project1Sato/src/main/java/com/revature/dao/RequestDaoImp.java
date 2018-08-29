package com.revature.dao;

import java.io.IOException;
import java.io.InputStream;
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
	public List<RRequest> getRequestsFromEmployee(int eid) {
		List<RRequest> requestsFromEmployee = new ArrayList<>();
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectReqs = "SELECT R.R_ID, R.REQ_DATE, R.APPROVED, R.REQ_DESC, R.IMAGE, R.AMOUNT, E.F_NAME ||' ' || E.L_NAME AS REQUESTER FROM REQUESTS R JOIN EMPLOYEE E ON R.E_ID = E.E_ID WHERE R.E_ID=?";
			prepped = con.prepareStatement(selectReqs);
			//System.out.println(eid);
			prepped.setInt(1, eid);
			ResultSet results = prepped.executeQuery();
			while (results.next()) {
				int id = results.getInt("R_ID");
				String reqster = results.getString("REQUESTER");
				Date requestDate = results.getDate("REQ_DATE");
				int approvalStat = results.getInt("APPROVED");
				String description = results.getString("REQ_DESC");
				Blob image = results.getBlob("IMAGE");
				double amt = results.getDouble("AMOUNT");
				requestsFromEmployee.add(new RRequest(id, eid, reqster,requestDate, approvalStat, description, image,amt));
				//System.out.println(requestsFromEmployee);
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
	public List<RRequest> getRequestsForManager(int eid) {
		List<RRequest> requestsForManager = new ArrayList<>();
		PreparedStatement prepped = null;
		EmployeeDao ed = new EmployeeDaoImp();
		List<Employee> empsManaged = ed.getAllEmployeesManaged(eid);
		for(Employee emp:empsManaged) {
			try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
				String selectReqs = "SELECT R.R_ID, R.REQ_DATE, R.APPROVED, R.REQ_DESC, R.IMAGE, R.AMOUNT, E.F_NAME ||' ' || E.L_NAME AS REQUESTER FROM REQUESTS R JOIN EMPLOYEE E ON R.E_ID = E.E_ID WHERE R.E_ID=?";
				prepped = con.prepareStatement(selectReqs);
				prepped.setInt(1, emp.getEmployeeID());
				ResultSet results = prepped.executeQuery();
				while (results.next()) {
					int id = results.getInt("R_ID");
					String reqster = results.getString("REQUESTER");
					Date requestDate = results.getDate("REQ_DATE");
					int approvalStat = results.getInt("APPROVED");
					String description = results.getString("REQ_DESC");
					Blob image = results.getBlob("IMAGE");
					double amt = results.getDouble("AMOUNT");
					requestsForManager.add(new RRequest(id, emp.getEmployeeID(), reqster, requestDate, approvalStat, description, image,amt));
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
	public boolean updateRequest(int requestID,int requestStatus) {
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String updateRequest = "UPDATE REQUESTS SET APPROVED=? WHERE R_ID=?";
			prepped = con.prepareStatement(updateRequest);
			prepped.setInt(1, requestStatus);
			prepped.setInt(2, requestID);
			prepped.executeQuery();
			con.close();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<RRequest> getResolvedRequests() {
		List<RRequest> resolvedRequests = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectAllReqs = "SELECT R.R_ID, R.E_ID, R.REQ_DATE, R.APPROVED, R.REQ_DESC, R.IMAGE, R.AMOUNT, E.F_NAME ||' ' || E.L_NAME AS REQUESTER FROM REQUESTS R JOIN EMPLOYEE E ON R.E_ID = E.E_ID WHERE APPROVED=1 OR APPROVED=-1";
			Statement statement = con.createStatement();
			ResultSet results = statement.executeQuery(selectAllReqs);
			while (results.next()) {
				int id = results.getInt("R_ID");
				int eid = results.getInt("E_ID");
				String reqster = results.getString("REQUESTER");
				Date requestDate = results.getDate("REQ_DATE");
				int approvalStat = results.getInt("APPROVED");
				String description = results.getString("REQ_DESC");
				Blob image = results.getBlob("IMAGE");
				double amt = results.getDouble("AMOUNT");
				resolvedRequests.add(new RRequest(id, eid, reqster, requestDate, approvalStat, description, image,amt));
			}
			con.close();
		} catch (SQLException se) {
			// do what
		} catch (IOException e1) {
			// do what
		}
		return resolvedRequests;
	}

	@Override
	public List<RRequest> getAllRequests() {
		List<RRequest> allRequests = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectAllReqs = "SELECT R.R_ID, R.E_ID, R.REQ_DATE, R.APPROVED, R.REQ_DESC, R.IMAGE, R.AMOUNT, E.F_NAME ||' ' || E.L_NAME AS REQUESTER FROM REQUESTS R JOIN EMPLOYEE E ON R.E_ID = E.E_ID";
			Statement statement = con.createStatement();
			ResultSet results = statement.executeQuery(selectAllReqs);
			while (results.next()) {
				int id = results.getInt("R_ID");
				int eid = results.getInt("E_ID");
				String reqster = results.getString("REQUESTER");
				Date requestDate = results.getDate("REQ_DATE");
				int approvalStat = results.getInt("APPROVED");
				String description = results.getString("REQ_DESC");
				Blob image = results.getBlob("IMAGE");
				double amt = results.getDouble("AMOUNT");
				allRequests.add(new RRequest(id, eid,reqster, requestDate, approvalStat, description, image,amt));
			}
			con.close();
		} catch (SQLException se) {
			// do what
		} catch (IOException e1) {
			// do what
		}
		return allRequests;
	}

	@Override
	public RRequest submitRequestWithImage(RRequest req, InputStream blob) {
		RRequest theReq = null;
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectReqs = "INSERT INTO REQUESTS(E_ID,REQ_DATE,APPROVED,REQ_DESC,IMAGE,AMOUNT) VALUES (?,?,?,?,?,?)";
			prepped = con.prepareStatement(selectReqs);
			prepped.setInt(1, req.getEmployeeID());
			prepped.setDate(2, req.getRequestDate());
			prepped.setInt(3, req.getApprovalStatus());
			prepped.setString(4, req.getDescription());
			prepped.setBlob(5, blob);
			prepped.setDouble(6, req.getAmount());
			prepped.executeQuery();
			con.close();
		} catch (SQLException se) {
			// do what
		} catch (IOException e1) {
			// do what
		}
		return theReq;
	}
	@Override
	public RRequest submitRequest(RRequest req) {
		RRequest theReq = null;
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectReqs = "INSERT INTO REQUESTS(E_ID,REQ_DATE,APPROVED,REQ_DESC,AMOUNT) VALUES (?,?,?,?,?)";
			prepped = con.prepareStatement(selectReqs);
			prepped.setInt(1, req.getEmployeeID());
			prepped.setDate(2, req.getRequestDate());
			prepped.setInt(3, req.getApprovalStatus());
			prepped.setString(4, req.getDescription());
			prepped.setDouble(5, req.getAmount());
			prepped.executeQuery();
			con.close();
		} catch (SQLException se) {
			return theReq;
		} catch (IOException e1) {
			return theReq;
		}
		return theReq;
	}

}
