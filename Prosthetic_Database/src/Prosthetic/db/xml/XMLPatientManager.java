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
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

		File file = new File("./xmls/External-Patient.xml");
		Patient patient = (Patient) unmarshaller.unmarshal(file);

		
		System.out.println("Patient:");
		System.out.println("Name: " + patient.getName());
		System.out.println("Surname: " + patient.getSurname());
		System.out.println("sex: " + patient.getSurname());
		System.out.println("dob: " + patient.getSurname());
		System.out.println("dni: " + patient.getSurname());
		
		ProstheticManager prosthMan = conMan.getprosMan();
		NeedManager needMan = conMan.getneedMan();
		PatientManager patMan = conMan.getpatientMan();


		
		List<Prosthetic> pros = prosthMan.getProstheticbyPatient();
		
		for (Prosthetic p : pros) {
			System.out.println("Prosthetic: ");
			System.out.println("Price: " + p.getPrice());
			System.out.println("Size: " + p.getSize());
			System.out.println("Report: " + p.getReport());
		}
		
		List<Need> needs = patient.getNeeds();
		for (Need need : needs) {
			System.out.println("Need: " + need.getType());
		}
		
		
		for (Prosthetic prosthetic : pros) {
			prosthMan.addProsthetic(prosthetic);
		}
		patient.setProsthetics(pros);
		
		for (Need need : needs) {
			needMan.insertPatientNeed(need, patient);
		}
		patient.setNeeds(needs);
		
		patMan.addPatient(patient);
		
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
