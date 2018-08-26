package com.revature.service;

import com.revature.dao.CredentialsDao;
import com.revature.dao.CredentialsDaoImp;
import com.revature.dao.EmployeeDao;
import com.revature.dao.EmployeeDaoImp;
import com.revature.pojos.Credentials;
import com.revature.pojos.Employee;

public class AuthenticationService {
	public static Employee validUser(String username, String password) {
		Employee e = null;
		if (username != null && password != null) {
			CredentialsDao cd = new CredentialsDaoImp();
			Credentials creds = cd.confirmCreds(username,password);
			if(creds!=null) {
				EmployeeDao ed = new EmployeeDaoImp();
				e = ed.getEmployee(creds.getEmployeeId());
			}
		}
		return e;
	}
}
