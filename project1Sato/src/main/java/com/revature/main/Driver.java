package com.revature.main;

import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.dao.RequestDao;
import com.revature.dao.RequestDaoImp;
import com.revature.pojos.Employee;
import com.revature.pojos.RRequest;
import com.revature.service.UltraService;

public class Driver {

	public static void main(String[] args) {
		test();
	}
	static void test() {
		RequestDao rd = new RequestDaoImp();
		List<RRequest> reqs = rd.getRequestsForManager(1);
		//System.out.println(reqs.toString());
	}
}
