package Prosthetic.db.jpa;

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
		Query q= em.createNamedQuery("SELECT FROM roles WHERE name LIKE ?",Role.class);
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
		Query q = em.createNativeQuery("SELECT FROM users WHERE usermane = ? AND password = ?", User.class);
		q.setParameter(1, username);
		q.setParameter(2, password);
		// TODO remember to provide a bad password and see what happens
		User u = (User) q.getSingleResult();
		return u;
	}

}
