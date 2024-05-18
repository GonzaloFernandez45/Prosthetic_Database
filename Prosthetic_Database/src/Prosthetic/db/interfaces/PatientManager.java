package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface PatientManager {
	
	public void addPatient(Patient p);
	public Patient getPatientByID(int id);
	public List<Patient> getPatientByName(String name);
	public void reportDelivery(int id, int Prosthetic_ID, String updateReport);
	public List<Patient> listPatients();
	public List<Patient> getPatientByIDandName();
	public Patient getPatientNameSurname(int id);
	


}
