package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import Prosthetic.db.interfaces.*;
import Prosthetic.db.pojos.*;


public class JDBCSurgeryManager implements SurgeryManager {
	
	private Connection c;
    private ConnectionManager conMan;
	
	
	public JDBCSurgeryManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
	//TODO como hacemos para el surgeon need y patient en todos los metodos

	@Override
	public void addSurgery(Surgery s) {
		try {
		String sql= "INSERT INTO surgery (id,  time, date, room, prosthetic_id, surgeon_id,result )"
				+ "VALUES (?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement prepstm= c.prepareStatement(sql);
		prepstm.setInt(1,s.getId());
		prepstm.setString(2,s.getTime());
		prepstm.setDate(3,s.getDate());
		prepstm.setInt(4,s.getRoom());
		prepstm.setInt(5,s.getProsthetic().getID());
		prepstm.setInt(6,s.getSurgeon().getId());
		prepstm.setString(8,s.getResult());
		prepstm.executeUpdate();
		prepstm.close();
		
		}catch( SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
			
		}

	}

	@Override
	public void deleteSurgery(Surgery s) {
		try {
			String sql= "DELETE FROM surgery WHERE id = ?";
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
	public List<Surgery> searchSurgerybyPatient(int Patient_ID) {
		List<Surgery>surgery= new ArrayList<Surgery>();
		try {
			String sql= "SELECT s.id,s.time,s.date,s.room,s.Prosthetic_ID,s.Surgeon_ID,s.result FROM surgery AS s JOIN prosthetic ON s.Prosthetic_ID = prosthetic.id WHERE prosthetic.Patient_ID = ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setInt(1,Patient_ID);
			ResultSet rs=prepstm.executeQuery();
	
			while (rs.next()) {
				int id =rs.getInt("id");
				String time = rs.getString("time");
				Date date = rs.getDate("date");
				int room =rs.getInt("room");
				ProstheticManager prostheticMan = conMan.getprosMan();
				Prosthetic prosthetic = prostheticMan.getProstheticByID(rs.getInt("Prosthetic_ID"));
				int surgeon_id = rs.getInt("Surgeon_ID");
				SurgeonManager surgeonMan = conMan.getsurgeonMan();
				Surgeon surgeon = surgeonMan.getSurgeon(surgeon_id);
				String result = rs.getString("result");
				String type = rs.getString("type");
				
				Surgery s= new Surgery(id,time,date,room,prosthetic,surgeon,result);
				surgery.add(s);
			}
			rs.close();
			prepstm.close();
			
			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return surgery;
	}

	@Override
	public List<Surgery> searchSurgerybyDate(Date date) {
		List<Surgery>surgeries= new ArrayList<Surgery>();
		try {
			String sql= "SELECT * FROM surgery WHERE date LIKE ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setDate(1,date);
			ResultSet rs=prepstm.executeQuery();
	
			while (rs.next()) {
				int id =rs.getInt("id");
				String time = rs.getString("time");
				Date dateSurgery = rs.getDate("date");
				int room =rs.getInt("room");
				ProstheticManager prostheticMan = conMan.getprosMan();
				Prosthetic prosthetic = prostheticMan.getProstheticByID(rs.getInt("Prosthetic_ID"));
				int surgeon_id = rs.getInt("Surgeon_ID");
				SurgeonManager surgeonMan = conMan.getsurgeonMan();
				Surgeon surgeon = surgeonMan.getSurgeon(surgeon_id);
				String result = rs.getString("result");
				
				Surgery s = new Surgery(id,time,dateSurgery,room,prosthetic,surgeon,result);
				surgeries.add(s);
	
			}
			rs.close();
			prepstm.close();
			

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return surgeries;
	}

	@Override
	public List<Surgery> searchSurgerybySurgeon(Surgeon s) {
		List<Surgery>surgery= new ArrayList<Surgery>();
		try {
			String sql= "SELECT * FROM surgery WHERE surgeon  LIKE ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setString(1,"%"+s+"%");
			ResultSet rs=prepstm.executeQuery();
	
			while (rs.next()) {
				int id =rs.getInt("id");
				String time = rs.getString("time");
				Date date = rs.getDate("date");
				int room =rs.getInt("room");
				ProstheticManager prostheticMan = conMan.getprosMan();
				Prosthetic prosthetic = prostheticMan.getProstheticByID(rs.getInt("Prosthetic_ID"));
				int surgeon_id = rs.getInt("Surgeon_ID");
				SurgeonManager surgeonMan = conMan.getsurgeonMan();
				Surgeon surgeon = surgeonMan.getSurgeon(surgeon_id);
				String result = rs.getString("result");
				
				Surgery sur= new Surgery(id,time,date,room,prosthetic,surgeon,result);
				surgery.add(sur);
				
			}
			rs.close();
			prepstm.close();

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return surgery;
	}

}
