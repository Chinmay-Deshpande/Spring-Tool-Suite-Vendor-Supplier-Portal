package com.calypso.banking.accounts.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.calypso.banking.accounts.persistence.PersonPersistence;



@Repository
@Transactional(propagation=Propagation.REQUIRED)

public class PersonDao {

	Logger log = LoggerFactory.getLogger(PersonDao.class);

	@PersistenceContext
	private EntityManager entityManager;
	
	
	public PersonPersistence createUser(PersonPersistence person)
	{
		//PersonPersistence persistence=new PersonPersistence();
		
		log.info(person.getPersonCity());
		entityManager.persist(person);
		entityManager.flush();
		log.info("Person saved userid:{}", person.getUserId() );
		
		return person;
	}

}
