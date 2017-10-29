package fr.eservices.drive.dao.impl;

import javax.persistence.EntityManager;

import fr.eservices.drive.dao.UserDao;
import fr.eservices.drive.model.User;

public class UserJPADao extends UserDao {
	
	EntityManager em;
	
	@Override
	public User find(String login) {
		return em.find(User.class, login);
	}
	
	@Override
	public void save(User user) {
		em.persist(user);
	}

}
