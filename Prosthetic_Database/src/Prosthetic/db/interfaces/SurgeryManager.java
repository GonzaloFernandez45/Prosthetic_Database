package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface SurgeryManager {
	public void addSurgery (Surgery s);
	public void deleteSurgery(Surgery s);
	public List<Surgery> searchSurgerybyPatient(int patient_id);
	public List<Surgery> searchSurgerybyDate(Surgery s);
	public List<Surgery> searchSurgerybySurgeon (Surgeon s);
	 

}
