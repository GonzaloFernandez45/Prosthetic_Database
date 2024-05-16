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
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.*;
import Prosthetic.db.jdbc.*;
import Prosthetic.db.jpa.JPAUserManager;
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
	private static UserManager userMan;
	
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
		userMan = new JPAUserManager();
		
		userMenu();
		
		
	}
	
	private static void userMenu() throws NumberFormatException, IOException {
		int option = -1;
		while(option !=0) {
			System.out.println("Choose your desire option: ");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("3. Modify information");
			System.out.println("0. Exit Database");
			
			option = Integer.parseInt(r.readLine());
			
			
			switch(option) {
			case 1: {
				 	menuLogin();
				 	break;
				}
			case 2: {
					menuRegister();
					break;
				}
			case 3: {
					modifyUser();
					break;
				}
			case 0: {
				System.out.println("Thank you for using the databse!");	
				conMan.close();
					return;
				}
			}
		}
	}
	private static User readUser() throws NumberFormatException, IOException{
		System.out.print("Username: ");
		String username = r.readLine();
		System.out.print("Password: ");
		String password = r.readLine();
		User u  = userMan.login(username, password);
		return u;
	}
	private static void menuLogin() throws NumberFormatException, IOException {
		User u = readUser();
				if (u == null) {
					System.out.println("User not found, try again");
				}else {
					System.out.println(u);
					prostheticMenu(u);
				}
				
	}
	
	private static void menuRegister() throws NumberFormatException, IOException {
	System.out.print("Choose a username: ");
	String username = r.readLine();
	System.out.print("Choose a password: ");
	String password = r.readLine();
	int passwordHash = password.hashCode();
	System.out.print("Choose your role by its name: \n" );
	List<Role> roles = userMan.getAllRoles();
	for (Role r : roles) {
		System.out.print(r);
	}
	String roleName = r.readLine();
	Role r = userMan.getRole(roleName);
	User u = new User(username, passwordHash, r);
	userMan.register(u);
	System.out.print("New user added correctly");
	
	}
	
	private static void modifyUser() throws NumberFormatException, IOException{
		User u = readUser();
		System.out.print("Dedice what you want to do: ");
		System.out.print("1. Change your password. ");
		System.out.print("2. Delete your user. ");
		int option = Integer.parseInt(r.readLine());
		switch(option) {
		case 1: {
			changePassword(u);
			break;
		}
		case 2: {
			deleteUser(u);
			break;
		}
		case 0: {
			return;
		}
		}
	}
	
	private static void changePassword (User u) throws NumberFormatException, IOException {
		System.out.print("Type your new password. ");
		String newPassword = r.readLine();
		int newPasswordHash = newPassword.hashCode();
		u.setPasswordHash(newPasswordHash);
		userMan.updateUser(u,newPasswordHash);

	}
	
	private static void deleteUser (User u) throws NumberFormatException, IOException {
		userMan.deleteUser(u);
		System.out.print("User deleted correctly. ");
		
	}
	
	private static void prostheticMenu(User u) throws NumberFormatException, IOException{
		//TODO method
		
		int option = u.getRole().getId();		
		
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
			surgeonMenu();
			break;
		}
		case 4: {
			companyMenu();
			break;
		}
		case 0: {
			conMan.close();
			return;
		}
						}
	}		

	
	private static void patientMenu() throws NumberFormatException, IOException{
		
		int option = -1;
		while(option!=0) {
			
		System.out.println("Welcome Patient, choose what do you want to do");
		System.out.println("1. Get information: ");
		System.out.println("2. Input option");
		System.out.println("3. Report delivery of prosthetic");
		System.out.println("0. Exit");
		
		option = Integer.parseInt(r.readLine());
		
		
		switch(option) {
		case 1: {
			getPatientByID();
			break;
		}
		case 2: {
			inputOption();
			break;
		}
		case 3: {
			reportDelivery();
			break;
		}
		case 0: 
			return;
		}
		}
	}
	
	private static void managerMenu() throws NumberFormatException, IOException{
		
		int option = -1;
		while(option!=0) {
		
		
		System.out.println("Welcome Manager, choose what do you want to do");
		System.out.println("1. Add surgeon");
		System.out.println("2. Add company");
		System.out.println("3. Delete surgeon");
		System.out.println("4. Delete company");
		System.out.println("5. See all patients");
		System.out.println("6. See all surgeons");
		System.out.println("7. See all companies");
		System.out.println("0. Exit");
		option = Integer.parseInt(r.readLine());
		
		
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
			printPatients();
			break;
		}
		case 6: {
			printSurgeons();
			break;
		}
		case 7: {
			printCompanies();
			break;
		}
		
		case 0: 
			
			return;
		}
		}	
		
	}
	
	private static void companyMenu() throws NumberFormatException, IOException{
		
		int option = -1;
		while(option!=0) {
		System.out.println("Welcome Company, choose what do you want to do");
		System.out.println("1. Create prosthetic");
		System.out.println("2. Check demand");
		System.out.println("3. Check the needs of a patient");
		System.out.println("4. Add material ");
		System.out.println("5. List all of the prosthetics");
		
		System.out.println("0. Exit");
		option = Integer.parseInt(r.readLine());
		
		
		switch(option) {
		case 1: {
			addProsthetic();
			break;
		}
		case 2: {
			checkDemand();
			break;
		}
		case 3: {
			checkPatientNeeds();
			break;
		}
		case 4: {
			addMaterial();
			break;
		}
		case 5: {
			printProsthetics();
			break;
		}
		case 0: 
			
			return;
		}
		}
	}
	private static void surgeonMenu() throws NumberFormatException, IOException{
		int option = -1;
		while(option!=0) {
		System.out.println("Welcome Surgeon, choose what do you want to do");
		System.out.println("1. Add a new patient ");
		System.out.println("2. Login to an existing patient");
		System.out.println("0. Exit");
		
		option = Integer.parseInt(r.readLine());
		
		
		switch(option) {
		case 1:
			addPatient();
			break;
		case 2:
			loginToAnExistingPatient();
			break;
		case 0:
			
			break;
		}
	}
	}
	private static void loginToAnExistingPatient() throws NumberFormatException, IOException{
		int option = -1;
		while(option!=0) {
		System.out.println("Welcome,choose an option");
		System.out.println("1. Schedule surgery ");
		System.out.println("2. Surgery result");
		System.out.println("3. Input needs");
		System.out.println("0. Exit");
		
		option = Integer.parseInt(r.readLine());
		
		
		switch(option) {
		case 1:
			scheduleSurgery();
			break;
		case 2:
			surgeryResult();
			break;
		case 3:
			inputNeed();
			break;
		case 0:
			
			break;
		}
		}
	}
	
	private static void reportDelivery() throws NumberFormatException, IOException {
//		System.out.println("Please enter the patient's ID: ");
//		int id = Integer.parseInt(r.readLine());
//		String report = patMan.reportDelivery(id);
//		System.out.println(report);
		
	}
	
	private static void getPatientByID() throws NumberFormatException, IOException {
		System.out.println("Please enter the patient's ID: ");
		int id = Integer.parseInt(r.readLine());
		Patient patient = patMan.getPatientByID(id);
		List<Prosthetic> prosthetics = prosthMan.getProstheticbyPatient(patient);
		patient.setProsthetics(prosthetics);
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
		Patient patient = new Patient(name,surname,sex,dob,dni);
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
		String location = r.readLine();
		Company company = new Company(name,location);
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
	
	private static void  addProsthetic() throws NumberFormatException, IOException {
		System.out.println("Add prosthetic information");
		System.out.println("Size:");
		String size = r.readLine();
		
		System.out.println("Select the company´s ID");
		System.out.println(comMan.listCompaniesIDandName());
		int company_id = Integer.parseInt(r.readLine());
		Company company = comMan.getCompany(company_id);
		
		
		System.out.println("Select the patient´s ID");
		System.out.println(patMan.getPatientByIDandName());
		int patient_id = Integer.parseInt(r.readLine());
		Patient patient = patMan.getPatientByID(patient_id);
		
		System.out.println("Select the need´s ID");
		System.out.println(needMan.listNeeds());
		int need_id = Integer.parseInt(r.readLine());
		Need need = needMan.getNeed(need_id);
		
		System.out.println("Select the material´s ID");
		System.out.println(matMan.listMaterials());
		int material_id = Integer.parseInt(r.readLine());
		System.out.println("Checking if the materials are available for this prosthetic...");
		String availability = matMan.checkAvailability(material_id);
		if(availability.equalsIgnoreCase("yes")) {
			System.out.println("...they are available!");
		}
		else if (availability.equalsIgnoreCase("no")) {
			System.out.println("The prosthetic couldnt be created as there are no materials available");
			
		return;
		}
		Material material = matMan.getMaterial(material_id);
		
		System.out.println("prize:");
		int price = Integer.parseInt(r.readLine());
		
		Prosthetic prosthetic = new Prosthetic(size,company,patient,need,price,material,"NO");
		prosthetic.setID(conMan.getPKofLastInsertedRow());
		prosthMan.addProsthetic(prosthetic);
		System.out.println("A new prosthetic has been succesfully created");
	}
	
	
	private static void checkDemand() throws NumberFormatException, IOException {
		
		List<Prosthetic> prosthetics = new ArrayList<>();

		for (Prosthetic prosthetic : prosthMan.listProsthetics()){
			
			if (prosthetic.getReport().equalsIgnoreCase("no")) {
                prosthetics.add(prosthetic);
                
			}
			
		}
		if (prosthetics.isEmpty()){
			System.out.println("Currently there is no demand");
		}
		else {
			System.out.println("These are the undelivered prosthetics");
			for (Prosthetic p: prosthetics) {
				System.out.println(p);
			}
		}
			
	}
	
	private static void printSurgeons() throws NumberFormatException, IOException{
		List<Surgeon> surgeons = new ArrayList<>();
		for(Surgeon surgeon : surgeonMan.listSurgeons()) {
			surgeons.add(surgeon);
		}
		for(Surgeon s : surgeons) {
			System.out.println(s);
		}
	}
	
	private static void printPatients() throws NumberFormatException, IOException{
		List<Patient> patients = new ArrayList<>();
		for(Patient patient : patMan.listPatients()) {
			patients.add(patient);
		}
		for(Patient p : patients) {
			System.out.println(p);
		}
	}
	
	private static void printCompanies() throws NumberFormatException, IOException{
		List<Company> companies = new ArrayList<>();
		for(Company company : comMan.listCompanies()) {
			companies.add(company);
		}
		for(Company c : companies) {
			System.out.println(c);
		}
	}
	
	private static void checkPatientNeeds() throws NumberFormatException, IOException {
		
		System.out.println("Select the patient´s ID");
		System.out.println(patMan.getPatientByIDandName());
		int patient_id = Integer.parseInt(r.readLine());
		Patient patient = patMan.getPatientByID(patient_id);
		List<Need> patientNeeds = needMan.getNeedByPatient(patient_id);
		patient.setNeeds(patientNeeds);
		System.out.println(patientNeeds);
		System.out.println("Name: "+patient.getName()+", Surname: "+patient.getSurname());
		for (Need need: patientNeeds) {
			System.out.println(need);
			}
		
		
	}
	
	private static void inputOption() throws NumberFormatException, IOException{
		System.out.println("Please enter the patient's ID: ");
		int id = Integer.parseInt(r.readLine());

			System.out.println("INSERT THE OPTION: ");
			System.out.println("SELECT ALREADY CREATED OPTIONS (Press 0): ");
			for (Option options: optMan.listOptions()) {
				System.out.println(options);
				}
			System.out.println("ADD A NEW ONE (Press 1): ");
			int option = Integer.parseInt(r.readLine());
			switch(option) {
				case 0: {
					System.out.println("Please select the wanted option by its ID: ");
					int finalOption = Integer.parseInt(r.readLine());
					Patient patient = patMan.getPatientByID(id);
					List<Prosthetic> prosthetics = prosthMan.getProstheticbyPatient(patient);
					if(!prosthetics.isEmpty()) {
					System.out.println("Select the prosthetic where you want to add the option");
					printProstheticsOfAPatient(patient);
					int prostheticOption = Integer.parseInt(r.readLine());
					Prosthetic prosthetic = prosthMan.getProstheticByID(prostheticOption);
					Option o = optMan.getOption(finalOption);
					optMan.insertFulfill(o,prosthetic);
					System.out.println("Option added correctly");
					break;
					}else {
						System.out.println("There are no prosthetics");
						break;
					}
				}
				case 1: {
					System.out.println("Please add the new option info: ");
					System.out.println("Type:");
					String type = r.readLine();
					Option newOption = new Option(type);
					optMan.addOption(newOption);
					
					Patient patient = patMan.getPatientByID(id);
					List<Prosthetic> prosthetics = prosthMan.getProstheticbyPatient(patient);
					System.out.println("Select the prosthetic where you want to add the option");
					for (Prosthetic prosth: prosthetics) {
						System.out.println(prosth);
						}
					int prostheticOption = Integer.parseInt(r.readLine());
					Prosthetic prosthetic = prosthMan.getProstheticByID(prostheticOption);
					Option o = optMan.getOptionByType(type);
					optMan.insertFulfill(o,prosthetic);
					System.out.println("Option added correctly");
					break;
				}
			}
		}
	private static void scheduleSurgery() throws NumberFormatException, IOException{
		
		System.out.println("Choose the id of your patient:");
	    System.out.println(patMan.getPatientByIDandName());
		int patient_id= Integer.parseInt(r.readLine());
		PatientManager patient_manager=conMan.getpatientMan();
		Patient patient =patient_manager.getPatientByID(patient_id);
		
		
		System.out.println("Please add the surgery info: ");
		System.out.println("Time: ");
		String time = r.readLine();
		System.out.println("Date (DD-MM-YYYY format): ");
		LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
		Date date = Date.valueOf(localDate);
		System.out.println("Room: ");
		int room = Integer.parseInt(r.readLine());
		
		System.out.println("Choose the ID of the prosthetic: ");
		List<Prosthetic>prosthetics= prosthMan.getProstheticbyPatient(patient);
		System.out.println(prosthMan.getProstheticbyPatient(patient));
		int prosthetic_id = Integer.parseInt(r.readLine());
		ProstheticManager pm= conMan.getprosMan();
		Prosthetic prosthetic= pm.getProstheticByID(prosthetic_id);

		if(!prosthetics.isEmpty()) {
		System.out.println("Choose the ID of the surgeon: ");
		System.out.println(surgeonMan.listSurgeonIDandName());
		int surgeon_id = Integer.parseInt(r.readLine());
		SurgeonManager surgeonMan = conMan.getsurgeonMan();
		Surgeon surgeon = surgeonMan.getSurgeon(surgeon_id);
		
		System.out.println("Result (Completed/Not completed): ");//TODO 
		String result  = r.readLine();
		Surgery surgery = new Surgery(time,date,room,surgeon,prosthetic,result);
		surgery.setProsthetic(prosthetic);
		surgeryMan.addSurgery(surgery);
		prosthetic.setSurgery(surgery);
		
		
		System.out.println("Surgery scheduled correctly");
		System.out.println("Surgery id: "+surgery.getId());
		
		
		}else {
			System.out.println("Patient doesn't have a prosthetic. Can't schedule the surgery");
		}
		
	}
	private static void surgeryResult () throws NumberFormatException, IOException{
		
		System.out.println("Input the surgery's date (DD-MM-YYYY format): ");
		List<Surgery> surgeries = surgeryMan.listSurgeries();
		System.out.println(surgeries);
		if(!surgeries.isEmpty()) {
			System.out.println("No surgeries scheduled");
		}
		else {
			LocalDate localDate = LocalDate.parse(r.readLine(), formatter);
			Date date = Date.valueOf(localDate);
			System.out.println(surgeryMan.searchSurgerybyDate(date));
			System.out.println("Enter the id of the surgery");
			int surgery_id = Integer.parseInt(r.readLine());
			String result = surgeonMan.resultSurgery(surgery_id);
			System.out.println(result);
			
		}
			
	}
	
	private static void inputNeed() throws NumberFormatException, IOException{
		System.out.println("Please enter the patient's ID: ");
		System.out.println(patMan.getPatientByIDandName());
		int id = Integer.parseInt(r.readLine());

			System.out.println("Insert the need: ");
			System.out.println("Select already created needs (Press 0): ");
			System.out.println(needMan.listNeeds());
			System.out.println("Add a new need (Press 1): ");
			int option = Integer.parseInt(r.readLine());
			switch(option) {
				case 0: {
					System.out.println("Please select the wanted need by its ID: ");
					int need_id = Integer.parseInt(r.readLine());
					Patient patient = patMan.getPatientByID(id);
					List<Need> patientNeeds = patient.getNeeds();
					patientNeeds.add(needMan.getNeed(need_id));
					patient.setNeeds(patientNeeds);
					List<Prosthetic> prosthetics = prosthMan.getProstheticbyPatient(patient);
					 if(prosthetics.isEmpty()) {
						 System.out.println("No prosthetics found for the patient");
						 break;
					 }else {
						 System.out.println("Choose the id of one of the patient's prosthetic");
						 System.out.println(prosthMan.getProstheticbyPatient(patient));
						 int prosthetic_id=Integer.parseInt(r.readLine());
						 for(Prosthetic prosthetic:prosthetics) {
							 if(prosthetic.getID()==prosthetic_id) {
								 NeedManager needManager= conMan.getneedMan();
								 Need need = needManager.getNeed(need_id);
								 prosthetic.setNeed(need);
							 }
						 }
						 System.out.println("Need added correctly");
						 break;
						 
					 }
			
				}
				case 1: {
					System.out.println("Please add the new need info: ");
					System.out.println("Type:");
					String type = r.readLine();
					Need need = new Need(type);
					needMan.addNeed(need);
					Patient patient = patMan.getPatientByID(id);
					List<Prosthetic> prosthetics = prosthMan.getProstheticbyPatient(patient);
					 if(prosthetics.isEmpty()) {
						 System.out.println("No prosthetics found for the patient");
						 break;
					 }else {
						 System.out.println("Choose the id of one of the patient's prosthetic");
						 System.out.println(prosthMan.getProstheticbyPatient(patient));//
						 int prosthetic_id=Integer.parseInt(r.readLine());
						 for(Prosthetic prosthetic:prosthetics) {
							 if(prosthetic.getID()==prosthetic_id) {
								 prosthetic.setNeed(need);
							 }
					 }
						 System.out.println("Need added correctly");
						 break;	 
					
				}
			}
		
	}		
	}
	
	private static void printProsthetics() throws NumberFormatException, IOException{
		List<Prosthetic> prosthetics = prosthMan.listProsthetics();
		
		for(Prosthetic prosthetic : prosthetics ) {
//			int prosthetic_id = prosthetic.getID();
//			Surgery surgery= surgeryMan.getSurgeryByProsthetic(prosthetic_id);
//			prosthetic.setSurgery(surgery);
			System.out.println(prosthetic);
		}
	}
	
	private static void printProstheticsOfAPatient(Patient patient) {
		List<Prosthetic> prosthetics = prosthMan.getProstheticbyPatient(patient);
		for(Prosthetic p : prosthetics) {
			System.out.println(p);
		}
	}
	
	
}
	

	

