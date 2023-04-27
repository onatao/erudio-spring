package com.devnatao.springrestfulapicourse.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devnatao.springrestfulapicourse.exceptions.ResourceNotFoundException;
import com.devnatao.springrestfulapicourse.model.Person;
import com.devnatao.springrestfulapicourse.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;

	// Will print info on log (console)
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public Person findById(Long id) {	
		return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records"));
	}
	
	public List<Person> findAll() {
		return repository.findAll();
	}
	
	public Person create(Person person) {
		logger.info("Creating person");
		return repository.save(person);
	}
	
	public Person update(Person person) {
		logger.info("Updating a person");
		
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		return repository.save(entity);
	}
	
	public void delete(Long id) {
		logger.info("Person deleted");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records"));
		repository.delete(entity);
	}
}
