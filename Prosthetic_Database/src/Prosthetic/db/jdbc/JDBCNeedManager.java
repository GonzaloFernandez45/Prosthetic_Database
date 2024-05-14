package Prosthetic.db.jdbc;

import java.sql.*;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.NeedManager;
import Prosthetic.db.pojos.*;

public class JDBCNeedManager implements NeedManager {

	private static Connection c;
	private ConnectionManager conMan;
	
	public JDBCNeedManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}

	@Override
	public Need getNeed(int id) {
		try {
			String sql = "SELECT * FROM need WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Need need = new Need(rs.getInt("id"),rs.getString("type"));
			return need;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
		}


	@Override
	public Need getNeedByType(String type) {
		try {
			String sql = "SELECT * FROM need WHERE name LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setString(1, "%" + type + "%");
			ResultSet rs = search.executeQuery();
			rs.next();
				Integer id = rs.getInt("id");
				String NeedType = rs.getString("type");
				Need need = new Need(id,NeedType);
				return need;
		}catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return null;
	}


	@Override
	public void addNeed(Need need) {
		try {
			String template = "INSERT INTO need (type) VALUES (?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, need.getType());
			pstmt.executeUpdate();
			pstmt.close();
		}catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Need> listNeeds() {
		List<Need> needs = new ArrayList<Need>();
		try {
			String sql = "SELECT * FROM need";
			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Need need = new Need(rs.getInt("id"),rs.getString("type"));
				needs.add(need);
				}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return needs;
	}
	
	@Override
	public List<Need> getNeedByPatient(int Patient_ID) {
		List<Need> needs = new ArrayList<>();
		try {
			String sql = "SELECT Need_ID FROM patient_need WHERE Patient_ID LIKE ?";
			PreparedStatement search = c.prepareStatement(sql);
			search.setInt(1, Patient_ID);
			ResultSet rs = search.executeQuery(); 
			while(rs.next()) {
				Integer id = rs.getInt("Need_ID");
				Need need = getNeed(id);
				needs.add(need);
			}
		}catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return needs;
	}

}
