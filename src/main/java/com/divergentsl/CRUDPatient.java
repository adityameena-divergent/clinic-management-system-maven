package com.divergentsl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.divergentsl.dao.PatientDao;

public class CRUDPatient {


    public static void updatePatient() {

        System.out.println("\n----Update Patient Data----");
        System.out.print("Enter Patient Id: ");
        Scanner sc = new Scanner(System.in);
        String patientId = sc.nextLine();

        try {
        	
        	PatientDao patientDao = new PatientDao(new DatabaseManager());
        	
        	Map<String, String> data = patientDao.search(patientId);

            if(data.size() == 0) {
                System.out.println("\nPatient Not Found!");
                return;
            }

            System.out.println("\nPatient Id: " + patientId);
            System.out.println("Patient Name: " + data.get("name"));
            System.out.println("Patient Age: " + data.get("age"));
            System.out.println("Patient Weight: " + data.get("weight"));
            System.out.println("Patient Gender: " + data.get("gender"));
            System.out.println("Patient Contact Number: " + data.get("contactNumber"));
            System.out.println("Patient Address: " + data.get("address"));

            System.out.print("\nEnter New Name: ");
            data.put("name", sc.nextLine());
            System.out.print("\nEnter New Age: ");
            data.put("age", sc.nextLine());
            System.out.print("\nEnter New Weight: ");
            data.put("weight", sc.nextLine());
            System.out.print("\nEnter New Gender: ");
            data.put("gender", sc.nextLine());
            System.out.print("\nEnter New Contact Number: ");
            data.put("contactNumber", sc.nextLine());
            System.out.print("\nEnter New Address: ");
            data.put("address", sc.nextLine());
            data.put("id", patientId);
            patientDao.update(data);
        } catch(SQLException e) {
        	e.printStackTrace();
        }

    }



    public static void deletePatient() {

        System.out.println("\n----Delete Patient Data----");
        System.out.print("Enter Patient Id: ");
        Scanner sc = new Scanner(System.in);
        String patientId = sc.nextLine();

        try {
        	PatientDao patientDao = new PatientDao(new DatabaseManager());
        	
        	if (patientDao.search(patientId).size() != 0)
        		patientDao.delete(patientId);
        	else
        		System.out.println("\nPatient not found!");
        	
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public static void printPatientOptions() {
        System.out.println("\n\n----Patient Panel----");
        System.out.println("1. Add new patient");
        System.out.println("2. Update patient data");
        System.out.println("3. List all patient data");
        System.out.println("4. Search patient");
        System.out.println("5. Delete patient");
        System.out.println("6. Back");
        System.out.print("Enter your choice: ");
    }


    public static void CRUDOperation() {
        CRUD:
        while(true) {
            printPatientOptions();

            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            switch (input) {

                case "1":
                    insert();
                    break;

                case "2":
                    updatePatient();
                    break;

                case "3":
                    listAll();
                    break;

                case "4":
                    search();
                    break;

                case "5":
                    deletePatient();
                    break;

                case "6":
                    break CRUD;

                default:
                    System.out.println("\nOperation not available");
                    break;
            }
        }
    }


    


    public static void insert() {
        Scanner sc = new Scanner(System.in);

        Map<String, String> map = new HashMap<>();

        System.out.println("\n----Add Patient----");
        System.out.print("Enter Patient Name: ");
        String patient_name = sc.nextLine();
        map.put("patient_name", patient_name);

        System.out.print("\nEnter Age: ");
        String age = sc.nextLine();
        map.put("age", age);

        System.out.print("\nEnter Weight: ");
        String weight = sc.nextLine();
        map.put("weight", weight);

        System.out.print("\nEnter Gender: ");
        String gender = sc.nextLine();
        map.put("gender", gender);

        System.out.print("\nEnter Contact Number: ");
        String contact_number = sc.nextLine();
        map.put("contact_number", contact_number);

        System.out.print("\nEnter Address: ");
        String address = sc.nextLine();
        map.put("address", address);

        
        try {
        	PatientDao patientDao = new PatientDao(new DatabaseManager());
        	patientDao.insert(map);
        } catch(SQLException e) {
        	e.printStackTrace();
        }
    }



    public static void search() {

        try {
        	Scanner sc = new Scanner(System.in);

            System.out.println("\n\n----Search Patient----");
            System.out.print("\nEnter Patient Id: ");
            String patientId = sc.nextLine();
            
            PatientDao patientDao = new PatientDao(new DatabaseManager());
            
            Map<String, String> data = patientDao.search(patientId);
            
            if (data.size() != 0) {
            	System.out.println("\nPatient Id: " + data.get("id"));
            	System.out.println("Patient Name: " + data.get("name"));
            	System.out.println("Age: " + data.get("age"));
            	System.out.println("Weight: " + data.get("weight"));
            	System.out.println("Gender: " + data.get("gender"));
            	System.out.println("Contact Number: " + data.get("contactNumber"));
            	System.out.println("Address: " + data.get("address"));
            } else {
            	System.out.println("\nData not found!");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    
    public static void listAll() {

        try {
        	PatientDao patientDao = new PatientDao(new DatabaseManager());
        	
        	List<Map<String, String>> list = patientDao.listAll();
        	
            System.out.println("\n\n----Patient List----\n");
            System.out.printf("%10s  %15s     %3s     %6s     %6s   %14s  %15s\n","Patient_Id", "Patient_Name", "Age", "Weight", "Gender", "Contact_Number", "Address");
            System.out.println("----------------------------------------------------------------------------------------------------------------");
            
            
            for(Map<String, String> record: list) {
                System.out.printf("%10s  %15s     %3s     %6s     %6s   %14s  %15s\n", record.get("id"), record.get("name"), record.get("age"), record.get("weight"), record.get("gender"), record.get("contactNumber"), record.get("address"));
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
