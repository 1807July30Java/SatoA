package com.revature.dao;

import java.util.List;
import com.revature.pojos.*;

public interface RequestDao {
	
	//public RRequest getRequest(int requestId);
	
	//public List<RRequest> getRequestsFromEmployee(int employeeID);
	public List<RRequest> getRequestsFromEmployee(int eid);
	
	public List<RRequest> getRequestsForManager(int eid);
	
	public RRequest submitRequest(RRequest req);
	
	//public boolean updateRequest(int reqID);
	public boolean updateRequest(RRequest req1,RRequest req2);
	
	public List<RRequest> getResolvedRequests();
	
	public List<RRequest> getAllRequests();


}
