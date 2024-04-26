package Prosthetic.db.jdbc;

import java.sql.*;

import Prosthetic.db.interfaces.OptionManager;
import Prosthetic.db.pojos.Option;



public class JDBCOptionManager implements OptionManager {
	private Connection c;
	private ConnectionManager conMan;
	
	public JDBCOptionManager(Connection c, ConnectionManager conMan) {
		super();
		this.c = c;
		this.conMan = conMan;
	}
	
	@Override
	public void addOption(Option o) {
		//Meter en el insert lista de protesis
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
	public Option getOptionByType(String type) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Option getOption(int id) {
		//Poner en el new Option la lista de protesis
		try {
			String sql = "SELECT * FROM option WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			//Option o = new Option (rs.getInt("id"), rs.getString("type"));
			//return o;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;
	}
	
	
}
