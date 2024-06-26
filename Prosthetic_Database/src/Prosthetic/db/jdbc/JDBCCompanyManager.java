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
			String template = "INSERT INTO company (name, location) VALUES (?,?)";
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
			rs.close();
			search.close();
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
		Company company =null;
		while (rs.next()) {
		company = new Company(rs.getString("name"), rs.getString("location"));
		company.setId(id);
		}
		rs.close();
		pstmt.close();
		return company;
		
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}	
		return null;
	}
	
	@Override
	public List<Company> listCompanies() {
		List<Company> companies = new ArrayList<Company>();
		try {
			String sql = "SELECT * FROM company";
			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Company company = new Company(rs.getInt("id"), rs.getString("name"), rs.getString("location"));
				companies.add(company);
				}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return companies;
	}
	
	@Override
	public List<Company> listCompaniesIDandName() {
		List<Company> companies = new ArrayList<Company>();
		try {
			String sql = "SELECT id,name FROM company";
			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Company company = new Company(rs.getInt("id"), rs.getString("name"));
				companies.add(company);
				}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return companies;
	}
	
	
	
	
	

}
