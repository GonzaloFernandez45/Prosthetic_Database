package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface ProstheticManager {
	public void addProsthetic (Prosthetic p);
	public void deleteProsthetic(Prosthetic p);
	public Prosthetic getProstheticByID(int id);
	public List<Prosthetic> getProstheticbyNeed (Need n);
	public List<Prosthetic> getProstheticbyPatient(Patient pa);
	public List<Prosthetic> listProsthetics();
}
