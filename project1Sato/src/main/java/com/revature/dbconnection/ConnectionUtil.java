package com.revature.dbconnection;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException, IOException {
		Properties prop = new Properties();
		InputStream dbinfofile = new FileInputStream("src/main/resources/connection.properties");
		prop.load(dbinfofile);
		String url = prop.getProperty("url");
		String user = prop.getProperty("username");
		String password = prop.getProperty("password");
		return DriverManager.getConnection(url, user, password);
	}
}
