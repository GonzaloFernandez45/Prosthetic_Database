package Prosthetic.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import Prosthetic.db.interfaces.*;
import Prosthetic.db.jdbc.ConnectionManager;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;
import Prosthetic.db.pojos.Prosthetic;
import Prosthetic.db.xml.utils.CustomErrorHandler;


public class XMLPatientManager implements XmlPatientManager {
	
	private static Connection c;
	private ConnectionManager conMan ;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	
	public XMLPatientManager(ConnectionManager conMan) {
		this.conMan = conMan;
		this.c = conMan.getConnection();
	}
	
	private static void printPatients() {
		try{
		String sql = "SELECT * FROM patient";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		List<Patient> patients = new ArrayList<>();
		while(rs.next()) {
			Integer id = rs.getInt("id");
			String PatientName = rs.getString("name");
			String surname = rs.getString("surname");
			String sex = rs.getString("sex");
			Date dob = rs.getDate("dob");
			Integer dni = rs.getInt("dni");
			Patient p = new Patient(id,PatientName,surname,sex,dob,dni);
			patients.add(p);
		}
		
		for (Patient pat : patients) {
			System.out.println(pat);
		}
		rs.close();
		ps.close();
		}catch(SQLException e) {
			System.out.println("Error looking for a book");
			e.printStackTrace();
		}
	} 
	
	
	@Override
	public void createXml(){
	
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		printPatients();
		System.out.print("Choose a patient to turn into an XML file:");
		int pat_id = Integer.parseInt(reader.readLine());
		String sql = "SELECT * FROM patient WHERE id = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, pat_id);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		ProstheticManager prosthMan = conMan.getprosMan();
		List<Prosthetic> prosthetics = prosthMan.getProstheticbyPatient(pat_id);
		NeedManager needMan= conMan.getneedMan();
		List<Need> needs = needMan.getNeedByPatient(pat_id);
		
		Patient patient = new Patient(rs.getInt("id"), rs.getString("name"), rs.getString("surname"), rs.getString("sex"), rs.getDate("dob"), rs.getInt("dni"));
		patient.setProsthetics(prosthetics);
		patient.setNeeds(needs);
		
		File file = new File("./xmls/PatientFile.xml");
		
		
		marshaller.marshal(patient, file);
		marshaller.marshal(patient, System.out);
		}catch(Exception ext){
			System.out.print(ext);
		}

	}

	@Override
	public void DTDCheckerPatient() {
		File xmlFile = new File("./xmls/External-Patient.xml"); 
        try {
        	
            DocumentBuilderFactory dBF = DocumentBuilderFactory.newInstance();
            
            dBF.setValidating(true);
            
            DocumentBuilder builder = dBF.newDocumentBuilder();
            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
            builder.setErrorHandler(customErrorHandler);
            
            Document doc = builder.parse(xmlFile);
            if (customErrorHandler.isValid()) {
                System.out.println(xmlFile + " was valid!");
            }
        } catch (ParserConfigurationException ex) {
            System.out.println(xmlFile + " error while parsing!");
        } catch (SAXException ex) {
            System.out.println(xmlFile + " was not well-formed!");
        } catch (IOException ex) {
            System.out.println(xmlFile + " was not accesible!");
        }

    }


	@Override
	public void Xml2JavaPatient() {
		try {
			// Create the JAXBContext
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		// Get the unmarshaller
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		// Use the Unmarshaller to unmarshal the XML document from a file
		File file = new File("./xmls/External-Patient.xml");
		Patient patient = (Patient) unmarshaller.unmarshal(file);

		// Print the report
		System.out.println("Patient:");
		System.out.println("Name: " + patient.getName());
		System.out.println("Surname: " + patient.getSurname());
		System.out.println("sex: " + patient.getSex());
		System.out.println("dob: " + patient.getDob());
		System.out.println("dni: " + patient.getDni());
		
		String sqlPatient = "SELECT * FROM patient WHERE name LIKE ? AND surname LIKE ? and dni = ?";
		PreparedStatement ps = c.prepareStatement(sqlPatient);
		ps.setString(1, patient.getName());
		ps.setString(2, patient.getSurname());
		ps.setInt(3, patient.getDni());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) { //si existe lo actualiza
			patient.setId(rs.getInt("id"));
			String sqlUpdatePatient = "UPDATE patient SET name = ?, surname = ?, sex = ?, dob = ?, dni = ? WHERE id = ?";
			PreparedStatement psUpdate = c.prepareStatement(sqlUpdatePatient);
			psUpdate.setString(1, patient.getName());
			psUpdate.setString(2, patient.getSurname());
			psUpdate.setString(3, patient.getSex());
			psUpdate.setDate(4, patient.getDob());
			psUpdate.setInt(5, patient.getDni());
			psUpdate.setInt(6, patient.getId());
			psUpdate.executeUpdate();
			psUpdate.close();
		}else { // si no existe lo inserta
			String sqlInsertPatient = "INSERT INTO patient (name, surname, sex, DOB, dni) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement psUpdate = c.prepareStatement(sqlInsertPatient);
			psUpdate.setString(1, patient.getName());
			psUpdate.setString(2, patient.getSurname());
			psUpdate.setString(3, patient.getSex());
			psUpdate.setDate(4, patient.getDob());
			psUpdate.setInt(5, patient.getDni());
			psUpdate.executeUpdate();
			psUpdate.close();
		}
		rs.close();
		ps.close();
		
		PatientManager patMan = conMan.getpatientMan();
		int patient_id = patMan.getIDofAPatient(patient.getName(), patient.getSurname(), patient.getDni());
		patient.setId(patient_id);
		
		NeedManager needMan = conMan.getneedMan();
	
		List<Prosthetic> prosthetics = patient.getProsthetics();
		List<Need> needs = patient.getNeeds();
		
		for(Need need : needs) {
			System.out.println("Type: "+need.getType());
		}
		
		for (Prosthetic prosthetic : prosthetics) {
			System.out.println("Size: "+prosthetic.getSize()+" Price: "+prosthetic.getPrice()+" Report: "+prosthetic.getReport()+" Need: "+prosthetic.getNeed().getType());
		}
		
		// Store the report in the database
		for(Need need : needs) {
			int need_id = needMan.getNeedByType(need.getType()).getId();
			need.setId(need_id);
			needMan.insertPatientNeed(need, patient);
		}
		
		System.out.println("MEte en patient_need");
		
		for (Prosthetic prosthetic : prosthetics) {
			System.out.println(prosthetic.getNeed());
			int need_id = needMan.getNeedByType(prosthetic.getNeed().getType()).getId();
			System.out.println(need_id);
			String sqlInsertProsthetic = "INSERT INTO prosthetic (size,Patient_ID,Need_ID,price,report) VALUES (?,?,?,?,?)";
			PreparedStatement psInsertProsthetic = c.prepareStatement(sqlInsertProsthetic);
			psInsertProsthetic.setString(1, prosthetic.getSize());
			psInsertProsthetic.setInt(2, patient.getId());
			psInsertProsthetic.setInt(3, needMan.getNeedByType(prosthetic.getNeed().getType()).getId());
			psInsertProsthetic.setInt(4, prosthetic.getPrice());
			psInsertProsthetic.setString(5, prosthetic.getReport());
			psInsertProsthetic.executeUpdate();
			psInsertProsthetic.close();
		}
		
		
		}catch(Exception ext) {
			System.out.print(ext);
		}
		
	}


	@Override
	public void Xml2Html(String sourcePath, String xsltPath,String resultDir) {
		TransformerFactory tFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = tFactory.newTransformer(new StreamSource(new File(xsltPath)));
			transformer.transform(new StreamSource(new File(sourcePath)),new StreamResult(new File(resultDir)));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
