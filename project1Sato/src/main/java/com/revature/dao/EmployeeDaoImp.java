package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.pojos.Employee;
import com.revature.util.ConnectionUtil;

public class EmployeeDaoImp implements EmployeeDao {

	private String propertiesFile = "connection.properties";

	@Override
	public Employee getEmployee(int employeeId) {
		Employee dude = null;
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectEmployee = "SELECT E.E_ID, E.F_NAME, E.L_NAME, E.EMAIL, E.SUPERIOR, M.F_NAME ||' ' || M.L_NAME AS MANAGER, E.IS_MANAGER FROM EMPLOYEE E FULL JOIN EMPLOYEE M ON E.SUPERIOR = M.E_ID WHERE E.E_ID = ?";
			prepped = con.prepareStatement(selectEmployee);
			prepped.setInt(1, employeeId);
			ResultSet results = prepped.executeQuery();
			while (results.next()) {
				int id = results.getInt("E_ID");
				String firstName = results.getString("F_NAME");
				String lastName = results.getString("L_NAME");
				String emailAdd = results.getString("EMAIL");
				int managerId = results.getInt("SUPERIOR");
				String managerName = results.getString("MANAGER");
				int isManager = results.getInt("IS_MANAGER");
				dude = new Employee(id, firstName, lastName, emailAdd, managerId,managerName, isManager);
			}
			con.close();
		} catch (SQLException e) {
			//do what
		} catch (IOException e1) {
			//do what
		}
		return dude;
	}

	@Override
	public void recuiteEmployee(String email) {
		// TODO Auto-generated method stub	
	}
	
	@Override
	public Employee addEmployee(Employee e) {
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectAllEmployees = "SELECT E.E_ID, E.F_NAME, E.L_NAME, E.EMAIL, E.SUPERIOR, M.F_NAME ||' ' || M.L_NAME AS MANAGER, E.IS_MANAGER FROM EMPLOYEE E LEFT JOIN EMPLOYEE M ON E.SUPERIOR = M.E_ID";
			Statement statement = con.createStatement();
			ResultSet results = statement.executeQuery(selectAllEmployees);
			while (results.next()) {
				int id = results.getInt("E_ID");
				String firstName = results.getString("F_NAME");
				String lastName = results.getString("L_NAME");
				String emailAdd = results.getString("EMAIL");
				int managerId = results.getInt("SUPERIOR");
				String managerName = results.getString("MANAGER");
				int isManager = results.getInt("IS_MANAGER");
				allEmployees.add(new Employee(id, firstName, lastName, emailAdd, managerId,managerName, isManager));
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			//do what
		}

		return allEmployees;
	}

	@Override
	public List<Employee> getAllEmployeesManaged(Employee e) {
		List<Employee> employeesManaged = new ArrayList<>();
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectEmployee = "SELECT E.E_ID, E.F_NAME, E.L_NAME, E.EMAIL, E.SUPERIOR, M.F_NAME ||' ' || M.L_NAME AS MANAGER, E.IS_MANAGER FROM EMPLOYEE E FULL JOIN EMPLOYEE M ON E.SUPERIOR = M.E_ID WHERE E.SUPERIOR = ?";
			prepped = con.prepareStatement(selectEmployee);
			prepped.setInt(1, e.getEmployeeID());
			ResultSet results = prepped.executeQuery();
			while (results.next()) {
				int id = results.getInt("E_ID");
				String firstName = results.getString("F_NAME");
				String lastName = results.getString("L_NAME");
				String emailAdd = results.getString("EMAIL");
				int managerId = results.getInt("SUPERIOR");
				String managerName = results.getString("MANAGER");
				int isManager = results.getInt("IS_MANAGER");
				employeesManaged.add(new Employee(id, firstName, lastName, emailAdd, managerId,managerName, isManager));
			}
			con.close();
		} catch (SQLException e2) {
			//do what
		} catch (IOException e1) {
			//do what
		}
		return employeesManaged;
	}
	@Override
	public List<Employee> getAllEmployeesManaged(int eID) {
		List<Employee> employeesManaged = new ArrayList<>();
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectEmployee = "SELECT E.E_ID, E.F_NAME, E.L_NAME, E.EMAIL, E.SUPERIOR, M.F_NAME ||' ' || M.L_NAME AS MANAGER, E.IS_MANAGER FROM EMPLOYEE E FULL JOIN EMPLOYEE M ON E.SUPERIOR = M.E_ID WHERE E.SUPERIOR = ?";
			prepped = con.prepareStatement(selectEmployee);
			prepped.setInt(1, eID);
			ResultSet results = prepped.executeQuery();
			while (results.next()) {
				int id = results.getInt("E_ID");
				String firstName = results.getString("F_NAME");
				String lastName = results.getString("L_NAME");
				String emailAdd = results.getString("EMAIL");
				int managerId = results.getInt("SUPERIOR");
				String managerName = results.getString("MANAGER");
				int isManager = results.getInt("IS_MANAGER");
				employeesManaged.add(new Employee(id, firstName, lastName, emailAdd, managerId,managerName, isManager));
			}
			con.close();
		} catch (SQLException e2) {
			//do what
		} catch (IOException e1) {
			//do what
		}
		return employeesManaged;
	}

	@Override
	public boolean updateEmployee(int employeeId, String param, String change) {
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String updateRequest = "";
			if(param.equals("first")) {
				updateRequest = "UPDATE EMPLOYEE SET F_NAME=? WHERE E_ID=?";
			}
			if (param.equals("last")) {
				updateRequest = "UPDATE EMPLOYEE SET L_NAME=? WHERE E_ID=?";
			}
			if (param.equals("email")) {
				updateRequest = "UPDATE EMPLOYEE SET EMAIL=? WHERE E_ID=?";
			} 
			if(updateRequest.equals("")) {
				System.out.println("Nothing Submitted");
				return false;
			}
			prepped = con.prepareStatement(updateRequest);
			System.out.println("Changing " +param + " TO " + change);
			prepped.setString(1, change);
			prepped.setInt(2, employeeId);
			prepped.executeQuery();
			con.close();
			return true;
		} catch (SQLException e) {

		} catch (IOException e) {
			
		}
		return false;
	}
	

}
