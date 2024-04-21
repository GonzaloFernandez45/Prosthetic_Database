package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface PatientManager {
	
	public void addPatient(Patient p);
	public void deletePatient(Patient p);
	public Patient getPatientByID(int id);
	public List<Patient> getPatientByName(String name);
	public String reportDelivery();
	public void inputNeeds(List<Need> needs);


}
