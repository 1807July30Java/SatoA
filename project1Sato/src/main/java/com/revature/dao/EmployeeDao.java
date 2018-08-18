package com.revature.dao;

import java.util.List;
import com.revature.pojos.*;

public interface EmployeeDao {
	
	public Employee getEmployee(int employeeId);
	public Employee getEmployee(String user, String pass);
	
	public Employee addEmployee(Employee e);
	public List<Employee> getAllEmployees(int employeeId);
	
}
