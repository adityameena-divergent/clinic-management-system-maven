package com.divergentsl;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.divergentsl.dao.LabTestDao;

public class LabTest {

	public static void labTestPanel() {

		Exit: while (true) {

			Scanner sc = new Scanner(System.in);
			String input = sc.nextLine();

			switch (input) {

			case "1":
				addNewTest();
				break;

			case "2":
				readTest();
				break;

			case "3":
				listTest();
				break;

			case "4":
				deleteTest();
				break;

			case "5":
				updateTest();
				break;

			case "6":
				break Exit;

			}
		}
	}

	

	private static void printTestOptions() {

		System.out.println("\n----Lab Test Panel----");
		System.out.println("1. Add New Test");
		System.out.println("2. Read Test");
		System.out.println("3. List All Tests");
		System.out.println("4. Delete Test");
		System.out.println("5. Update Test");
		System.out.println("6. Exit");
		System.out.print("Enter Your Choice: ");
	}

	public static void addNewTest() {

		Scanner sc = new Scanner(System.in);

		System.out.println("\n----Adding Lab Test Data----");
		System.out.print("Enter Patient Id: ");
		String patient_id = sc.nextLine();

		System.out.print("\nEnter Test Name: ");
		String test_name = sc.nextLine();

		System.out.print("\nEnter Test Fee: ");
		String fee = sc.nextLine();

		try {
			LabTestDao labTestDao = new LabTestDao(new DatabaseManager());

			labTestDao.add(patient_id, test_name, fee);

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void readTest() {

		System.out.println("\n----Read Lab Test----");
		System.out.print("Enter Test Id: ");

		Scanner sc = new Scanner(System.in);
		String testId = sc.nextLine();

		try {

			LabTestDao labTestDao = new LabTestDao(new DatabaseManager());

			Map<String, String> data = labTestDao.search(testId);

			if (data.size() != 0) {
				System.out.println("Test id: " + data.get("testId"));
				System.out.println("Test Name: " + data.get("testName"));
				System.out.println("Patient Id: " + data.get("patientId"));
				System.out.println("Test Fee: " + data.get("fee"));
			} else {
				System.out.println("\nTest not found!");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void listTest() {

		System.out.println("\n----List Tests----");

		try {
			
			LabTestDao labTestDao = new LabTestDao(new DatabaseManager());
			
			List<Map<String, String>> list = labTestDao.listTest();
			
			System.out.printf("%7s | %12s | %10s | %12s | %14s | %8s |", "Test Id", "Test Name", "Patient Id",
					"Patient Name", "Contact Number", "Test Fee");
			System.out.println(
					"\n--------------------------------------------------------------------------------------------");
			for(Map<String, String> data: list) {
				System.out.printf("%7s | %12s | %10s | %12s | %14s | %8s |\n", data.get("testId"), data.get("testName"),
						data.get("patientId"), data.get("patientName"), data.get("contactNumber"), data.get("fee"));
			}
			System.out.println(
					"--------------------------------------------------------------------------------------------");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void deleteTest() {

		System.out.print("\nEnter Test Id: ");
		Scanner sc = new Scanner(System.in);

		String testId = sc.nextLine();
		
		LabTestDao labTestDao = new LabTestDao(new DatabaseManager());
		
		try {
			
			if (labTestDao.search(testId).size() != 0) {
				labTestDao.delete(testId);
			} else {
				System.out.println("\nTest data not found!");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void updateTest() {

		System.out.println("\n----Update Test Data----");
		System.out.print("Enter Test Id; ");
		Scanner sc = new Scanner(System.in);

		String testId = sc.nextLine();

		try {
			LabTestDao labTestDao = new LabTestDao(new DatabaseManager());
			Map<String, String> data = labTestDao.search(testId);
			if (data.size() != 0) {
				
				System.out.println("\n----Previous Data----");
				System.out.println("Previous Test Name: " + data.get("testName"));
				System.out.println("Previous Patient Id: " + data.get("patientId"));
				System.out.println("Previous Patient Name: " + data.get("patientName"));
				System.out.println("Previous Test Fee: " + data.get("fee"));
				
				System.out.print("\nEnter New Test Name: ");
				data.put("testName", sc.nextLine());

				System.out.print("\nEnter New Patient Id: ");
				data.put("patientId", sc.nextLine());
				
				
				System.out.print("\nEnter New Test Fee: ");
				data.put("fee", sc.nextLine());
				
				labTestDao.update(data);
				
			} else {
				System.out.println("\nTest data not found!");
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
}
