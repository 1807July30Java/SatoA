package com.revature.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection(String filename) throws SQLException, IOException {
		// check that driver is being seen
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			//IDEK What we should do in this case
			return null;
		}
		Properties prop = new Properties();
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		prop.load(loader.getResourceAsStream(filename));
		String url = prop.getProperty("url");
		String user = prop.getProperty("username");
		String password = prop.getProperty("password");
		return DriverManager.getConnection(url, user, password);
	}
}
