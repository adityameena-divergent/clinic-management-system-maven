package com.divergentsl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.divergentsl.IDatabaseManager;

public class LoginDao {
	
	IDatabaseManager databaseManager;
	
	public LoginDao(IDatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}
	
	public String doctorLogin(String username, String password) throws SQLException {
	
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		ResultSet rs = st.executeQuery("select * from doctor where username = '" + username + "' AND password = '" + password + "'");
		if(rs.next())
			return rs.getString(1) + " " + rs.getString(2);
		else
			return null;
	}
	
	
	public boolean adminLogin(String username, String password) throws SQLException {
		
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		ResultSet rs = st.executeQuery("select * from admin where username = '" + username + "' AND password = '" + password + "'");
		
		if (rs.next())
			return true;
		else
			return false;
	}
	
	
	

}
