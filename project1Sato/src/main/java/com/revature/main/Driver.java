package com.revature.main;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.pojos.Employee;

public class Driver {

	public static void main(String[] args) {
		test();
	}
	static void test() {
		EmployeeDao ed = new EmployeeDaoImp();
		List<Employee> emps = ed.getAllEmployees();
		System.out.println(emps.toString());
	}
}
