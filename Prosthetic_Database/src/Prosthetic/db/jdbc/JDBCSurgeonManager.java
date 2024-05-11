package Prosthetic.db.jdbc;

import java.io.IOException;
import java.security.AlgorithmParametersSpi;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.NeedManager;
import Prosthetic.db.interfaces.PatientManager;
import Prosthetic.db.interfaces.SurgeonManager;
import Prosthetic.db.pojos.Company;
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
		String sql= "INSERT INTO surgeon(name,surname,salary,specialization,hiredate)"
				+ "Values (?,?,?,?,?)";
		PreparedStatement pstmt= c.prepareStatement(sql);
		pstmt.setString(1, s.getName());
		pstmt.setString(2, s.getSurname());
		pstmt.setInt(3, s.getSalary());
		pstmt.setString(4, s.getSpecialization());
		pstmt.setDate(5, s.getHiredate());
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
	public List<Surgeon> getSurgeonByIDandName(){
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		try {
			String sql = "SELECT id,name,surname FROM surgeon GROUP BY id";
			PreparedStatement search = c.prepareStatement(sql);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String PatientName = rs.getString("name");
				String surname = rs.getString("surname");
				Surgeon s = new Surgeon(id,PatientName,surname);
				surgeons.add(s);
			}
			rs.close();
			search.close();
		}catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return surgeons;
	}

	@Override
	public Surgeon getSurgeonbySurgery(int surgery_id) {
		try {
		String sql = "SELECT s.id,s.name,s.surname,s.salary,s.hiredate,s.specialization "
				+ "FROM surgeon AS s JOIN surgery  ON s.id = surgery.Surgeon_ID WHERE surgery_ID = ?";
		Statement st;
		st = c.createStatement();
		ResultSet rs = st.executeQuery(sql);
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
		rs.next();
		Surgeon s = new Surgeon (rs.getInt("id"), rs.getInt("Salary"), rs.getString("name"), rs.getString("surname"),rs.getString("Specialization"), rs.getDate("Hiredate"));
		return s;
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
	public List<Surgeon> listSurgeons(){
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		try {
			String sql = "SELECT * FROM surgeon";
			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Surgeon s = new Surgeon (rs.getInt("id"), rs.getInt("Salary"), rs.getString("name"), rs.getString("surname"),rs.getString("Specialization"), rs.getDate("Hiredate"));
				surgeons.add(s);
				}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return surgeons;
	}
	
	public List<Surgeon> listSurgeonIDandName() {
		List<Surgeon> surgeons = new ArrayList<Surgeon>();
		try {
			String sql = "SELECT id,name,surname FROM surgeon";
			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Surgeon s = new Surgeon (rs.getInt("id"), rs.getString("name"), rs.getString("surname"));
				surgeons.add(s);
				}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return surgeons;
	}
	

}
