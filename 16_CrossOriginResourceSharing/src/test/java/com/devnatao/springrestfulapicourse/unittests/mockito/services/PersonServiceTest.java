package com.devnatao.springrestfulapicourse.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.devnatao.springrestfulapicourse.data.vo.v1.PersonVO;
import com.devnatao.springrestfulapicourse.exceptions.RequiredObjectIsNullException;
import com.devnatao.springrestfulapicourse.model.Person;
import com.devnatao.springrestfulapicourse.repository.PersonRepository;
import com.devnatao.springrestfulapicourse.services.PersonService;
import com.devnatao.springrestfulapicourse.unittests.MockPerson;

@TestInstance(Lifecycle.PER_CLASS) // define the lifecyle (per class)
@ExtendWith(MockitoExtension.class) // extends to use Mockito
class PersonServiceTest {
	
	MockPerson input;
	
	/*
	 * Inject one mock to PersonRepository, that
	 * was injected on PersonService class
	 */
	@InjectMocks
	private PersonService service;
	
	/*
	 * Mocking one PersonRepository
	 */
	@Mock
	PersonRepository repository;
	

	@BeforeEach
	void setUpMocks() throws Exception {
		input = new MockPerson();
		MockitoAnnotations.openMocks(this); // opening mocks
	}

	@Test
	void testFindById() {
		// Mocking one entity (without ID) - Person.class
		Person entity = input.mockEntity(1); 
		// Setting ID
		entity.setId(1L);
		
		/*
		 * When mock perform "repository.findById(1L)" must
		 * return one Optional of person (because person has 1L as ID value). 
		 */
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		
		// Result
		var result = service.findById(1L);
		
		// Verification (assertions)
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		// Verifying result link - HATEOAS
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		
		// Other assertions
		assertEquals("Addres Test1", result.getAdress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}

	@Test
	void testFindAll() {
		// Mocking one entity list
		List<Person> list = input.mockEntityList(); 
		
		/*
		 * When mock perform "repository.findById(1L)" must
		 * return one Optional of person (because person has 1L as ID value). 
		 */
		when(repository.findAll()).thenReturn(list);
		// Result
		var people = service.findAll();
		
		// Verification (assertions)
		assertNotNull(people);
		assertEquals(14, people.size());
		
		// Verifying some persons from people list
		
		// PERSON ONE
		
		var personOne = people.get(1);
				
		// Verification (assertions)
		assertNotNull(personOne);
		assertNotNull(personOne.getKey());
		assertNotNull(personOne.getLinks());
				
		// Verifying result link - HATEOAS
		assertTrue(personOne.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
				
		// Other assertions
		assertEquals("Addres Test1", personOne.getAdress());
		assertEquals("First Name Test1", personOne.getFirstName());
		assertEquals("Last Name Test1", personOne.getLastName());
		assertEquals("Female", personOne.getGender());
		
		// PERSON FOUR
		
		var personFour = people.get(4);
		
		// Verification (assertions)
		assertNotNull(personFour);
		assertNotNull(personFour.getKey());
		assertNotNull(personFour.getLinks());
				
		// Verifying result link - HATEOAS
		assertTrue(personFour.toString().contains("links: [</api/person/v1/4>;rel=\"self\"]"));
				
		// Other assertions
		assertEquals("Addres Test4", personFour.getAdress());
		assertEquals("First Name Test4", personFour.getFirstName());
		assertEquals("Last Name Test4", personFour.getLastName());
		assertEquals("Male", personFour.getGender());
		
		// PERSON SEVEN
		
		var personSeven = people.get(7);
		
		// Verification (assertions)
		assertNotNull(personSeven);
		assertNotNull(personSeven.getKey());
		assertNotNull(personSeven.getLinks());
				
		// Verifying result link - HATEOAS
		assertTrue(personSeven.toString().contains("links: [</api/person/v1/7>;rel=\"self\"]"));
				
		// Other assertions
		assertEquals("Addres Test7", personSeven.getAdress());
		assertEquals("First Name Test7", personSeven.getFirstName());
		assertEquals("Last Name Test7", personSeven.getLastName());
		assertEquals("Female", personSeven.getGender());
	}

	@Test
	void testCreate() {
		// Mocking one entity (before persistence, without ID) - Person.class
		Person entity = input.mockEntity(1); 
		
		// Persisted entity - Must have ID
		Person persisted = entity;
		persisted.setId(1L);	
		
		// Setting VO ID 
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
		
		/*
		 * When mock perform "repository.save(entity)" must
		 * return persisted (entity with ID). 
		 */
		when(repository.save(entity)).thenReturn(persisted);
		
		// Result
		var result = service.create(vo);
		
		// Verification (assertions)
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		// Verifying result link - HATEOAS
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
		
		// Other assertions
		assertEquals("Addres Test1", result.getAdress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testCreateWithNullPerson() {
	
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		// Setting expected message - same message at RequiredObjectIsNull.class
		String expectedMessage = "Isnt allowed to persisted an null object!";
		// Setting actual message - error
		String actualMessage = exception.getMessage();
		
		// Verifying
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testUpdate() {
		// Mocking one entity - Person.class
		Person entity = input.mockEntity(1); 
		entity.setId(1L);		
		
		// Persisted entity - Must have ID
		Person persisted = entity;
		persisted.setId(1L);	
				
		// Setting VO ID 
		PersonVO vo = input.mockVO(1);
		vo.setKey(1L);
				
	   /*
		* When mock perform "repository.findById(1L)" must
		* return one Optional of person (because person has 1L as ID value). 
		*/
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
		/*
		 * When mock perform "repository.save(entity)" must
		 * return persisted (entity with ID). 
	   	 */
		when(repository.save(entity)).thenReturn(persisted);
				
		// Result
		var result = service.update(vo);
				
		// Verification (assertions)
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
				
		// Verifying result link - HATEOAS
		assertTrue(result.toString().contains("links: [</api/person/v1/1>;rel=\"self\"]"));
				
		// Other assertions
		assertEquals("Addres Test1", result.getAdress());
		assertEquals("First Name Test1", result.getFirstName());
		assertEquals("Last Name Test1", result.getLastName());
		assertEquals("Female", result.getGender());
	}
	
	@Test
	void testUpdateWithNullPerson() {
	
		Exception exception = assertThrows(RequiredObjectIsNullException.class, () -> {
			service.create(null);
		});
		
		// Setting expected message - same message at RequiredObjectIsNull.class
		String expectedMessage = "Isnt allowed to persisted an null object!";
		// Setting actual message - error
		String actualMessage = exception.getMessage();
		
		// Verifying
		assertTrue(actualMessage.contains(expectedMessage));
	}

	@Test
	void testDelete() {
		// Mocking one entity (without ID) - Person.class
		Person entity = input.mockEntity(1); 
		// Setting ID
		entity.setId(1L);
				
		/*
		 * When mock perform "repository.findById(1L)" must
		 * return one Optional of person (because person has 1L as ID value). 
		 */
		when(repository.findById(1L)).thenReturn(Optional.of(entity));
				
		// Result
		service.delete(1L);
	}

}
