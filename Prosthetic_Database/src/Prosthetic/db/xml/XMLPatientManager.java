package Prosthetic.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
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

import Prosthetic.db.interfaces.XmlPatientManager;
import Prosthetic.db.pojos.Need;
import Prosthetic.db.pojos.Patient;
import Prosthetic.db.pojos.Prosthetic;
import Prosthetic.db.xml.utils.CustomErrorHandler;


public class XMLPatientManager implements XmlPatientManager {

	private static EntityManager em;
	private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static final String PERSISTENCE_PROVIDER = "prosthetic-provider";
	private static EntityManagerFactory factory;
	
	private static void printPatients() {
		Query q1 = em.createNativeQuery("SELECT * FROM patient", Patient.class);
		List<Patient> patients = (List<Patient>) q1.getResultList();
		for (Patient pat : patients) {
			System.out.println(pat);
		}
	} 
	
	
	@Override
	public void createXml(){
		em = Persistence.createEntityManagerFactory("prosthetic-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
				
		try {
		JAXBContext jaxbContext = JAXBContext.newInstance(Patient.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,Boolean.TRUE);
		
		printPatients();
		System.out.print("Choose a patient to turn into an XML file:");
		int pat_id = Integer.parseInt(reader.readLine());
		Query q2 = em.createNativeQuery("SELECT * FROM patient WHERE id = ?", Patient.class);
		q2.setParameter(1, pat_id);
		Patient patient = (Patient) q2.getSingleResult();
		
		
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
		
		
		
		List<Prosthetic> pros = patient.getProsthetics();
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

		
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_PROVIDER);
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();

		
		EntityTransaction tx1 = em.getTransaction();

		
		tx1.begin();

		
		for (Prosthetic prosthetic : pros) {
			em.persist(prosthetic);
		}
		em.persist(patient);
		
		
		for (Need need : needs) {
			em.persist(need);
		}
		em.persist(patient);
		
		tx1.commit();
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
