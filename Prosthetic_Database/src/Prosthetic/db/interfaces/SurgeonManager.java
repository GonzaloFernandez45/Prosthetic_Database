package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface SurgeonManager {
	
	public void addsurgeon ();
	public void getsurgeonbysurgery (int id);
	public void getsurgeon(int id);
	public void deletesurgeon (Surgeon s);
	public void schedulesurgery ();
	public void reportdurgery ();
	public void registerpatient (Patient p);
}
