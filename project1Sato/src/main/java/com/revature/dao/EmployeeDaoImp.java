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
			String selectEmployee = "SELECT * FROM EMPLOYEE WHERE E_ID = ?";
			prepped = con.prepareStatement(selectEmployee);
			prepped.setInt(1, employeeId);
			ResultSet results = prepped.executeQuery();
			while (results.next()) {
				int id = results.getInt("E_ID");
				String firstName = results.getString("F_NAME");
				String lastName = results.getString("L_NAME");
				String emailAdd = results.getString("EMAIL");
				int managerId = results.getInt("SUPERIOR");
				int isManager = results.getInt("IS_MANAGER");
				dude = new Employee(id, firstName, lastName, emailAdd, managerId, isManager);
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
	public Employee addEmployee(Employee e) {
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		List<Employee> allEmployees = new ArrayList<>();
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectAllEmployees = "SELECT * FROM EMPLOYEE";
			Statement statement = con.createStatement();
			ResultSet results = statement.executeQuery(selectAllEmployees);
			while (results.next()) {
				int id = results.getInt("E_ID");
				String firstName = results.getString("F_NAME");
				String lastName = results.getString("L_NAME");
				String emailAdd = results.getString("EMAIL");
				int managerId = results.getInt("SUPERIOR");
				int isManager = results.getInt("IS_MANAGER");
				allEmployees.add(new Employee(id, firstName, lastName, emailAdd, managerId, isManager));
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
	public List<Employee> getAllEmployeesManaged(int employeeId) {
		List<Employee> employeesManaged = new ArrayList<>();
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String selectEmployee = "SELECT * FROM EMPLOYEE WHERE SUPERIOR = ?";
			prepped = con.prepareStatement(selectEmployee);
			prepped.setInt(1, employeeId);
			ResultSet results = prepped.executeQuery();
			while (results.next()) {
				int id = results.getInt("E_ID");
				String firstName = results.getString("F_NAME");
				String lastName = results.getString("L_NAME");
				String emailAdd = results.getString("EMAIL");
				int managerId = results.getInt("SUPERIOR");
				int isManager = results.getInt("IS_MANAGER");
				employeesManaged.add(new Employee(id, firstName, lastName, emailAdd, managerId, isManager));
			}
			con.close();
		} catch (SQLException e) {
			//do what
		} catch (IOException e1) {
			//do what
		}
		return employeesManaged;
	}

}
