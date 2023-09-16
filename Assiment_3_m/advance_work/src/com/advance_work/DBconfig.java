package com.advance_work;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconfig {
	static Connection con = null;
	static {
		try {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/first_java_jdbc","root","root@123");
		}catch(Exception e) {}
		}
	static Connection getCon() {
		return con;
		
	}
	
	
	
}
