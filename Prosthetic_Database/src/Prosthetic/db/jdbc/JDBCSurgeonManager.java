package Prosthetic.db.jdbc;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.NeedManager;
import Prosthetic.db.interfaces.PatientManager;
import Prosthetic.db.interfaces.SurgeonManager;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;
import Prosthetic.db.pojos.Surgeon;
import Prosthetic.db.pojos.Surgery;


public class JDBCSurgeonManager implements SurgeonManager {
	
	private Connection c;
	private ConnectionManager conMan;
	
	public JDBCSurgeonManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
	@Override
	public void addSurgeon(Surgeon s) {
		try {
		String sql= "INSERT INTO surgeon(name,surname,salary,hiredate,specialization)"
				+ "Values (?,?,?,?,?,?)";
		PreparedStatement pstmt= c.prepareStatement(sql);
		pstmt.setString(1, s.getName());
		pstmt.setString(2, s.getSurname());
		pstmt.setInt(3, s.getSalary());
		pstmt.setDate(4, s.getHiredate());
		pstmt.setString(5, s.getSpecialization());
		pstmt.executeUpdate();
		pstmt.close();
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
	}	
	
	@Override
	public List<Surgeon>SearchSurgeonByName(String name, String surname) {
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		try {
			String sql = "SELECT * FROM surgeon WHERE name LIKE ? AND surname LIKE ?";
			PreparedStatement pstmt= c.prepareStatement(sql);
			pstmt.setString(1, "%" + name+ "%");
			pstmt.setString(2, "%" + surname+ "%");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				Surgeon s = new Surgeon (rs.getInt("id"), rs.getInt("Salary"), rs.getString("name"), rs.getString("surname"),rs.getString("Specialization"), rs.getDate("Hiredate"));
				surgeons.add(s);
			}
			rs.close();
			pstmt.close();
	
	}catch (SQLException e) {
		System.out.println("Error in the database");
		e.printStackTrace();
	}
	return surgeons;
}

	@Override
	public Surgeon getSurgeonbySurgery(int surgery_id) {
		try {
		String sql = "SELECT s.id,s.name,s.surname,s.salary,s.hiredate,s.specialization "
				+ "FROM surgeon AS s JOIN surgery  ON s.id = surgery.Surgeon_ID WHERE surgery_ID = ?";
		PreparedStatement pstmt= c.prepareStatement(sql);
		ResultSet rs = pstmt.executeQuery(sql);
		rs.next();
		Surgeon s = new Surgeon (rs.getInt("id"), rs.getInt("Salary"), rs.getString("name"), rs.getString("surname"),rs.getString("Specialization"), rs.getDate("Hiredate"));
		return s;
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
		return null;
		}

	//TODO Revisar
	@Override
	public Surgeon getSurgeon(int id) {
		try {
		String sql = "SELECT * FROM surgeon WHERE id LIKE ? ";
		PreparedStatement pstmt= c.prepareStatement(sql);
		pstmt.setInt(1,id);
		ResultSet rs = pstmt.executeQuery();
			
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public void deleteSurgeon(int surgeon_id) {
		try {
			String template = "DELETE FROM company WHERE name LIKE ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, surgeon_id);
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	

	@Override
	public String resultSurgery(int surgery_id) {
		try {
			String sql = "SELECT report FROM surgery WHERE id = " + surgery_id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			String Newresult = rs.getString("result");
			return Newresult;
			
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public Surgeon listSurgeons(){
		try {
			String sql = "SELECT * FROM surgeon";
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Surgeon s = new Surgeon (rs.getInt("id"), rs.getInt("Salary"), rs.getString("name"), rs.getString("surname"),rs.getString("Specialization"), rs.getDate("Hiredate"));
			return s;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	

}
