package Prosthetic.db.jpa;

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
		Query q = em.createNativeQuery("SELECT * FROM users WHERE username = ? AND passwordHash = ?", User.class);
		q.setParameter(1, username);
		q.setParameter(2, password.hashCode());
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
	public void updateUser (User u, int newPasswordHash) {
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
