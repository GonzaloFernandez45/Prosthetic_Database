package Prosthetic.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import Prosthetic.db.interfaces.*;



public class ConnectionManager {
	private Connection c;
	private CompanyManager conMan;
	private OptionManager opMan;
	private NeedManager neMan;
	private PatientManager patMan;
	private ProstheticManager prosMan;
	private SurgeonManager surgeonMan;
	private SurgeryManager surgeryMan;
	
	public Connection getConnection() {
		return c;
	}
	
	private void connect() {
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:./db/Prosthetic.db");
			c.createStatement().execute("PRAGMA foreign_keys=ON");
		} catch (ClassNotFoundException cnfE) {
			System.out.println("Databases prosthetic not loaded");
			cnfE.printStackTrace();
		} catch (SQLException sqlE) {
			System.out.println("Error with database");
			sqlE.printStackTrace();
		}
	}
	
	public void close() {
		try {
			c.close();
		} catch (SQLException e) {
			System.out.println("Error closing the database");
			e.printStackTrace();
		}
	}
	
	private void createTables() {
		try {
			Statement createTables1 = c.createStatement();
			String create1 = "CREATE TABLE company ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT"
					+ "name TEXT NOT NULL"
					+ "location TEXT NOT NULL)";
			createTables1.executeUpdate(create1);
			createTables1.close();
			
			Statement createTables2 = c.createStatement();
			String create2 = "CREATE TABLE need ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT"
					+ "type TEXT NOT NULL)";
			createTables2.executeUpdate(create2);
			createTables2.close();
			
			Statement createTables3 = c.createStatement();
			String create3 = "CREATE TABLE option ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT"
					+ "type TEXT NOT NULL)";
			createTables3.executeUpdate(create3);
			createTables3.close();
			
			Statement createTables4 = c.createStatement();
			String create4 = "CREATE TABLE patient ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT"
					+ "name TEXT NOT NULL"
					+ "surname TEXT NOT NULL"
					+ "sex SEX"
					+ "DOB DATE NOT NULL"
					+ "dni INTEGER NOT NULL"
					+ "report TEXT NOT NULL DEFAULT 'NO')";
			createTables4.executeUpdate(create4);
			createTables4.close();
			
			Statement createTables5 = c.createStatement();
			String create5 = "CREATE TABLE prosthetic ("
					+ "id INTEGER PRIAMRY KEY AUTOINCREMENT"
					+ "size TEXT NOT NULL"
					+ "Company_ID INTEGER REFERENCES company(ID) ON DELETE SET NULL"
					+ "Patient_ID INTEGER REFERENCES patient(ID) ON DELETE RESTRICT"
					+ "Need_ID INTEGER REFERENCES need(ID) ON DELETE RESTRICT )";
			createTables5.executeUpdate(create5);
			createTables5.close();
			
			Statement createTables6 = c.createStatement();
			String create6 = "CREATE TABLE surgeon ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT"
					+ "name TEXT NOT NULL"
					+ "surname TEXT NOT NULL"
					+ "salary INTEGER"
					+ "hiredate DATE NOT NULL"
					+ "specialization TEXT)";
			createTables6.executeUpdate(create6);
			createTables6.close();
			
			Statement createTables7 = c.createStatement();
			String create7 = "CREATE TABLE surgery ("
					+ "id INTEGER PRIAMRY KEY AUTOINCREMENT"
					+ "Time DATE"
					+ "Date DATE"
					+ "OR INTEGER"
					+ "Patient_ID INTEGER REFERENCES patient(ID) ON DELETE RESTRICT"
					+ "Surgeon_ID INTEGER REFERENCES surgeon(ID) ON DELETE SET NULL"
					+ "Need_ID INTEGER REFERENCES need(ID) ON DELETE RESTRICT"
					+ "Result TEXT NOT NULL DEFAULT 'Not completed')";
			createTables7.executeUpdate(create7);
			createTables7.close();
			
			Statement createTables8 = c.createStatement();
			String create8 = "CREATE TABLE fulfill ("
					+ "Option_ID INTEGER REFERENCES option(ID) ON DELETE SET NULL"
					+ "Prosthetic_ID INTEGER REFERENCES prosthetic(ID) ON DELETE CASCADE"
					+ "PRIMARY KEY(Option_ID,Prosthetic_ID))";
			createTables8.executeUpdate(create8);
			createTables8.close();		
			
		} catch (SQLException sqlE) {
			if (sqlE.getMessage().contains("already exist")){
				System.out.println("No need to create the tables; already there");
			}
			else {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}
		}
	}
	
	public CompanyManager getconMan() {
		return conMan; 
	}
	
	public OptionManager getopMan() {
		return opMan;
	}
	
	public NeedManager getneMan() {
		return neMan;
	}
	
	public PatientManager getpatMan() {
		return patMan;
	}
	
	public ProstheticManager getprosMan() {
		return prosMan;
	}
	
	public SurgeonManager getsurgeonMan() {
		return surgeonMan;
	}
	
	public SurgeryManager getsurgeryMan() {
		return surgeryMan;
	}
	
	

}
