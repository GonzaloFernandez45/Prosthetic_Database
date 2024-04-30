package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.ProstheticManager;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;
import Prosthetic.db.pojos.Prosthetic;
import Prosthetic.db.pojos.Surgery;

public class JDBCProstheticManager implements ProstheticManager {

	
	private Connection c;
    private ConnectionManager conMan;
	
	
	public JDBCProstheticManager(Connection c, ConnectionManager conMan) {
		super();
		this.c = c;
		this.conMan = conMan;
	}
	

	
	// TODO como ponemos need options material en todos los metodos
	@Override
	public void addProsthetic(Prosthetic p) {
		try {
		String sql = "INSERT INTO prosthetic (id, size,company,patient,needs,price,material) "
				+"VAUES (?,?,?,?,?,?)";
		PreparedStatement prepstm= c.prepareStatement(sql);
		prepstm.setInt(1,p.getID());
		prepstm.setString(2, p.getSize());
		prepstm.setInt(3, p.getCompany().getId());
		prepstm.setInt(4, p.getPatient().getId());
		prepstm.setInt(5, p.getNeed().getId());
		prepstm.setInt(6, p.getPrice());
		prepstm.executeUpdate();
		prepstm.close();
		
		}catch(SQLException sqlE) {
			System.out.println ("Error in the database");
			sqlE.printStackTrace();
			
		}

	}

	@Override
	public void deleteProsthetic(Prosthetic p) {
		try {
		String sql= "DELETE FROM prosthetic WHERE id = ?";
		PreparedStatement prepstm= c.prepareStatement(sql);
		prepstm.setInt(1, p.getID());
		prepstm.executeUpdate();
		prepstm.close();
		}catch(SQLException sqlE) {
			System.out.println("Error in the database");
			sqlE.printStackTrace();
		}
		

	}

	@Override
	public List<Prosthetic> getProstheticbyNeed(Need n) {
		List<Prosthetic>prosthetics= new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT * FROM patient WHERE need LIKE ?";
			PreparedStatement prepstm = c.prepareStatement(sql);
			prepstm.setString(1, "%" + n + "%");
			ResultSet rs = prepstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				Integer price = rs.getInt("price");
				String size = rs.getString("size");
				
				
				
				
			}
			
		}catch (SQLException sqlE) {
			System.out.println("Error in the database");
			sqlE.printStackTrace();
		}
		return null;
	}
		

	@Override
	public List<Prosthetic> getProstheticbyPatient(Patient pa) {
		List<Prosthetic>prosthetics= new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT * FROM patient WHERE patient LIKE ?";
			PreparedStatement prepstm = c.prepareStatement(sql);
			prepstm.setString(1, "%" + pa + "%");
			ResultSet rs = prepstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				Integer price = rs.getInt("price");
				String size = rs.getString("size");
				
				
			}
			rs.close();

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		
		
		
		return null;
	}

}

