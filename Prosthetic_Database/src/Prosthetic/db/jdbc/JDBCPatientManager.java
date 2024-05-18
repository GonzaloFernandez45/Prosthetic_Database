package Prosthetic.db.jdbc;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.PatientManager;
import Prosthetic.db.pojos.Company;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;


public class JDBCPatientManager implements PatientManager {
	
	private static Connection c;
	private ConnectionManager conMan;
	
	public JDBCPatientManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
	@Override
	public void addPatient(Patient p) {
		
		try {
			String template = "INSERT INTO patient (name, surname, sex, DOB, dni) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, p.getName());
			pstmt.setString(2, p.getSurname());
			pstmt.setString(3, p.getSex());
			pstmt.setDate(4, p.getDob());
			pstmt.setInt(5, p.getDni());
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
			String sql = "SELECT * FROM patient WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Patient p = new Patient (rs.getInt("id"),rs.getString("name"),rs.getString("surname"),rs.getString("sex"),rs.getDate("DOB"),rs.getInt("dni"));
			return p;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public List<Patient> listPatients(){
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM patient";
			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Patient patient = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("sex"), rs.getDate("dob"), rs.getInt("dni"));
				patients.add(patient);
				}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return patients;
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
				Patient p = new Patient(id,PatientName,surname,sex,DOB,dni);
				patients.add(p);
			}
			rs.close();
			search.close();
		}catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return patients;
	}

	@Override
	public void reportDelivery(int Patient_ID, int Prosthetic_ID, String updateReport) {
		try {
			String sql = "UPDATE prosthetic SET report = ? WHERE Patient_ID = ? AND id = ?";
			PreparedStatement prepstmt = c.prepareStatement(sql);
			prepstmt.setString(1, updateReport);
			prepstmt.setInt(2, Patient_ID);
			prepstmt.setInt(3, Prosthetic_ID);
			prepstmt.executeUpdate();
			prepstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	@Override
	public List<Patient> getPatientByIDandName(){
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
			rs.close();
			search.close();
		}catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return patients;
	}
	@Override
	public Patient getPatientNameSurname(int id) {
		try {
			String sql = "SELECT name,surname FROM patient WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Patient p = new Patient (rs.getString("name"),rs.getString("surname"));
			return p;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
		
	}
	
	@Override
	public int getIDofAPatient(String name, String surname, int dni) {
		try {
			String sql = "SELECT id FROM patient WHERE name LIKE ? AND surname LIKE ? AND dni = ? ";
			PreparedStatement prepstmt = c.prepareStatement(sql);
			prepstmt.setString(1, name);
			prepstmt.setString(2, surname);
			prepstmt.setInt(3, dni);
			ResultSet rs = prepstmt.executeQuery();
			rs.next();
			int Patient_ID = rs.getInt("id");
			return Patient_ID;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return -1;
		
	}
	

	
	
}
