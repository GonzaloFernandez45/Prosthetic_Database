package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface MaterialManager {
	public Material getMaterial(int id);
	public void deleteMaterial(int id);
	public void checkAvailability(int id);
	public void addMaterial(Material m);
	
	

}
