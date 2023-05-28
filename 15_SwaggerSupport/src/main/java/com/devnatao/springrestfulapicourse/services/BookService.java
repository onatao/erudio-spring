package com.devnatao.springrestfulapicourse.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//HATEOAS STATIC IMPORTS
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
//- - - - - - - - - - - 

import com.devnatao.springrestfulapicourse.controllers.BookController;
import com.devnatao.springrestfulapicourse.data.vo.v1.BookVO;
import com.devnatao.springrestfulapicourse.exceptions.ResourceNotFoundException;
import com.devnatao.springrestfulapicourse.mapper.DozerMapper;
import com.devnatao.springrestfulapicourse.model.Book;
import com.devnatao.springrestfulapicourse.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository repository;
	
	
	@Transactional
	public BookVO create(BookVO entityVo) {
		var entityConverted = DozerMapper.parseObject(entityVo, Book.class);
		repository.save(entityConverted);
		
		var responseVo = DozerMapper.parseObject(entityConverted, BookVO.class);
		
		responseVo
			.add(linkTo(methodOn(BookController.class)
							.findById(responseVo.getKey()))
										.withSelfRel());
		return responseVo;
	}
	
	@Transactional
	public void delete(Long id) {
		var entity = repository.findById(id)
			.orElseThrow(() -> new ResourceNotFoundException("Not found"));
		repository.delete(entity);
	}
	
	@Transactional
	public BookVO update (BookVO entityVo) {
		Book entity = repository.findById(entityVo.getKey())
					.orElseThrow(() -> new ResourceNotFoundException("Not found"));
		
		entity.setAuthor(entityVo.getAuthor());
		entity.setLaunchDate(entityVo.getLaunchDate());
		entity.setPrice(entityVo.getPrice());
		entity.setTitle(entityVo.getTitle());
		
		repository.save(entity);
		var responseVo = DozerMapper.parseObject(entity, BookVO.class);
		
		responseVo.add(linkTo(methodOn(BookController.class)
							.findById(responseVo.getKey()))
								.withSelfRel());
		return responseVo;
	}
	
	@Transactional(readOnly = true)
	public BookVO findById(Long id) {
		Book entityBook = repository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Not found"));
		
		var entityVo = DozerMapper.parseObject(entityBook, BookVO.class);
		
		entityVo.add(linkTo(methodOn(BookController.class)
						.findById(entityVo.getKey()))
							.withSelfRel());	
		return entityVo;
	}
	
	@Transactional(readOnly = true)
	public List<BookVO> findAll() {
		List<BookVO> responseListVo = DozerMapper
							.parseListObjects(repository.findAll(), BookVO.class);
		
		responseListVo.stream()
						.map(e -> e.add(linkTo(methodOn(BookController.class)
									.findById(e.getKey()))
										.withSelfRel())).collect(Collectors.toList());
		return responseListVo;
	}
	
}
