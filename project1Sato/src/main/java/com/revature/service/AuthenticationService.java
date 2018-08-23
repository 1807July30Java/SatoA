package com.revature.service;

import com.revature.dao.CredentialsDao;
import com.revature.dao.CredentialsDaoImp;
import com.revature.pojos.Credentials;

public class AuthenticationService {
	public static Credentials validUser(String username, String password) {
		if (username != null && password != null) {
			CredentialsDao cd = new CredentialsDaoImp();
			return cd.confirmCreds(username, password);
		} else {
			return null;
		}
	}
}
