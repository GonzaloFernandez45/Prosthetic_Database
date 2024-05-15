package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface NeedManager {
	
	public void addNeed (Need n);
	public Need getNeed (int id);
	//public Need getNeedByType (String type);
	public List<Need> listNeeds();
	public List<Need> getNeedByPatient(int Patient_ID);
}
