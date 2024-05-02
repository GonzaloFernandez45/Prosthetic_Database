package Prosthetic.db.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Prosthetic.db.interfaces.MaterialManager;
import Prosthetic.db.pojos.*;

public class JDBCMaterialManager implements MaterialManager{
	
	private static Connection c;
	private ConnectionManager conMan;
	
	public JDBCMaterialManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public Material getMaterial(int id) {
		try {
			String sql = "SELECT * FROM material WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Material material = new Material(rs.getInt("id"),rs.getString("type"), rs.getString("availability"));
			return material;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void deleteMaterial(int id) {
		try {
			String template = "DELETE FROM material WHERE id LIKE ?";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}

	@Override
	public String checkAvailability(int id) {
		try {
			String sql = "SELECT availability FROM material WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			String availability = rs.getString("availability");
			return availability;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	public void addMaterial(Material m) {
		try {
			String template = "INSERT INTO material (type, availability) VALUES (?, ?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, m.getType());
			pstmt.setString(1, m.getAvailability());
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		
	}
	

}
