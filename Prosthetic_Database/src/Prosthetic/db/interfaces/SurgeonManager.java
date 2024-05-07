package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface SurgeonManager {
	
	public void addSurgeon (Surgeon s);
	public Surgeon getSurgeonbysurgery (int surgery_ID);
	public Surgeon getSurgeon(int id);
	public void deleteSurgeon (int surgeon_id);
	public String resultSurgery (int surgery_id);
	public List<Surgeon>SearchSurgeonByName(String name, String surname);
	public Surgeon listSurgeons();
 }
