package com.revature.pojos;

import java.sql.Date;

public class RRequest {

	private int requestID;
	private int employeeID;
	private Date requestDate;
	private int approved;
	private String description;

	public RRequest(int reqID, int empID, Date reqDate, int app, String desc) {
		super();
		this.requestID = reqID;
		this.employeeID = empID;
		this.requestDate = reqDate;
		this.approved = app;
		this.description = desc;
	}
	//getters and setters
	public int getApprovalStatus() {
		return approved;
	}

	public void setApprovalSatus(int choice) {
		//1 - approved
		//0 - pending
		//-1 - denied
		this.approved = choice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getRequestID() {
		return requestID;
	}

	public int getEmployeeID() {
		return employeeID;
	}

	public Date getRequestDate() {
		return requestDate;
	}

}
