package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface NeedManager {
	
	public void addneed (Need n);
	public void getneed (int id);
	public void getneedbytype (String type);
	
}
