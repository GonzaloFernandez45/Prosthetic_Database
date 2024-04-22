package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface NeedManager {
	
	public void addneed (Need n);
	public Need getneed (int id);
	public Need getneedbyoftype (String type);
	
}
