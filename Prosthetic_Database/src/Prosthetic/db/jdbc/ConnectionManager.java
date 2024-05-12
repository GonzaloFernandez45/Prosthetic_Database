package Prosthetic.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private MaterialManager materialMan;
	
	public Connection getConnection() {
		return c;
	}
	public ConnectionManager() {
		this.connect();
		this.conMan = new JDBCCompanyManager(this);
		this.opMan = new JDBCOptionManager(this);
		this.neMan = new JDBCNeedManager(this);
		this.patMan = new JDBCPatientManager(this);
		this.prosMan = new JDBCProstheticManager(this);
		this.surgeonMan = new JDBCSurgeonManager(this);
		this.surgeryMan = new JDBCSurgeryManager(this);
		this.materialMan = new JDBCMaterialManager(this);
		this.createTables();
		this.insertNeeds();
		this.insertOption();
		this.insertMaterial();
		
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
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "location TEXT NOT NULL)";
			createTables1.executeUpdate(create1);
			createTables1.close();
			
			Statement createTables2 = c.createStatement();
			String create2 = "CREATE TABLE need ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "type TEXT NOT NULL)";
			createTables2.executeUpdate(create2);
			createTables2.close();
			
			Statement createTables3 = c.createStatement();
			String create3 = "CREATE TABLE option ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "type TEXT NOT NULL)";
			createTables3.executeUpdate(create3);
			createTables3.close();
			
			Statement createTables4 = c.createStatement();
			String create4 = "CREATE TABLE patient ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL,"
					+ "sex SEX,"
					+ "DOB DATE NOT NULL,"
					+ "dni INTEGER NOT NULL,"
					+ "report TEXT NOT NULL DEFAULT 'NO')";
			createTables4.executeUpdate(create4);
			createTables4.close();
			
			Statement createTables5 = c.createStatement();
			String create5 = "CREATE TABLE prosthetic ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "size TEXT NOT NULL,"
					+ "Company_ID INTEGER REFERENCES company(ID) ON DELETE SET NULL,"
					+ "Patient_ID INTEGER REFERENCES patient(ID) ON DELETE RESTRICT,"
					+ "Need_ID INTEGER REFERENCES need(ID) ON DELETE RESTRICT,"
					+ "price INTEGER NOT NULL,"
					+ "Material_ID INTEGER REFERENCES material(ID))";
			createTables5.executeUpdate(create5);
			createTables5.close();
			
			
			Statement createTables6 = c.createStatement();
			String create6 = "CREATE TABLE surgeon ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "name TEXT NOT NULL,"
					+ "surname TEXT NOT NULL,"
					+ "salary INTEGER,"
					+ "hiredate DATE NOT NULL,"
					+ "specialization TEXT)";
			createTables6.executeUpdate(create6);
			createTables6.close();
			
			Statement createTables7 = c.createStatement();
			String create7 = "CREATE TABLE surgery ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "time TEXT NOT NULL,"
					+ "date DATE NOT NULL,"
					+ "room INTEGER NOT NULL,"
					+ "Surgeon_ID INTEGER REFERENCES surgeon(ID) ON DELETE SET NULL,"
					+ "Prosthetic_ID INTEGER REFERENCES prosthetic(ID) ON DELETE SET NULL,"
					+ "result TEXT NOT NULL DEFAULT 'Not completed')";
					
			createTables7.executeUpdate(create7);
			createTables7.close();
			
			Statement createTables8 = c.createStatement();
			String create8 = "CREATE TABLE fulfill ("
					+ "Option_ID INTEGER REFERENCES option(ID) ON DELETE SET NULL,"
					+ "Prosthetic_ID INTEGER REFERENCES prosthetic(ID) ON DELETE CASCADE,"
					+ "PRIMARY KEY(Option_ID,Prosthetic_ID))";
			createTables8.executeUpdate(create8);
			createTables8.close();	
			
			Statement createTables9 = c.createStatement();
			String create9 = "CREATE TABLE material ("
					+ "ID INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "Type STRING NOT NULL,"
					+ "Availability STRING NOT NULL)";
			createTables9.executeUpdate(create9);
			createTables9.close();
			
			Statement createTables10 = c.createStatement();
			String create10 = "CREATE TABLE patient_need ("
					+ "Patient_ID INTEGER REFERENCES patient(ID),"
					+ "Need_ID INTEGER REFERENCES need(ID),"
					+ "PRIMARY KEY (patient_ID, need_ID))";
			createTables10.executeUpdate(create10);
			createTables10.close();
			
			
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
	
	private void insertNeeds() {
		try {
			Statement insertNeed1 = c.createStatement();
			String need1 = "INSERT INTO need VALUES (1, 'Bellow Elbow')";
			insertNeed1.executeUpdate(need1);
			insertNeed1.close();
			
			Statement insertNeed2 = c.createStatement();
			String need2 = "INSERT INTO need VALUES (2, 'Bellow Wrist')";
			insertNeed2.executeUpdate(need2);
			insertNeed2.close();
			
			Statement insertNeed3 = c.createStatement();
			String need3 = "INSERT INTO need VALUES (3, 'Bellow Shoulder')";
			insertNeed3.executeUpdate(need3);
			insertNeed3.close();
			
			Statement insertNeed4 = c.createStatement();
			String need4 = "INSERT INTO need VALUES (4, 'Bellow Ankle')";
			insertNeed4.executeUpdate(need4);
			insertNeed4.close();
			
			Statement insertNeed5 = c.createStatement();
			String need5 = "INSERT INTO need VALUES (5, 'Bellow Knee')";
			insertNeed5.executeUpdate(need5);
			insertNeed5.close();
			
			Statement insertNeed6 = c.createStatement();
			String need6 = "INSERT INTO need VALUES (6, 'Bellow Pelvis')";
			insertNeed6.executeUpdate(need6);
			insertNeed6.close();
			
		}catch(SQLException sqlE) {
			if (sqlE.getMessage().contains("UNIQUE constraint failed")){
				System.out.println("No need to insert the needs; already there");
			}
			else {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}
		}
	}
	
	private void insertOption() {
		try {
			Statement insertOption1 = c.createStatement();
			String option1 = "INSERT INTO option VALUES (1, 'sport')";
			insertOption1.executeUpdate(option1);
			insertOption1.close();
			
			Statement insertOption2 = c.createStatement();
			String option2 = "INSERT INTO option VALUES (2, 'realistic')";
			insertOption2.executeUpdate(option2);
			insertOption2.close();
			
			Statement insertOption3 = c.createStatement();
			String option3 = "INSERT INTO option VALUES (3, 'carbon fiber')";
			insertOption3.executeUpdate(option3);
			insertOption3.close();
			
			Statement insertOption4 = c.createStatement();
			String option4 = "INSERT INTO option VALUES (4, 'blue')";
			insertOption4.executeUpdate(option4);
			insertOption4.close();
			
			Statement insertOption5 = c.createStatement();
			String option5 = "INSERT INTO option VALUES (5, 'camouflage')";
			insertOption5.executeUpdate(option5);
			insertOption5.close();
			
		}catch(SQLException sqlE) {
			if (sqlE.getMessage().contains("UNIQUE constraint failed")){
				System.out.println("No need to insert the options; already there");
			}
			else {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}
		}
	}
	
	private void insertMaterial() {
		try {
			Statement insertMaterial1 = c.createStatement();
			String material1 = "INSERT INTO material VALUES (1, 'Iron','YES')";
			insertMaterial1.executeUpdate(material1);
			insertMaterial1.close();
			
			Statement insertMaterial2 = c.createStatement();
			String material2 = "INSERT INTO material VALUES (2, 'Carbon Fiber','YES')";
			insertMaterial2.executeUpdate(material2);
			insertMaterial2.close();
			
			Statement insertMaterial3 = c.createStatement();
			String material3 = "INSERT INTO material VALUES (3, 'Aluminium','YES')";
			insertMaterial3.executeUpdate(material3);
			insertMaterial3.close();
		}catch(SQLException sqlE) {
			if (sqlE.getMessage().contains("UNIQUE constraint failed")){
				System.out.println("No need to insert the materials; already there");
			}
			else {
				System.out.println("Error in query");
				sqlE.printStackTrace();
			}
		}
	}
	
	public int getPKofLastInsertedRow() {
		try {
			String query = "SELECT last_insert_rowid() AS lastId";
			PreparedStatement p;
			p = c.prepareStatement(query);
			ResultSet rs = p.executeQuery();
			Integer lastId = rs.getInt("lastId");
			return lastId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public CompanyManager getcomMan() {
		return conMan; 
	}
	
	public OptionManager getoptionMan() {
		return opMan;
	}
	
	public NeedManager getneedMan() {
		return neMan;
	}
	
	public PatientManager getpatientMan() {
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
	public MaterialManager getmaterialMan() {
		return materialMan;
	}
	
	

}
