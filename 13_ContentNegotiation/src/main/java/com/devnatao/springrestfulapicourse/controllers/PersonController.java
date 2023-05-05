package com.devnatao.springrestfulapicourse.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devnatao.springrestfulapicourse.data.vo.v1.PersonVO;
import com.devnatao.springrestfulapicourse.services.PersonService;
import com.devnatao.springrestfulapicourse.util.MediaType;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

	@Autowired
	PersonService service;
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, 
							MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public ResponseEntity<PersonVO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public ResponseEntity<List<PersonVO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, 
						produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
		return ResponseEntity.ok().body(service.create(person));
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
						produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {
		return ResponseEntity.ok().body(service.update(person));
	}
	
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	public ResponseEntity<?> delete(@PathVariable Long id) { 
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
 }
