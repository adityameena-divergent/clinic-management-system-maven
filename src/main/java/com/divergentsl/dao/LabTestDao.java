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

public class LabTestDao {

	IDatabaseManager databaseManager;

	public LabTestDao(IDatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}
	
	
	/**
	 * It is a helper method for add new lab test data
	 * @param patientId
	 * @param testName
	 * @param fee
	 * @throws SQLException
	 */
	public int add(String patientId, String testName, String fee) throws SQLException {

		Connection con = null;
		Statement st = null;

		con = databaseManager.getConnection();
		st = con.createStatement();

		int i = st.executeUpdate("insert into lab_test (test_name, patient_id, test_fee) values ('" + testName + "', "
				+ patientId + ", " + fee + ")");
		System.out.println("Test Added Successfully");
		st.close();
		con.close();
		return i;
	}
	
	
	/**
	 * It is a helper for search lab test data
	 * @param testId
	 * @return Map of lab test data if data is found otherwise return empty Map.
	 * @throws SQLException
	 */
	public Map<String, String> search(String testId) throws SQLException {

		Connection con = null;
		Statement st = null;

		con = databaseManager.getConnection();
		st = con.createStatement();

		ResultSet rs = st.executeQuery("select * from lab_test where test_id = " + testId);
		Map<String, String> data = new HashMap<>();

		if (rs.next()) {
			data.put("testId", rs.getString(1));
			data.put("testName", rs.getString(2));
			data.put("patientId", rs.getString(3));
			data.put("fee", rs.getString(4));
		}
		return data;
	}
	
	
	/**
	 * It is helper method for listing out all the lab test present in database.
	 * @return List of Map.
	 * @throws SQLException
	 */
	public List<Map<String, String>> listTest() throws SQLException {

		Connection con = null;
		Statement st = null;

		con = databaseManager.getConnection();
		st = con.createStatement();

		ResultSet rs = st.executeQuery(
				"select test_id, test_name, p.patient_id, p.patient_name, p.contact_number, test_fee from lab_test l join patient p on p.patient_id = l.patient_id");
		List<Map<String, String>> list = new ArrayList<>();

		while (rs.next()) {

			Map<String, String> data = new HashMap<>();
			data.put("testId", rs.getString(1));
			data.put("testName", rs.getString(2));
			data.put("patientId", rs.getString(3));
			data.put("patientName", rs.getString(4));
			data.put("contactNumber", rs.getString(5));
			data.put("fee", rs.getString(6));

			list.add(data);
		}
		return list;
	}
	
	
	public int delete(String testId) throws SQLException {
		
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		int i = st.executeUpdate("delete from lab_test where test_id = " + testId);
		System.out.println("Data deleted successfully...");
		
		st.close();
		con.close();
		return i;
	}
	
	
	public int update(Map<String, String> data) throws SQLException {
		
		Connection con = null;
		Statement st = null;
		
		con = databaseManager.getConnection();
		st = con.createStatement();
		
		int i = st.executeUpdate("update lab_test set test_name = '" + data.get("testName") + "', patient_id = " + data.get("patientId")
				+ ", test_fee = " + data.get("fee") + " where test_id = "
				+ data.get("testId"));
		System.out.println("\nData update successfully...");
		
		st.close();
		con.close();
		return i;
	}

}
