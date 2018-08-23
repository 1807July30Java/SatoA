package com.revature.dao;

import java.util.List;

import com.revature.pojos.Employee;
import com.revature.pojos.RRequest;

public class RequestDaoImp implements RequestDao {

	private String propertiesFile = "connection.properties";

	@Override
	public List<RRequest> getRequestsFromEmployee(Employee e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RRequest> getRequestsForManager(int managerID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RRequest> getRequestsForManager(Employee man) {
		// TODO Auto-generated method stub
		return null;
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
