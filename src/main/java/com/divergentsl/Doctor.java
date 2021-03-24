package com.divergentsl;

import java.sql.*;
import java.util.Scanner;

import com.divergentsl.dao.LoginDao;

public class Doctor {

    public static void printDoctorOptions(String dname) {

        System.out.println("\n----Login as : " + dname + "----");
        System.out.println("----Doctor Panel----");
        System.out.println("1. List of patients");
        System.out.println("2. Add prescription and notes for a patient");
        System.out.println("3. See booked appointments for him");
        System.out.println("4. Check patient history and his prescription");
        System.out.println("5. Create Invoice of patient");
        System.out.println("6. Logout");
        System.out.print("Enter Your Choice: ");

    }



    public static void doctorPanel(String doctorId, String doctorName) {

        Logout:
        while(true) {

            printDoctorOptions(doctorName);

            Scanner sc = new Scanner(System.in);

            String input = sc.nextLine();

            switch (input) {

                case "1":
                	PatientAppointment.allPatientList(doctorName);
                    break;

                case "2":
                    PatientAppointment.addPrescription();
                    break;

                case "3":
                    PatientAppointment.patientAppointToYou(doctorId, doctorName);
                    break;

                case "4":
                    PatientAppointment.checkPatientHistory();
                    break;

                case "5":
                    PatientAppointment.generateInvoice();
                    break;

                case "6":
                    break Logout;

            }
        }
    }


    public static String doctorLogin() {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n----Doctor Login----");
        System.out.print("Enter Username: ");
        String username = sc.nextLine();
        System.out.print("\nEnter Password: ");
        String password = sc.nextLine();

        try {
            
        	LoginDao loginDao = new LoginDao(new DatabaseManager());
        	
        	String doctorName = loginDao.doctorLogin(username, password);
        	
            if(doctorName != null) {
                System.out.println("\nDoctor Login Successful");
                return doctorName;
            } else {
                System.out.println("\nIncorrect Username & Password");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


}
