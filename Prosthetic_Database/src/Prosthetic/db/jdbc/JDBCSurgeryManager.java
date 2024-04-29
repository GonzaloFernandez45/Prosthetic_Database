package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.SurgeryManager;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;
import Prosthetic.db.pojos.Surgeon;
import Prosthetic.db.pojos.Surgery;

public class JDBCSurgeryManager implements SurgeryManager {
	
	private Connection c;
    private ConnectionManager conMan;
	
	
	public JDBCSurgeryManager(Connection c, ConnectionManager conMan) {
		super();
		this.c = c;
		this.conMan = conMan;
	}
	
	//TODO como hacemos para el surgeon need y patient en todos los metodos

	@Override
	public void addSurgery(Surgery s) {
		try {
		String sql= "INSERT INTO surgeries (type,  date,  time,  result,  room,  id,  surgeon,need, patient)"
				+ "VALUES (?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement prepstm= c.prepareStatement(sql);
		prepstm.setString(1,s.getType());
		prepstm.setString(2,s.getDate());
		prepstm.setString(3,s.getTime());
		prepstm.setString(4,s.getResult());
		prepstm.setInt(5,s.getRoom());
		prepstm.setInt(6,s.getId());
		prepstm.setString(7,s.getSurgeon().getName());
		prepstm.setString(8,s.getNeed().getType());
		prepstm.setString(9,s.getPatient().getName());
		//TODO como hacemos para  patient surgeon and need, hemos puesto date y time como string no como clase date 
		
		
		}catch( SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
			
		}
		
		

	}

	@Override
	public void deleteSurgery(Surgery s) {
		try {
			String sql= "DELETE FROM surgeries WHERE id = ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setInt(1,s.getId());
			prepstm.executeUpdate();
			prepstm.close();
			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}

	}

	@Override
	public List<Surgery> searchSurgerybyPatient(Patient p) {
		List<Surgery>surgeries= new ArrayList<Surgery>();
		try {
			String sql= "SELECT * FROM surgeries WHERE patient LIKE ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setString(1,"%"+p+"%");
			
			ResultSet rs=prepstm.executeQuery();
	
			while (rs.next()) {
				String type = rs.getString("type");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String result = rs.getString("result");
				int room =rs.getInt("room");
				int id =rs.getInt("id");
				String surgeon_name = rs.getString("name of surgeon");
				String need = rs.getString("type of need");
				String patient_name = rs.getString("name of patient");
				
				
				
			
				
			}
			rs.close();

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Surgery> searchSurgerybyDate(Surgery s) {
		List<Surgery>surgeries= new ArrayList<Surgery>();
		try {
			String sql= "SELECT * FROM surgeries WHERE date LIKE ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setString(1,s.getDate());
			ResultSet rs=prepstm.executeQuery();
	
			while (rs.next()) {
				String type = rs.getString("type");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String result = rs.getString("result");
				int room =rs.getInt("room");
				int id =rs.getInt("id");
				String surgeon_name = rs.getString("name of surgeon");
				String need = rs.getString("type of need");
				String patient_name = rs.getString("name of patient");
				
				
				
			
				
			}
			rs.close();

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Surgery> searchSurgerybySurgeon(Surgeon s) {
		List<Surgery>surgeries= new ArrayList<Surgery>();
		try {
			String sql= "SELECT * FROM surgeries WHERE surgeon  LIKE ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setString(1,"%"+s+"%");
			ResultSet rs=prepstm.executeQuery();
	
			while (rs.next()) {
				String type = rs.getString("type");
				String date = rs.getString("date");
				String time = rs.getString("time");
				String result = rs.getString("result");
				int room =rs.getInt("room");
				int id =rs.getInt("id");
				String surgeon_name = rs.getString("name of surgeon");
				String need = rs.getString("type of need");
				String patient_name = rs.getString("name of patient");
				
			
				
			
				
			}
			rs.close();

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return null;
	}

}
