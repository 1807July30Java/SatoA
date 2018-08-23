package com.revature.pojos;

public class Credentials {
	
	private String username;
	private String password;
	private int employeeId;
	
	public Credentials(String username, String password, int employeeId) {
		super();
		this.username = username;
		this.password = password;
		this.employeeId = employeeId;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public int getEmployeeId() {
		return employeeId;
	}
}
