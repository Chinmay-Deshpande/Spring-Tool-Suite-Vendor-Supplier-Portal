package com.calypso.banking.accounts.service;

import java.awt.PageAttributes.MediaType;
import java.util.List;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.calypso.banking.accounts.dao.PersonDao;
import com.calypso.banking.accounts.model.User;
import com.calypso.banking.accounts.persistence.PersonPersistence;
import com.calypso.banking.accounts.repository.PersonRepository;

@Service
public class UserService {

	// Logger log=LoggerFactory(UserService.class);
	@Autowired
	public PersonRepository personRepository;
	
	@Autowired
	public PersonDao personDao;

	public User getuser(int userId) {
//		User userobj = new User();
//
 //		userobj.setUserid(3);
//		userobj.setName("ravi");
//		userobj.setCity("long beach");
//
//		if (userId == 0) {
//			throw new NullPointerException("userId is null");
//
//		}
		
		User userObj = invokeAccountsServer(userId);
		return userObj;
	}

	private User invokeAccountsServer(int id)
	{
		RestTemplate template=new RestTemplate();
		
		String URL="http://localhost:6060/v1/user/data/"+id;
		//set up headers
		
		
		//commented code is in class done,
		//current code is by me
		
		/*HttpHeaders headers=new HttpHeaders();
		headers.add("Content-Type", org.springframework.http.MediaType.APPLICATION_JSON_VALUE);
		headers.add("Accept", org.springframework.http.MediaType.APPLICATION_JSON_VALUE);

		HttpEntity entity=new HttpEntity<>(headers);*/
		
		return template.getForObject(URL, User.class);
		/*HttpEntity<User> response=template.exchange(URL, HttpMethod.GET, entity, User.class);
		
		//
		System.out.println(("response:{}" +response.getBody()));
		
		if(response.getBody()!=null)
		{
			System.out.println(response.getBody().getName());
		
		}
		return response.getBody();*/
	}
	public PersonPersistence createUser(User user) {
		PersonPersistence personPersistence = new PersonPersistence();

		
		personPersistence.setPersonName(user.getName());
		personPersistence.setPersonCity(user.getCity());
			
		//invoking spring-data
		//return personRepository.save(personPersistence);
		
		//invoking hibernate      configured in spring-config.xml and persistence.xml
		return personDao.createUser(personPersistence);
		
		
	}

	public PersonPersistence getUserById(int userId) {

		return personRepository.findById(userId).get();
	}

	public List<PersonPersistence> findAllUsers() {
		return (List<PersonPersistence>) personRepository.findAll();
	}

	public PersonPersistence updateUser(int userId, String city) {
		PersonPersistence persistence = personRepository.findById(userId).get();
		// log.info("userId:{},city:{},userId, persistence.getCity());

		persistence.setPersonCity(city);
		return personRepository.save(persistence);

	}

	public List<PersonPersistence> findUserByNameAndCity(String name, String city) {
		return personRepository.findByPersonNameAndPersonCity(name, city);
	}
}
