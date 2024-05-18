package Prosthetic.db.xml;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import Prosthetic.db.pojos.*;

public class Xml2JavaPatient {

	private static final String PERSISTENCE_PROVIDER = "Prosthetic-provider";
	private static EntityManagerFactory factory;

	public static void main(String[] args) throws JAXBException {

		
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
	}
}
