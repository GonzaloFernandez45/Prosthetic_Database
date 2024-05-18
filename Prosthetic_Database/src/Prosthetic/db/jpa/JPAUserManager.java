package Prosthetic.db.jpa;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.*;

import Prosthetic.db.interfaces.UserManager;
import Prosthetic.db.pojos.Role;
import Prosthetic.db.pojos.User;

public class JPAUserManager implements UserManager {
	private EntityManager em;
	
	public JPAUserManager() {
		em=Persistence.createEntityManagerFactory("prosthetic-provider").createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("PRAGMA foreign_keys=ON").executeUpdate();
		em.getTransaction().commit();
		try {
		this.getRole("Manager");
		} catch(NoResultException w) {
			this.createRole(new Role("Manager"));
			this.createRole(new Role("Patient"));
			this.createRole(new Role("Surgeon"));
			this.createRole(new Role("Company"));
		}
		

	}
	
	@Override
	public String securePassword(String password){
	String passwordToHash = password;
    String generatedPassword = null;

    try 
    {
      // Create MessageDigest instance for MD5
      MessageDigest md = MessageDigest.getInstance("MD5");

      // Add password bytes to digest
      md.update(passwordToHash.getBytes());

      // Get the hash's bytes
      byte[] bytes = md.digest();

      // This bytes[] has bytes in decimal format. Convert it to hexadecimal format
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
      }

      // Get complete hashed password in hex format
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }
	@Override
	public void register(User u) {
		em.getTransaction().begin();
		em.persist(u);
		em.getTransaction().commit();
		
	}

	@Override
	public void createRole(Role r) {
		em.getTransaction().begin();
		em.persist(r);
		em.getTransaction().commit();
	}

	@Override
	public Role getRole(String name) {
		Query q= em.createNativeQuery("SELECT * FROM roles WHERE name LIKE ?",Role.class);
		q.setParameter(1, name);
		Role r= (Role)q.getSingleResult();
		return r;
	}

	@Override
	public void assignRole(User u, Role r) {
		em.getTransaction().begin();
		u.setRole(r);
		r.addUser(u);
		em.getTransaction().commit();

	}

	@Override
	public User login(String username, String password) {
		User u = null;
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username LIKE ? AND passwordHash LIKE ?", User.class);
		q.setParameter(1, username);
		q.setParameter(2, securePassword(password));
		// TODO remember to provide a bad password and see what happens
		try {
			u = (User) q.getSingleResult();
		}catch(NoResultException e) {
			
			return null;
		}
		return u;
	}
	
	@Override
	public void deleteUser(User u) {
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
	}
	
	@Override
	public void updateUser (User u, String newPasswordHash) {
		em.getTransaction().begin();
		u.setPasswordHash(newPasswordHash);
		em.getTransaction().commit();
	}
	
	public List<Role> getAllRoles(){
		Query q= em.createNativeQuery("SELECT * FROM roles",Role.class);
		List <Role> roles = (List<Role>) q.getResultList();
		return roles;
	}
	
	
	

}
