package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.CompanyManager;
import Prosthetic.db.pojos.*;

public class JDBCCompanyManager implements CompanyManager {
	
	private static Connection c;
	private ConnectionManager conMan;
	
	
	public JDBCCompanyManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
	
	
	@Override
	public void addCompany(Company com) {
		try {
			String template = "INSERT INTO company (name, location) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, com.getName());
			pstmt.setString(2, com.getLocation());
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void deleteCompany(String com_name) {
		try {
			String template = "DELETE FROM company WHERE name LIKE ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, com_name);
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public List<Patient> checkDemandByNeed(int need_id) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT p.id,p.name,p.surname,p.sex,p.DOB,p.dni,p.report FROM patient AS p JOIN patient_need AS pn ON p.id = pn.patient_id WHERE need = ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1,need_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String sex = rs.getString("sex"); //TODO cambiar a tipo SEX si hacemos un enumerado
				Date DOB = rs.getDate("DOB");
				Integer dni = rs.getInt("dni");
				String report = rs.getString("report");
				Patient p = new Patient(id,name,surname,sex,DOB,dni,report);
				patients.add(p);			
				}
			}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return patients;
	}
	@Override
	public List<Need> checkNeeds(int patient_id) {
		List<Need> needs = new ArrayList<Need>();
		try {
			String sql = "SELECT n.type,ns.patient_id FROM needs As n JOIN patient_needs AS ns ON n.id=ns.need_id GROUP BY ns.patient_id ";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1,patient_id);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String type = rs.getString("type");
				Need n = new Need(id,type);
				needs.add(n);
			}
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return needs;
	}
	@Override
	public Company getCompany(int id) {
		try {
		String sql = "SELECT * FROM company WHERE id = ? ";
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
	public Company listCompanies() {
		try {
			String sql = "SELECT * FROM company";
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Company company = new Company(rs.getString("name"), rs.getString("location"));
			return company;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}


	

}
