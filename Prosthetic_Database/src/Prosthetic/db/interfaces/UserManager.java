package Prosthetic.db.interfaces;

import java.util.List;

import Prosthetic.db.pojos.*;

public interface UserManager {
	
	public void register(User u);
	public void createRole(Role r);
	public Role getRole(String name);
	public List<Role> getAllRoles();
	public void assignRole(User u,Role r);
	public User login(String username, String password);
	public void deleteUser(User u);
	public void updateUser (User u, String newPasswordHash);
	public String securePassword(String password);
}
