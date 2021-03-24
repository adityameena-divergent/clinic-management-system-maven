package com.divergentsl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import com.divergentsl.IDatabaseManager;

public class DrugDao {
	IDatabaseManager databaseManager;

	public DrugDao(IDatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}

	public int add(String name, String description) throws SQLException {

		Connection con = null;
		Statement st = null;

		con = databaseManager.getConnection();
		st = con.createStatement();
		int i = st.executeUpdate("insert into drug (drug_name, description) values ('" + name + "','" + description + "')");
		System.out.println("Drug added successfully...");
		st.close();
		con.close();
		return i;
	}

	public Map<String, String> search(String id) throws SQLException {
    	
    	Connection con = null;
    	Statement st = null;
    	
    	con = databaseManager.getConnection();
    	st = con.createStatement();
    	
    	ResultSet rs = st.executeQuery("select * from drug where drug_id = " + id);
    	Map<String, String> data = new HashMap<>();
    	if (rs.next()) {
    		data.put("id", rs.getString(1));
    		data.put("name", rs.getString(2));
    		data.put("description", rs.getString(3));
    	}
    	return data;
    }
	
	
	/**
	 * It is helper method for delete drug data
	 * @param id
	 * @throws SQLException
	 */
	public int delete(String id) throws SQLException {
		
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		int i = st.executeUpdate("delete from drug where drug_id = " + id);
		System.out.println("\nData deleted successfully...");
		return i;
	}
	
	
	/**
	 * It is a helper method for update the drug data
	 * @param data
	 * @throws SQLException
	 */
	public int update(Map<String, String> data) throws SQLException {
		
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		int i = st.executeUpdate("update drug set drug_name = '" + data.get("name") + "', description = '" + data.get("description") + "' where drug_id = " + data.get("id"));
		
		System.out.println("\nData update successfully...");
		
		st.close();
		con.close();
		return i;
	}

}
