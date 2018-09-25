package com.calypso.banking.accounts.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.calypso.banking.accounts.persistence.PersonPersistence;
import java.lang.String;

@Repository
public interface PersonRepository extends CrudRepository<PersonPersistence, Integer>{

	public List<PersonPersistence> findByPersonNameAndPersonCity(String name, String city); //ie select * from table where PersonName=name AND PersonCity=city
	
	
	//paramerters name ie PersonName should match with same name as in PersonPersistence class which is personName, similarly for PersonCity
}
