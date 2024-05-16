package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.OptionManager;
import Prosthetic.db.pojos.*;




public class JDBCOptionManager implements OptionManager {
	
	private Connection c;
	private ConnectionManager conMan;
	
	public JDBCOptionManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
	@Override
	public void addOption(Option o) {
		try {
			String template = "INSERT INTO option (type) VALUES (?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setString(1, o.getType());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}



	@Override
	public Option getOption(int id) {
		try {
			String sql = "SELECT * FROM option WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Option o = new Option (rs.getInt("id"), rs.getString("type"));
			return o;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Option> listOptions() {
			List<Option> options = new ArrayList<Option>();
			try {
				String sql = "SELECT * FROM option";
				PreparedStatement pstmt = c.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) {
					Option option = new Option(rs.getInt("id"),rs.getString("type"));
					options.add(option);
					}
				rs.close();
				pstmt.close();
			} catch (SQLException e) {
				System.out.println("Error in the database");
				e.printStackTrace();
			}
			return options;
		}
	
	@Override
	public void insertFulfill(Option o, Prosthetic p) {
		try {
			String template = "INSERT INTO fulfill (Option_ID,Prosthetic_ID) VALUES (?,?)";
			PreparedStatement pstmt;
			pstmt = c.prepareStatement(template);
			pstmt.setInt(1, o.getId());
			pstmt.setInt(2, p.getID());			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
	}
	
	@Override
	public List<Option> listOptionsOfProsthetic(int Prosthetic_ID){
		List<Option> options = new ArrayList<Option>();
		try {
			String sql = "SELECT Option_ID FROM prosthetic JOIN fulfill ON prosthetic.id = fulfill.Prosthetic_ID WHERE Prosthetic_ID = ?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, Prosthetic_ID);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				OptionManager optMan = conMan.getoptionMan();
				Option option = optMan.getOption(rs.getInt("Option_ID"));
				options.add(option);
				}
			rs.close();
			pstmt.close();
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return options;
	}
	
	@Override
	public Option getOptionByType(String type) {
		try {
			String sql = "SELECT * FROM option WHERE type = " + type;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Option o = new Option (rs.getInt("id"), rs.getString("type"));
			return o;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
