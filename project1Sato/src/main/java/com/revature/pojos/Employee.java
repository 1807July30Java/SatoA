package com.revature.pojos;

public class Employee {
	//all of the fields for info about employee
	private int employeeID;
	private String firstName;
	private String lastName;
	private String emailAdd;
	private int managerID;
	private boolean isManager;
	
	/**
	 * @param id
	 * @param fName
	 * @param lName
	 * @param mailAdd
	 * @param manID
	 * @param isMan
	 * @param uname
	 * @param pass
	 */
	public Employee(int id,String fName,String lName,String mailAdd, int manID, int isMan)
	{
		super();
		this.employeeID = id;
		this.setFirstName(fName);
		this.lastName = lName;
		this.emailAdd = mailAdd;
		this.managerID = manID;
		//isManager in SQL DB is either 1 or 0
		if (isMan == 1) {
			this.isManager = true;
		} else {
			this.isManager = false;
		}
	}
	
	//Getters and setters
	public int getEmployeeID() {
		return employeeID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmailAdd() {
		return emailAdd;
	}
	public void setEmailAdd(String emailAdd) {
		this.emailAdd = emailAdd;
	}
	public int getManagerID() {
		return managerID;
	}
	public void setManagerID(int managerID) {
		this.managerID = managerID;
	}
	public boolean isManager() {
		return isManager;
	}
	public void setManager(boolean isManager) {
		this.isManager = isManager;
	}

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAdd=" + emailAdd + ", managerID=" + managerID + ", isManager=" + isManager + "]";
	}


}
