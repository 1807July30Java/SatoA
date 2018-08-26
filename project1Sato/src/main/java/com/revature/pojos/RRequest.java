package com.revature.pojos;

import java.sql.Blob;
import java.sql.Date;

public class RRequest {

	private int requestID;
	private int employeeID;
	private Date requestDate;
	private int approved;
	private String description;
	private Blob image;

	public RRequest(int reqID, int empID, Date reqDate, int app, String desc, Blob img) {
		super();
		this.requestID = reqID;
		this.employeeID = empID;
		this.requestDate = reqDate;
		this.approved = app;
		this.description = desc;
		this.image = img;
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
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}

}
