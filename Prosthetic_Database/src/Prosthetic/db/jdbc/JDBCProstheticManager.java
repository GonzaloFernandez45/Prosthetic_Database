package Prosthetic.db.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Prosthetic.db.interfaces.CompanyManager;
import Prosthetic.db.interfaces.MaterialManager;
import Prosthetic.db.interfaces.NeedManager;
import Prosthetic.db.interfaces.PatientManager;
import Prosthetic.db.interfaces.ProstheticManager;
import Prosthetic.db.pojos.Company;
import Prosthetic.db.pojos.Material;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;
import Prosthetic.db.pojos.Prosthetic;
import Prosthetic.db.pojos.Surgery;

public class JDBCProstheticManager implements ProstheticManager {

	
	private Connection c;
    private ConnectionManager conMan;
	
	
	public JDBCProstheticManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	

    @Override
	public List<Prosthetic> getProstheticByIDandPriceandSize(){
	
		List<Prosthetic> prosthetics = new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT id,size,price FROM prosthetic GROUP BY id";
			PreparedStatement search = c.prepareStatement(sql);
			ResultSet rs = search.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String size = rs.getString("size");
				Integer price = rs.getInt("price");
				Prosthetic p = new Prosthetic(id,size,price);
				prosthetics.add(p);
			}
			rs.close();
			search.close();
		}catch (SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
		return prosthetics;
	}
	@Override
	public void addProsthetic(Prosthetic p) {
		try {
		String sql = "INSERT INTO prosthetic (id, size,company,patient,need,price,material) "
				+"VAUES (?,?,?,?,?,?)";
		PreparedStatement prepstm= c.prepareStatement(sql);
		prepstm.setInt(1,p.getID());
		prepstm.setString(2, p.getSize());
		prepstm.setInt(3, p.getCompany().getId());
		prepstm.setInt(4, p.getPatient().getId());
		prepstm.setInt(5, p.getNeed().getId());
		prepstm.setInt(6, p.getMaterial().getId());
		prepstm.setInt(7, p.getPrice());
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
		List<Prosthetic>prosthetic= new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT * FROM prosthetic WHERE Need_ID = ?";
			PreparedStatement prepstm = c.prepareStatement(sql);
			prepstm.setString(1, "%" + n + "%");
			ResultSet rs = prepstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				Integer price = rs.getInt("price");
				String size = rs.getString("size");
				int company_id = rs.getInt("Company_ID");
				CompanyManager companyMan=conMan.getcomMan();
				Company company= companyMan.getCompany(company_id);
				int patient_id=rs.getInt("Patient_ID");
				PatientManager patientMan = conMan.getpatientMan();
				Patient patient = patientMan.getPatientByID(patient_id);
				int need_id = rs.getInt("Need_ID");
				NeedManager needMan = conMan.getneedMan();
				Need need = needMan.getNeed(need_id);
				int material_id=rs.getInt("Material_ID");
				MaterialManager materialsMan= conMan.getmaterialMan();
				Material material=materialsMan.getMaterial(material_id);
				Prosthetic p = new Prosthetic(id,size,company,patient,need,price,material);
				prosthetic.add(p);
				
			}
			rs.close();
			prepstm.close();
			
		}catch (SQLException sqlE) {
			System.out.println("Error in the database");
			sqlE.printStackTrace();
		}
		return prosthetic;
	}
		

	@Override
	public List<Prosthetic> getProstheticbyPatient(Patient pa) {
		List<Prosthetic>prosthetic= new ArrayList<Prosthetic>();
		try {
			String sql = "SELECT * FROM prosthetic WHERE Patient_ID = ?";
			PreparedStatement prepstm = c.prepareStatement(sql);
			prepstm.setString(1, "%" + pa + "%");
			ResultSet rs = prepstm.executeQuery();
			while(rs.next()) {
				Integer id = rs.getInt("id");
				String size = rs.getString("size");
				int company_id = rs.getInt("Company_ID");
				CompanyManager companyMan=conMan.getcomMan();
				Company company= companyMan.getCompany(company_id);
				int patient_id=rs.getInt("Patient_ID");
				PatientManager patientMan = conMan.getpatientMan();
				Patient patient = patientMan.getPatientByID(patient_id);
				int need_id = rs.getInt("Need_ID");
				NeedManager needMan = conMan.getneedMan();
				Need need = needMan.getNeed(need_id);
				int price= rs.getInt("price");
				int material_id=rs.getInt("Material_ID");
				MaterialManager materialsMan= conMan.getmaterialMan();
				Material material=materialsMan.getMaterial(material_id);
				
				Prosthetic p = new Prosthetic(id,size,company,patient,need,price,material);
				prosthetic.add(p);
			}
			rs.close();
			prepstm.close();
			

			
		}catch(SQLException sqlE) {
			System.out.println("Error in the data base");
			sqlE.printStackTrace();
		}
		
		
		
		return prosthetic;
	}


	@Override
	public Prosthetic getProstheticByID(int id) {
		try {
			String sql = "SELECT * FROM prosthetic WHERE id = " + id;
			Statement st;
			st = c.createStatement();
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			Integer idProsthetic = rs.getInt("id");
			String size = rs.getString("size");
			CompanyManager comMan = conMan.getcomMan();
			Company company = comMan.getCompany(rs.getInt("Company_ID"));
			PatientManager patientMan = conMan.getpatientMan();
			Patient patient = patientMan.getPatientByID(rs.getInt("Patient_ID"));
			NeedManager needMan = conMan.getneedMan();
			Need need = needMan.getNeed(rs.getInt("Need_ID"));
			MaterialManager materialsMan= conMan.getmaterialMan();
			Material material=materialsMan.getMaterial(rs.getInt("Material_ID"));
			
			Prosthetic p = new Prosthetic(idProsthetic,size,company,patient,need,rs.getInt("price"),material);
			return p;
		} catch (SQLException e) {
			System.out.println("Error in the database");
			e.printStackTrace();
		}
		return null;		
	}

}

