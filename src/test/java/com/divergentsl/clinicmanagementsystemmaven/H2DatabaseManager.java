package com.divergentsl.clinicmanagementsystemmaven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.divergentsl.IDatabaseManager;

public class H2DatabaseManager implements IDatabaseManager {

//	public static String DB_URL = "jdbc:h2:mem:";
	public static String DB_URL = "jdbc:h2:~/test";

	static {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public Connection getConnection() throws SQLException {
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(DB_URL, "sa", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

}
