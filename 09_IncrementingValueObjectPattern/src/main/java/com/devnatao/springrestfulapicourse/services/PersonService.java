package com.devnatao.springrestfulapicourse.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devnatao.springrestfulapicourse.data.vo.v1.PersonVO;
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
		
		return DozerMapper.parseObject(entity, PersonVO.class);
	}
	
	public List<PersonVO> findAll() {
		return DozerMapper.parseListObjects(repository.findAll(), PersonVO.class);
	}
	
	public PersonVO create(PersonVO person) {
		logger.info("Creating person");
		var entity = DozerMapper.parseObject(person, Person.class);
		var vo = DozerMapper.parseObject(repository.save(entity), PersonVO.class);
		return vo;
	}
	
	public PersonVO update(PersonVO person) {
		logger.info("Updating a person");
		
		Person entity = repository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		return DozerMapper.parseObject(repository.save(entity), PersonVO.class);
	}
	
	public void delete(Long id) {
		logger.info("Person deleted");
		Person entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records"));
		repository.delete(entity);
	}
}
