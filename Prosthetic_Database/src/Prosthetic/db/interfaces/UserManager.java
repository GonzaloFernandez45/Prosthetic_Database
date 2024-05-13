package Prosthetic.db.interfaces;

import Prosthetic.db.pojos.*;

public interface UserManager {
	
	public void register(User u);
	public void createRole(Role r);
	public Role getRole(String name);
	public void assignRole(User u,Role r);
	//return null if there is no user,login
	public User login(String username, String password);

}
