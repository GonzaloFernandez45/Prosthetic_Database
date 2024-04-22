package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface SurgeonManager {
	
	public void addSurgeon ();
	public Surgeon getSurgeonbysurgery (Surgery s);
	public Surgeon getSurgeon(int id);
	public void deleteSurgeon (Surgeon s);
	public void scheduleSurgery (Surgery s);
	public String resultSurgery ();
	public void registerPatient (Patient p);
}
