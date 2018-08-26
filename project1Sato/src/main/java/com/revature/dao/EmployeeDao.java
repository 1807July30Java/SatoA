package com.revature.dao;

import java.util.List;
import com.revature.pojos.*;

public interface EmployeeDao {
	
	public Employee getEmployee(int employeeId);
	
	public void recuiteEmployee(String email);
	
	public Employee addEmployee(Employee e);
	
	public List<Employee> getAllEmployees();
	
	public List<Employee> getAllEmployeesManaged(Employee e);
	
}
