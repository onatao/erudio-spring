package com.devnatao.springrestfulapicourse.services;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
// HATEOAS STATIC IMPORTS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
// - - - - - - - - - - - 
import org.springframework.stereotype.Service;

import com.devnatao.springrestfulapicourse.controllers.PersonController;
import com.devnatao.springrestfulapicourse.data.vo.v1.PersonVO;
import com.devnatao.springrestfulapicourse.exceptions.RequiredObjectIsNullException;
import com.devnatao.springrestfulapicourse.exceptions.ResourceNotFoundException;
import com.devnatao.springrestfulapicourse.mapper.DozerMapper;
import com.devnatao.springrestfulapicourse.model.Person;
import com.devnatao.springrestfulapicourse.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository repository;

	// Will print info on log (console)
	private Logger logger = Logger.getLogger(PersonService.class.getName());

	public PersonVO findById(Long id) {	
		var entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records"));
		// Creating entityVO (repository entity converted)
		PersonVO entityVO = DozerMapper.parseObject(entity, PersonVO.class);
		// Implementing HATEOAS - after static imports
		entityVO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());
		// returning entityVO to PersonController
		return entityVO;
	}
	
	public List<PersonVO> findAll() {
		var personsVO =  DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
		// Implementing HATEOAS
		personsVO.stream()
				.map(e -> e.add(linkTo(methodOn(PersonController.class)
											.findById(e.getKey())).withSelfRel()))
													.collect(Collectors.toList());
		
		// Returning List<Person> 
		return personsVO;
	}
	
	public PersonVO create(PersonVO person) {
		if (person == null) throw new RequiredObjectIsNullException();
		logger.info("Creating person");
		// Converting VO to Entity and saving
		var entityConverted = DozerMapper.parseObject(person, Person.class);
		repository.save(entityConverted);
		// Converted VO
		var entityVO = DozerMapper.parseObject(entityConverted, PersonVO.class);
		// Implementing HATEOAS
		entityVO.add(linkTo(methodOn(PersonController.class).findById(entityVO.getKey())).withSelfRel());
		return entityVO;
	}
	
	public PersonVO update(PersonVO person) {
		if (person == null) throw new RequiredObjectIsNullException();
		logger.info("Updating a person");
		// Finding 
		Person entity = repository.findById(person.getKey())
				.orElseThrow(() -> new ResourceNotFoundException("No records"));
		// Updating information and saving updated entity
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		repository.save(entity);
	
		
		var entityVO = DozerMapper.parseObject(entity, PersonVO.class);
		// Implementing HATEOAS
		entityVO.add(linkTo(methodOn(PersonController.class).findById(entityVO.getKey())).withSelfRel());
		// Returning entity VO
		return entityVO;
	}
	
	public void delete(Long id) {
		logger.info("Person deleted");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records"));
		repository.delete(entity);
	}
}
