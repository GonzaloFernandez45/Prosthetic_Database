package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface ProstheticManager {
	public void addProsthetic (Prosthetic p);
	public void deleteProsthetic(Prosthetic p);
	public void findProstheticbyNeed (Need n);
	public void searchProstheticbyPatient(Patient pa);
	
}
