package Prosthetic.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
	private static PatientManager patMan;
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
		patMan = conMan.getpatientMan();
		
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
			managerMenu();
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
		System.out.println("2. Input option");
		System.out.println("3. Report delivery of prosthetic");
		System.out.println("0. Exit");
		
		int option = Integer.parseInt(r.readLine());
		
		switch(option) {
		case 1: {
			getPatientByID();
			break;
		}
		case 2: {
			//addNeed(); ESTE METODO CORRESPONDE A SURGEON CON LA NUEVA MODIFICACIÓN
			break;
		}
		case 3: {
			
			break;
		}
		case 0: 
			conMan.close();
			return;
		}
	
	}
	
	private static void managerMenu() throws NumberFormatException, IOException{
		System.out.println("Welcome Manager, choose what do you want to do");
		System.out.println("1. Add surgeon");
		System.out.println("2. Add company");
		System.out.println("3. Delete surgeon");
		System.out.println("4. Delete company");
		System.out.println("5. See all patients");
		System.out.println("6. See all surgeons");
		System.out.println("7. See all companies");
		System.out.println("0. Exit");
		int option = Integer.parseInt(r.readLine());
		switch(option) {
		case 1: {
			addSurgeon();
			break;
		}
		case 2: {
			addCompany();
			break;
		}
		case 3: {
			deleteSurgeon();
			break;
		}
		case 4: {
			deleteCompany();
			break;
		}
		case 5: {
			patMan.listPatients();
			break;
		}
		case 6: {
			surgeonMan.listSurgeons();
			break;
		}
		case 7: {
			comMan.listCompanies();
			break;
		}
		
		case 0: conMan.close();
			return;
		}
		
		
	}
	
	private static void reportDelivery() throws NumberFormatException, IOException {
		System.out.println("Please enter the patient's ID: ");
		int id = Integer.parseInt(r.readLine());
		
	}
	
	private static void getPatientByID() throws NumberFormatException, IOException {
		System.out.println("Please enter the patient's ID: ");
		int id = Integer.parseInt(r.readLine());
		Patient patient = patMan.getPatientByID(id);
		System.out.println(patient);
	}
	
	private static void addNeed() throws NumberFormatException, IOException{
		System.out.println("Please add the need information:");
		System.out.println("Type: ");
		String type = r.readLine();
		Need need = new Need(type);
		needMan.addNeed(need);
		
	}
	
	private static void addPatient() throws NumberFormatException, IOException{
		System.out.println("Please add the patient info: ");
		System.out.println("NAME: ");
		String name = r.readLine();
		System.out.println("SURNAME: ");
		String surname = r.readLine();
		System.out.println("SEX (ENTER MAN OR WOMEN): ");
		String sex = r.readLine();
		System.out.println("Date of Birth (DD-MM-YYYY format):");
		LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
		Date dob = Date.valueOf(localDate);
		System.out.println("DNI: ");
		Integer dni = Integer.parseInt(r.readLine());
		System.out.println("REPORT (Will be filled by the patient: ");
		String report = r.readLine();
		Patient patient = new Patient(name,surname,sex,dob,dni,report);
		patMan.addPatient(patient);
	}
	
	private static void addSurgeon() throws NumberFormatException, IOException{
		System.out.println("Please add the surgeon info: ");
		System.out.println("NAME: ");
		String name = r.readLine();
		System.out.println("SURNAME: ");
		String surname = r.readLine();
		System.out.println("SALARY: ");
		Integer salary = Integer.parseInt(r.readLine());
		System.out.println("HIREDATE (DD-MM-YYYY format): ");
		LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
		Date hiredate = Date.valueOf(localDate);
		System.out.println("SPECIALIZATION: ");
		String specialization = r.readLine();
		Surgeon surgeon = new Surgeon(name,surname,salary,hiredate,specialization);
		surgeonMan.addSurgeon(surgeon);
		
	}
	
	private static void addCompany() throws NumberFormatException, IOException{
		System.out.println("Please add the company info: ");
		System.out.println("NAME: ");
		String name = r.readLine();
		System.out.println("LOCATION: ");
		String localization = r.readLine();
		Company company = new Company(name,localization);
		comMan.addCompany(company);
		
	}

	private static void deleteSurgeon() throws NumberFormatException, IOException{
		System.out.println("Please enter the id of the surgeon you want to delete: ");
		surgeonMan.listSurgeonIDandName();
		int id = Integer.parseInt(r.readLine());
		surgeonMan.deleteSurgeon(id);
	}
	
	private static void deleteCompany() throws NumberFormatException, IOException{
		System.out.println("Please enter the name of the company you want to delete: ");
		comMan.listCompaniesIDandName();
		String name = r.readLine();
		comMan.deleteCompany(name);
	}

	
	private static void addMaterial() throws NumberFormatException, IOException{
		System.out.println("Please add the material info: ");
		System.out.println("ID: ");
		Integer id = Integer.parseInt(r.readLine());
		System.out.println("TYPE: ");
		String type = r.readLine();
		System.out.println("AVAILABILITY: ");
		String availability = r.readLine();
		Material material = new Material(id,type,availability);
		matMan.addMaterial(material);
	}
	
	private static void getReport() {
		//TODO method
	}
	
	
	
}
