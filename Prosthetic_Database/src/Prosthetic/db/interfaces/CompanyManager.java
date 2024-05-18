package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface CompanyManager {
	public void addCompany(Company c);
	public void deleteCompany(String com_name);
	public List<Need> checkNeeds(int patient_id);
	public Company getCompany(int id);
	public List<Company> listCompanies();
	public List<Company> listCompaniesIDandName();
	
	
}
