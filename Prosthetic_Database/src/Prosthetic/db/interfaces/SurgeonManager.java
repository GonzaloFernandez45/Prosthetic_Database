package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface SurgeonManager {
	
	public void addSurgeon (Surgeon s);
	public Surgeon getSurgeonbysurgery (int surgery_ID);
	public Surgeon getSurgeon(int id);
	public void deleteSurgeon (Surgeon s);
	public void scheduleSurgery (Surgery s);
	public String resultSurgery ();
	public void registerPatient (Patient p);
	public List<Surgeon>SearchSurgeonByName(String name, String surname);
 }
