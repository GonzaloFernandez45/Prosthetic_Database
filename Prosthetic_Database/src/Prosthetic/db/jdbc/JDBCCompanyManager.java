package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.List;

import Prosthetic.db.interfaces.CompanyManager;
import Prosthetic.db.pojos.*;

public class JDBCCompanyManager implements CompanyManager {
	
	private Connection c;
	private ConnectionManager conMan;
	
	
	public JDBCCompanyManager(Connection c, ConnectionManager conMan) {
		super();
		this.c = c;
		this.conMan = conMan;
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
	public void createProsthetic(Prosthetic p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deleteCompany(Company c) {
		// TODO Auto-generated method stub
		
	}
	
	private static void printCompanies() throws SQLException{
		//TODO Create this method to implement it in deleteCompany
	}
	
	@Override
	public List<Patient> checkDemand() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Need> checkNeeds(Patient p) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
