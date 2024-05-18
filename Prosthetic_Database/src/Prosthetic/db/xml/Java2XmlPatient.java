package Prosthetic.db.xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import Prosthetic.db.pojos.*;

public class Java2XmlPatient {

		private static EntityManager em;
		private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			
		private static void printPatients() {
			Query q1 = em.createNativeQuery("SELECT id, name, surname, dni FROM patients", Patient.class);
			List<Patient> patients = (List<Patient>) q1.getResultList();
			for (Patient pat : patients) {
				System.out.println(pat);
			}
		}
		
		public static void main(String[] args) throws Exception {
			
			em = Persistence.createEntityManagerFactory("Prosthetic-provider").createEntityManager();
			em.getTransaction().begin();
			em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
			em.getTransaction().commit();
					
			
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

		}
}
