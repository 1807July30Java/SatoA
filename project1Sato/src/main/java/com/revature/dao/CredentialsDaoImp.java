package com.revature.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.pojos.Credentials;
import com.revature.pojos.Employee;
import com.revature.util.ConnectionUtil;

public class CredentialsDaoImp implements CredentialsDao {
	
	private String propertiesFile = "connection.properties";

	@Override
	public Credentials confirmCreds(String user, String pass) {
		Credentials usah = null;
		PreparedStatement prepped = null;
		try (Connection con = ConnectionUtil.getConnection(propertiesFile)) {
			String Creds = "SELECT PWORD,E_ID FROM CREDENTIALS WHERE UNAME = ?";
			prepped = con.prepareStatement(Creds);
			prepped.setString(1, user);
			ResultSet results = prepped.executeQuery();
			while (results.next()) {
				String password = results.getString("PWORD");
				if(password.equals(pass)) {
					int employeeId = results.getInt("E_ID");
					usah = new Credentials(user, pass, employeeId);
				}
			}
			con.close();
		} catch (SQLException e) {
			//do what
		} catch (IOException e1) {
			//do what
		}
		return usah;
	}

	@Override
	public boolean updatePassword(Credentials creds) {
		// TODO Auto-generated method stub
		return false;
	}

}
