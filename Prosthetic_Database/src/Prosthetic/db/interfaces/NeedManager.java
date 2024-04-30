package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface NeedManager {
	
	public void addNeed (Need n);
	public Need getNeed (int id);
	public Need getNeedByType (String type);
	
}
