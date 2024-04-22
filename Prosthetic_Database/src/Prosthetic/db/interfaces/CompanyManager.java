package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface CompanyManager {
	public void addCompany(Company c);
	public void createProsthetic (Prosthetic p);
	public void deleteCompany(Company c);
	public List<Patient> checkDemand();
	public List<Need> checkNeeds(Patient p);
	
}
