package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.*;
import Prosthetic.db.pojos.*;


public class JDBCSurgeryManager implements SurgeryManager {
	
	private Connection c;
    private ConnectionManager conMan;
	
	
	public JDBCSurgeryManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	


	@Override
	public void addSurgery(Surgery s) {
		try {
		String sql= "INSERT INTO surgery (time, date, room, Surgeon_ID,Prosthetic_ID,result )"
				+ "VALUES (?,?,?,?,?,?)" ;
		PreparedStatement prepstm= c.prepareStatement(sql);
		prepstm.setString(1,s.getTime());
		prepstm.setDate(2,s.getDate());
		prepstm.setInt(3,s.getRoom());
		prepstm.setInt(4,s.getSurgeon().getId());
		prepstm.setInt(5,s.getProsthetic().getID());
		prepstm.setString(6,s.getResult());
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
			String sql= "SELECT s.id,s.time,s.date,s.room,s.Surgeon_ID,s.Prosthetic_ID,s.result FROM surgery AS s JOIN prosthetic ON s.Prosthetic_ID = prosthetic.id WHERE prosthetic.Patient_ID = ?";
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
				
				Surgery s= new Surgery(id,time,date,room,surgeon,prosthetic,result);
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
				
				Surgery s = new Surgery(id,time,dateSurgery,room,surgeon,prosthetic,result);
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
			String sql= "SELECT * FROM surgery WHERE Surgeon_ID =?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setInt(1,s.getId());
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
				
				Surgery sur= new Surgery(id,time,date,room,surgeon,prosthetic,result);
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
	
	@Override
	public Surgery getSurgeryByProsthetic(int Prosthetic_ID) {
		try {
			String sql = "SELECT * FROM surgery WHERE Prosthetic_ID ="+Prosthetic_ID;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			int idSurgery =rs.getInt("id");
			String time = rs.getString("time");
			Date date = rs.getDate("date");
			int room =rs.getInt("room");
			ProstheticManager prostheticMan = conMan.getprosMan();
			Prosthetic prosthetic = prostheticMan.getProstheticByID(rs.getInt("Prosthetic_ID"));
			int surgeon_id = rs.getInt("Surgeon_ID");
			SurgeonManager surgeonMan = conMan.getsurgeonMan();
			Surgeon surgeon = surgeonMan.getSurgeon(surgeon_id);
			String result = rs.getString("result");
			Surgery surgery = new Surgery(idSurgery,time,date,room,surgeon,prosthetic,result);
			return surgery;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public List<Surgery> listSurgeries(){
		List<Surgery> surgeries = new ArrayList<Surgery>();
		try {
			String sql = "SELECT * FROM surgery";
			PreparedStatement pstmt = c.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer idSurgery = rs.getInt("id");
				String time = rs.getString("time");
				Date date =rs.getDate("date");
				Integer room = rs.getInt("room");
				SurgeonManager surgeonMan = conMan.getsurgeonMan();
				Surgeon surgeon = surgeonMan.getSurgeon(rs.getInt("Surgeon_ID"));
				ProstheticManager prosMan = conMan.getprosMan();
				Prosthetic pros = prosMan.getProstheticByID(rs.getInt("Prosthetic_ID"));
				String result = rs.getString("result");

				Surgery surgery= new Surgery(idSurgery,time,date,room,surgeon,pros,result);
				surgeries.add(surgery);
				}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return surgeries;
	}
	
	@Override
	public List<Surgery> listSurgeriesOfAPatient(int Patient_ID){
		List<Surgery> surgeries = new ArrayList<Surgery>();
		try {
			String sql = "SELECT s.id,time,date,room,Surgeon_ID,Prosthetic_ID,result FROM surgery AS s JOIN prosthetic ON prosthetic.id = s.Prosthetic_ID WHERE Patient_ID = ?";
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1,Patient_ID);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Integer idSurgery = rs.getInt("id");
				String time = rs.getString("time");
				Date date =rs.getDate("date");
				Integer room = rs.getInt("room");
				SurgeonManager surgeonMan = conMan.getsurgeonMan();
				Surgeon surgeon = surgeonMan.getSurgeon(rs.getInt("Surgeon_ID"));
				ProstheticManager prosMan = conMan.getprosMan();
				Prosthetic pros = prosMan.getProstheticByID(rs.getInt("Prosthetic_ID"));
				String result = rs.getString("result");

				Surgery surgery= new Surgery(idSurgery,time,date,room,surgeon,pros,result);
				surgeries.add(surgery);
				}
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return surgeries;
	}


	

}
