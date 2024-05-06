package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.NeedManager;
import Prosthetic.db.interfaces.PatientManager;
import Prosthetic.db.interfaces.ProstheticManager;
import Prosthetic.db.interfaces.SurgeonManager;
import Prosthetic.db.interfaces.SurgeryManager;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;
import Prosthetic.db.pojos.Prosthetic;
import Prosthetic.db.pojos.Surgeon;
import Prosthetic.db.pojos.Surgery;

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
			String sql= "SELECT * FROM surgery WHERE Patient_ID = ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setInt(1,Patient_ID);
			ResultSet rs=prepstm.executeQuery();
	
			while (rs.next()) {
				int id =rs.getInt("id");
				String time = rs.getString("time");
				Date date = rs.getDate("date");
				int room =rs.getInt("room");
				int patient_id = rs.getInt("Prosthetic_ID");
				ProstheticManager prostheticMan = conMan.getprosMan();
				Prosthetic prosthetic = prostheticMan.getProstheticByID();
				int surgeon_id = rs.getInt("Surgeon_ID");
				SurgeonManager surgeonMan = conMan.getsurgeonMan();
				Surgeon surgeon = surgeonMan.getSurgeon(surgeon_id);
				int need_id = rs.getInt("Need_ID");
				NeedManager needMan = conMan.getneedMan();
				Need need = needMan.getNeed(need_id);
				String result = rs.getString("result");
				String type = rs.getString("type");
				
				Surgery s= new Surgery(id,time,date,room,prosthetic,surgeon,result);
				surgery.add(s);
			}
			rs.close();
			return surgery;
			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Surgery> searchSurgerybyDate(Surgery s) {
		List<Surgery>surgery= new ArrayList<Surgery>();
		try {
			String sql= "SELECT * FROM surgery WHERE date LIKE ?";
			PreparedStatement prepstm= c.prepareStatement(sql);
			prepstm.setDate(1,s.getDate());
			ResultSet rs=prepstm.executeQuery();
	
			while (rs.next()) {
				int id =rs.getInt("id");
				String time = rs.getString("time");
				Date date = rs.getDate("date");
				int room =rs.getInt("room");
				int patient_id = rs.getInt("Patient_ID");
				PatientManager patientMan = conMan.getpatientMan();
				Patient patient = patientMan.getPatientByID(patient_id);
				int surgeon_id = rs.getInt("Surgeon_ID");
				SurgeonManager surgeonMan = conMan.getsurgeonMan();
				Surgeon surgeon = surgeonMan.getSurgeon(surgeon_id);
				int need_id = rs.getInt("Need_ID");
				NeedManager needMan = conMan.getneedMan();
				Need need = needMan.getNeed(need_id);
				String result = rs.getString("result");
				String type = rs.getString("type");
				
				s= new Surgery(id,time,date,room,patient,surgeon,need,result,type);
				surgery.add(s);
	
			}
			rs.close();
			return surgery;

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return null;
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
				int patient_id = rs.getInt("Patient_ID");
				PatientManager patientMan = conMan.getpatientMan();
				Patient patient = patientMan.getPatientByID(patient_id);
				int surgeon_id = rs.getInt("Surgeon_ID");
				SurgeonManager surgeonMan = conMan.getsurgeonMan();
				Surgeon surgeon = surgeonMan.getSurgeon(surgeon_id);
				int need_id = rs.getInt("Need_ID");
				NeedManager needMan = conMan.getneedMan();
				Need need = needMan.getNeed(need_id);
				String result = rs.getString("result");
				String type = rs.getString("type");
				
				Surgery sur= new Surgery(id,time,date,room,patient,surgeon,need,result,type);
				surgery.add(sur);
				
			}
			rs.close();
			return surgery;

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		return null;
	}

}
