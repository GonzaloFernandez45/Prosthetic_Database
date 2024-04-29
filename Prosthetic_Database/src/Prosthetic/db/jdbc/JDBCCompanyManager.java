package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.CompanyManager;
import Prosthetic.db.pojos.*;

public class JDBCCompanyManager implements CompanyManager {
	
	private static Connection c;
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
			//TODO should we put here also the list of prosthetics??
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
		// TODO create prosthetic is already a table created, how should we implement this method?
		
	}
	@Override
	public void deleteCompany(Company com) {
		//TODO cambiar metodo para pasarle el id no un objeto company
		try {
			String template = "DELETE FROM company WHERE name LIKE ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, com.getName());
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public List<Patient> checkDemandByNeed(String need) {
		List<Patient> patients = new ArrayList<Patient>();
		try {
			String sql = "SELECT * FROM patient WHERE need LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + need + "%");
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				String sex = rs.getString("sex"); //TODO cambiar a tipo SEX si hacemos un enumerado
				String DOB = rs.getString("DOB");
				String dni = rs.getString("dni");
				String report = rs.getString("report");
				String condition = rs.getString("condition");
				
				// TODO PROBLEMA, EN LOS CONSTRUCTORES TENEMOS LISTAS PERO EN LAS TABLAS NO HAY ESOS ATRIBUTOS, SE QUITAN O NO??
				//Patient patient = new Patient(id,name,surname,sex,DOB, condition, dni,report);
			}
			
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Need> checkNeeds(Patient p) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
