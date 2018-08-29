package com.revature.service;

import java.io.InputStream;
import java.util.List;

import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.dao.RequestDao;
import com.revature.dao.RequestDaoImp;
import com.revature.pojos.Employee;
import com.revature.pojos.RRequest;

public class UltraService {
	private EmployeeDao ed = new EmployeeDaoImp();
	private RequestDao rd = new RequestDaoImp();
	
	public List<Employee> allEmployees(){
		return ed.getAllEmployees();
	}
	public List<Employee> employeesManaged(int i){
		return ed.getAllEmployeesManaged(i);
	}
	public List<RRequest> requestsfor(int id){
		return rd.getRequestsForManager(id);
	}
	public List<RRequest> requestsby(int id){
		return rd.getRequestsFromEmployee(id);
	}
	public List<RRequest> allRequests(){
		return rd.getAllRequests();
	}
	public Employee getEmployee(int id) {
		return ed.getEmployee(id);
	}
	
	public RRequest sendRequest(RRequest req, InputStream blobby) {
		if(blobby!=null) {
			return rd.submitRequestWithImage(req, blobby);
		} else {
			return rd.submitRequest(req);
		}
	}
	public boolean updateReq(int rid,int rstatus) {
		return rd.updateRequest(rid, rstatus);
	}
}
