package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface CompanyManager {
	public void addCompany(Company c);
	public void deleteCompany(String com_name);
	public List<Patient> checkDemandByNeed(int need_id);
	public List<Need> checkNeeds(int patient_id);
	
}
