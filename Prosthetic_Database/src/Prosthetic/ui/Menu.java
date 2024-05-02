package Prosthetic.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import Prosthetic.db.interfaces.*;
import Prosthetic.db.jdbc.*;
import Prosthetic.db.pojos.*;


public class Menu {

	private static BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private static ConnectionManager conMan;
	private static CompanyManager comMan;
	private static MaterialManager matMan;
	private static NeedManager needMan;
	private static OptionManager optMan;
	private static PatientManager patientMan;
	private static ProstheticManager prosthMan;
	private static SurgeonManager surgeonMan;
	private static SurgeryManager surgeryMan;
	
	public static void main(String[] args) throws NumberFormatException, IOException{
		System.out.println("WELCOME TO THE PROSTHETIC DATABSE");
		
		conMan = new ConnectionManager();
		comMan = conMan.getcomMan();
		matMan = conMan.getmaterialMan();
		needMan = conMan.getneedMan();
		optMan = conMan.getoptionMan();
		prosthMan = conMan.getprosMan();
		surgeonMan = conMan.getsurgeonMan();
		surgeryMan = conMan.getsurgeryMan();
		
		prostheticMenu();
		
	}
	
	private static void prostheticMenu() throws NumberFormatException, IOException{
		//TODO method
		System.out.println("Choose your role");
		System.out.println("1. Manager");
		System.out.println("2. Patient");
		System.out.println("2. Surgeon");
		System.out.println("4. Company/Engineer");
		System.out.println("0. Exit");

		int option = Integer.parseInt(r.readLine());
		
		switch(option) {
		case 1: {
			break;
		}
		case 2: {
			patientMenu();
			break;
		}
		case 3:{
			break;
		}
		case 4: {
			break;
		}
		case 0: {
			conMan.close();
			return;
		}
		
		}
	}
	
	private static void patientMenu() throws NumberFormatException, IOException{
		System.out.println("Welcome Patient, choose what do you want to do");
		System.out.println("1. Get information: ");
		System.out.println("2. Input need");
		System.out.println("0. Exit");
		
		int option = Integer.parseInt(r.readLine());
		
		switch(option) {
		case 1: {
			getPatientByID();
			break;
		}
		case 2: {
			addNeed();
			break;
		}
		case 0: {
			return;
		}
	
		}	
	}
	
	private static void getPatientByID() throws NumberFormatException, IOException {
		System.out.println("Please enter the patient's ID: ");
		int id = Integer.parseInt(r.readLine());
		Patient patient = patientMan.getPatientByID(id);
		System.out.println(patient);
	}
	
	private static void addNeed() throws NumberFormatException, IOException{
		System.out.println("Please add the need information:");
		System.out.println("Type: ");
		String type = r.readLine();
		Need need = new Need(type);
		needMan.addNeed(need);
		
	}
	
	
	
}
