package com.revature.pojos;

import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;

public class RRequest {

	private int requestID;
	private int eID;
	private String requester;
	private Date requestDate;
	private int approved;
	private String description;
	private byte[] imageBlob;
	private double amount;

	public RRequest(int reqID, int empID, String reqster, Date reqDate, int app, String desc, Blob img, double amt) {
		super();
		this.requestID = reqID;
		this.eID = empID;
		this.requester = reqster;
		this.requestDate = reqDate;
		this.approved = app;
		this.description = desc;
		try {
			this.imageBlob = img.getBytes(1, (int)img.length());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		this.amount = amt;
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
		return eID;
	}

	public Date getRequestDate() {
		return requestDate;
	}
	public byte[] getImage() {
		return imageBlob;
	}
	public void setImage(Blob image) {
		//this.imageBlob = image;
	}
	@Override
	public String toString() {
		return "RRequest [requestID=" + requestID + ", employeeID=" + eID + ", requestDate=" + requestDate
				+ ", approved=" + approved + ", description=" + description + ", image=" + imageBlob + "]";
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getRequester() {
		return requester;
	}
	public void setRequester(String requester) {
		this.requester = requester;
	}

}
