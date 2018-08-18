package com.revature.dao;

import java.util.List;
import com.revature.pojos.*;

public interface RequestDao {
	
	public List<RRequest> getRequestsFromEmployee(int employeeID);
	public List<RRequest> getRequestsFromEmployee(Employee e);
	
	public List<RRequest> getRequestsForManager(int managerID);
	public List<RRequest> getRequestsForManager(Employee man);
	
	public boolean submitRequest(RRequest req);
	
	public boolean updateRequest(int reqID);
	public boolean updateRequest(RRequest req);
	
}
