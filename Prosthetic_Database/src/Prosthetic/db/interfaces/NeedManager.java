package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface NeedManager {
	
	public void addNeed (Need n);
	public Need getNeed (int id);
	public List<Need> listNeeds();
	public List<Need> getNeedByPatient(int Patient_ID);
	public void insertPatientNeed(Need need, Patient p);
	public Need getNeedByType(String typeNeed);
	
}
