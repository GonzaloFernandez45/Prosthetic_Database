package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface SurgeryManager {
	public void addSurgery (Surgery s);
	public void deleteSurgery(Surgery s);
	public void searchSurgerybyPatient(Patient p);
	public void searchSurgerybyDate(Surgery s);
	public void searchSurgerybySurgeon (Surgeon s);
	

}
