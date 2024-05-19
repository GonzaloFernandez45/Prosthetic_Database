package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface MaterialManager {
	public Material getMaterial(int id);
	public void deleteMaterial(int id);
	public String checkAvailability(int id);
	public void addMaterial(Material m);
	public List<Material> listMaterials();
	public void updateAvailability(int Material_ID, String updateAvailability);
	

}
