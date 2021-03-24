package com.divergentsl.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.divergentsl.IDatabaseManager;

public class PatientDao {
	
	IDatabaseManager databaseManager;
	
	public PatientDao(IDatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}
	
	
	public int insert(Map<String, String> inputData) throws SQLException {

		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		int i = st.executeUpdate("insert into patient (patient_name, age, weight, gender, contact_number, address) values " +
                "('" + inputData.get("patient_name") + "', " + inputData.get("age") + ", " + inputData.get("weight") + ", '" + inputData.get("gender") + "', " + inputData.get("contact_number") + ", '" + inputData.get("address") + "')");
		
		st.close();
		con.close();
		System.out.println("Data inserted successfully...");
		return i;
	}
	
	
	public Map<String, String> search(String patientId) throws SQLException {
		
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		Map<String, String> data = new HashMap<>();
		
		ResultSet rs = st.executeQuery("select * from patient where patient_id = " + patientId);
		
		if (rs.next()) {
			data.put("id", rs.getString(1));
			data.put("name", rs.getString(2));
			data.put("age", rs.getString(3));
			data.put("weight", rs.getString(4));
			data.put("gender", rs.getString(5));
			data.put("contactNumber", rs.getString(6));
			data.put("address", rs.getString(7));
		}
		return data;
	}
	
	
	public List<Map<String, String>> listAll() throws SQLException {
		
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		ResultSet rs = st.executeQuery("select * from patient");
		List<Map<String, String>> list = new ArrayList<>();
		
		while(rs.next()) {
			Map<String, String> record = new HashMap<>();
			record.put("id", rs.getString(1));
			record.put("name", rs.getString(2));
			record.put("age", rs.getString(3));
			record.put("weight", rs.getString(4));
			record.put("gender", rs.getString(5));
			record.put("contactNumber", rs.getString(6));
			record.put("address", rs.getString(7));
			list.add(record);
		}
		return list;
	}
	
	
	public int delete(String patientId) throws SQLException {
		
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		int i = st.executeUpdate("delete from patient where patient_id = " + patientId);
		System.out.println("Data deleted successfully...");
		return i;
	}
	
	
	public int update(Map<String, String> data) throws SQLException {
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		int i = st.executeUpdate("update patient set patient_name = '" + data.get("name") + "', age = " + data.get("age") + ", weight = " + data.get("weight") + ", gender = '" + data.get("gender") + "', contact_number = '" + data.get("contactNumber") + "', address = '" + data.get("address") + "' where patient_id = " + data.get("id"));
		System.out.println("Data successfully updated...");
		return i;
	}

}
