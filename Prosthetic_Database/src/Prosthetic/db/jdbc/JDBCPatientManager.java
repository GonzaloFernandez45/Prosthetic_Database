package Prosthetic.db.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.PatientManager;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;


public class JDBCPatientManager implements PatientManager {
	
	private static Connection c;
	private ConnectionManager conMan;
	
	public JDBCPatientManager(Connection c, ConnectionManager conMan) {
		super();
		this.c = c;
		this.conMan = conMan;
	}
	
	public List<Patient> getPatientByIDandName(){
		//TODO metodo que muestre unan lista de los pacientes para saber el id de cada uno
		//Y luego poder llamar a otros metodos como checkneeds pasandole el id del paciente que quieras
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT id,name,surname FROM patient GROUP BY id";
			PreparedStatement search = c.prepareStatement(sql);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String PatientName = rs.getString("name");
				String surname = rs.getString("surname");
				Patient p = new Patient(id,PatientName,surname);
				patients.add(p);
			}
			return patients;
		}catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return patients;
	}
	@Override
	public void addPatient(Patient p) {
		
		try {
			String template = "INSERT INTO patient (name, surname, sex, DOB, dni, report) VALUES (?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getSurname());
			pstmt.setString(3, p.getSex());
			pstmt.setDate(4, p.getDob());
			pstmt.setInt(5, p.getDni());
			pstmt.setString(6, p.getReport());
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}

	}

	@Override
	public Patient getPatientByID(int id) {
		try {
			String sql = "SELECT * FROM patients WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Patient p = new Patient (rs.getInt("name"),rs.getString("name"),rs.getString("surname"),rs.getString("sex"),rs.getDate("DOB"),rs.getInt("dni"),rs.getString("report"));
			return p;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public List<Patient> getPatientByName(String name) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM patient WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + name + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String PatientName = rs.getString("name");
				String surname = rs.getString("surname");
				String sex = rs.getString("sex"); //TODO cambiar a tipo SEX si hacemos un enumerado
				Date DOB = rs.getDate("DOB");
				Integer dni = rs.getInt("dni");
				String report = rs.getString("report");
				Patient p = new Patient(id,PatientName,surname,sex,DOB,dni,report);
				patients.add(p);
			}
			return patients;
		}catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return patients;
	}

	//Meter el paciente directamente para sacar su report o meter el id, sacar el paciente y luego el report
	@Override
	public String reportDelivery(int id) {
		try {
			String sql = "SELECT report FROM patients WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Patient p = new Patient(rs.getInt("name"),rs.getString("name"),rs.getString("surname"),rs.getString("sex"),rs.getDate("DOB"),rs.getInt("dni"),rs.getString("report"));
			String report = p.getReport();
			return report;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}


	
}
