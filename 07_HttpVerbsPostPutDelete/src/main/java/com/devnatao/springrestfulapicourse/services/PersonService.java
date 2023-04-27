package com.devnatao.springrestfulapicourse.services;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import com.devnatao.springrestfulapicourse.model.Person;

@Service
public class PersonService {
	
	// Simulates ID
	private final AtomicLong counter = new AtomicLong();
	// Will print info on log (console)
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public Person findById(Long id) {
		logger.info("Finding one person!");
		Person person = new Person();
	
		// Mocking one person
		person.setId(counter.incrementAndGet());
		person.setFirstName("Nathan");
		person.setLastName("Barros");
		person.setAdress("São Paulo - SP");
		person.setGender("Male");	
		return person;
	}
	
	public List<Person> findAll() {
		logger.info("Finding all people!");
		List<Person> persons = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			Person person = mockPerson(i);
			persons.add(person);
		}
		return persons;
	}
	
	public Person create(Person person) {
		logger.info("Creating person");
		return person;
	}
	
	public Person update(Person person) {
		logger.info("Updating a person");
		return person;
	}
	
	public void delete(Long id) {
		logger.info("Person deleted");
	}

	// Mocking persons
	private Person mockPerson(int i) {
		Person person = new Person();
		
		person.setId(counter.incrementAndGet());
		person.setFirstName("First name: " + i);
		person.setLastName("Last name: " + i);
		person.setAdress("Adress: " + i);
		person.setGender("Gender: " + i);	
		return person;
	}
}
