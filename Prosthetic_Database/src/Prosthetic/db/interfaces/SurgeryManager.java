package Prosthetic.db.interfaces;

import java.sql.Date;
import java.util.List;

import Prosthetic.db.pojos.*;

public interface SurgeryManager {
	public void addSurgery (Surgery s);
	public void deleteSurgery(Surgery s);
	public List<Surgery> searchSurgerybyPatient(int patient_id);
	public List<Surgery> searchSurgerybyDate(Date date);
	public List<Surgery> searchSurgerybySurgeon (Surgeon s);
	public Surgery getSurgeryByProsthetic(int id);

}
