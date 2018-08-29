package com.revature.dao;

import java.io.InputStream;
import java.util.List;
import com.revature.pojos.*;

public interface RequestDao {
	
	//public RRequest getRequest(int requestId);
	
	//public List<RRequest> getRequestsFromEmployee(int employeeID);
	public List<RRequest> getRequestsFromEmployee(int eid);
	
	public List<RRequest> getRequestsForManager(int eid);
	
	public RRequest submitRequest(RRequest req);
	public RRequest submitRequestWithImage(RRequest req, InputStream blob);
	
	//public boolean updateRequest(int reqID);
	public boolean updateRequest(int reqID,int newStatus);
	
	public List<RRequest> getResolvedRequests();
	
	public List<RRequest> getAllRequests();


}
