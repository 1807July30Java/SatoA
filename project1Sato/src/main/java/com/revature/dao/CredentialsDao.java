package com.revature.dao;

import com.revature.pojos.Credentials;

public interface CredentialsDao {
	public Credentials confirmCreds(String user, String pass);
	public boolean updatePassword(Credentials creds);
}
