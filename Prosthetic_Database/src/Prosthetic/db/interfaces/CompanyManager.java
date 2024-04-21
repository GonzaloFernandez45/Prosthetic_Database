package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface CompanyManager {
	public void addCompany(Company a);
	public void createProsthetic (Prosthetic c);
	public List<Patient> checkDemand();
	public List<Need> checkNeeds(Patient p);
	
}
