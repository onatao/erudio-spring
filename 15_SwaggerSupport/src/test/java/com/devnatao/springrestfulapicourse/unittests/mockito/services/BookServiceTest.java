package com.devnatao.springrestfulapicourse.unittests.mockito.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import com.devnatao.springrestfulapicourse.data.vo.v1.BookVO;
import com.devnatao.springrestfulapicourse.model.Book;
import com.devnatao.springrestfulapicourse.repository.BookRepository;
import com.devnatao.springrestfulapicourse.services.BookService;
import com.devnatao.springrestfulapicourse.unittests.MockBook;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
class BookServiceTest {
	
	MockBook input;
	
	@InjectMocks
	private BookService service;
	
	@Mock
	BookRepository repository;

	@BeforeEach
	void setUp() throws Exception {
		input = new MockBook();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreate() {
		// Mocking book entity - without id
		Book book = input.mockEntity(1);
		
		// Persisted entity
		Book persistedBook = book;
		persistedBook.setId(1L);
		
		// mocking BookVO
		BookVO bookVo = input.mockVO(1);
		bookVo.setKey(1L);
		
		/*
		 * When mock perform "repository.save(entity)" must
		 * return persisted (entity with ID). 
		 */
		when(repository.save(book)).thenReturn(persistedBook);
		
		// Result
		var result = service.create(bookVo);
		
		// Assertions
		assertNotNull(result);
		assertNotNull(result.getKey());
		assertNotNull(result.getLinks());
		
		// Verifying result link
		assertTrue(result.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		
		// Other assertions
		assertEquals("Author1", result.getAuthor());
		assertEquals("Launch Date1", result.getLaunchDate());
		assertEquals(5d + 1, result.getPrice());
		assertEquals("Book title1", result.getTitle());
	}

	@Test
	void testDelete() {
		// Mocking one book
		Book book = input.mockEntity();
		// Setting books id
		book.setId(1L);
		
		/*
		 * When mock perform "repository.findById(1L)" must
		 * return one Optional of book (because person has 1L as ID value). 
		 */
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		
		// Result
		service.delete(1L);
	}

	@Test
	void testUpdate() {
		// Mocking one book
		Book book = input.mockEntity(1);
		book.setId(1L);
		
		// Persisted book
		Book bookPersisted = book;
		bookPersisted.setId(1L);
		
		// Mocking VO
		BookVO bookVo = input.mockVO(1);
		bookVo.setKey(1L);
		
		/*
		 * When mock perform "repository.findById(1L)" must
		 * return one Optional of book (because person has 1L as ID value). 
		 */
		when(repository.findById(1L)).thenReturn(Optional.of(book));
		
		/*
		 * When mock perform "repository.save(entity)" must
		 * return persisted (entity with ID). 
	   	 */
		when(repository.save(book)).thenReturn(bookPersisted);
		
		// Result (updated BookVO)
		var updatedBookResult = service.update(bookVo);
		
		// Assertions
		assertNotNull(updatedBookResult);
		assertNotNull(updatedBookResult.getKey());
		assertNotNull(updatedBookResult.getLinks());

		assertTrue(updatedBookResult.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		
		assertEquals("Author1", updatedBookResult.getAuthor());
		assertEquals("Launch Date1", updatedBookResult.getLaunchDate());
		assertEquals(5d + 1, updatedBookResult.getPrice());
		assertEquals("Book title1", updatedBookResult.getTitle());
	}

	@Test
	void testFindById() {
		// Mocking one book - entity
		Book book = input.mockEntity(1);
		book.setId(1L);
		
		/*
		 * When mock perform "repository.findById(1L)" must
		 * return one Optional of book (because book has 1L as ID value). 
		 */
		when(repository.findById(1L)).thenReturn(Optional.of(book));
	
		// Result - from .findById
		var resultedBook = service.findById(1L);
		
		// Assertions
		assertNotNull(resultedBook);
		assertNotNull(resultedBook.getKey());
		assertNotNull(resultedBook.getLinks());
		
		// Verifying HATEOAS link
		assertTrue(resultedBook.toString().contains("links: [</api/books/v1/1>;rel=\"self\"]"));
		
		assertEquals("Author1", resultedBook.getAuthor());
		assertEquals("Launch Date1", resultedBook.getLaunchDate());
		assertEquals(5d + 1, resultedBook.getPrice());
		assertEquals("Book title1", resultedBook.getTitle());
		
	}

	@Test
	void testFindAll() {
		// Mocking list of books - entity
		List<Book> bookList = input.mockEntityList();
		
		/*
		 * When mock perform "repository.findAll()" must
		 * return one List Optional of books (because books has 1L as ID value). 
		 */
		when(repository.findAll()).thenReturn(bookList);
		
		// Result - BookVO
		var resultBookListVo = service.findAll();
		
		// Assertions
		assertNotNull(resultBookListVo);
		assertEquals(14, resultBookListVo.size());
		
		// Verifying some books from the list
		
		// ONE
		var bookOne = resultBookListVo.get(1);
		
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
		
		assertTrue(bookOne.toString()
							.contains("links: [</api/books/v1/{id}>;rel=\"self\"]"));
		
		assertEquals("Author1", bookOne.getAuthor());
		assertEquals("Launch Date1", bookOne.getLaunchDate());
		assertEquals(5d + 1, bookOne.getPrice());
		assertEquals("Book title1", bookOne.getTitle());
		
		// TWO
		var bookTwo = resultBookListVo.get(2);
				
		assertNotNull(bookTwo);
		assertNotNull(bookTwo.getKey());
		assertNotNull(bookTwo.getLinks());
	
		assertTrue(bookTwo.toString()
									.contains("links: [</api/books/v1/{id}>;rel=\\\"self\\\"]"));
				
		assertEquals("Author2", bookTwo.getAuthor());
		assertEquals("Launch Date2", bookTwo.getLaunchDate());
		assertEquals(5d + 2, bookTwo.getPrice());
		assertEquals("Book title2", bookTwo.getTitle());
				
		// ONE
		var bookThree = resultBookListVo.get(3);
				
		assertNotNull(bookOne);
		assertNotNull(bookOne.getKey());
		assertNotNull(bookOne.getLinks());
				
		assertTrue(bookOne.toString()
									.contains("links: [</api/books/v1/3>;rel=\"self\"]"));
				
		assertEquals("Author3", bookOne.getAuthor());
		assertEquals("Launch Date3", bookOne.getLaunchDate());
		assertEquals(5d + 3, bookOne.getPrice());
		assertEquals("Book title3", bookOne.getTitle());			
	}

}
