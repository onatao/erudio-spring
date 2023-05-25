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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People") // Swagger UI 
public class PersonController {

	@Autowired
	PersonService service;
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON, 
							MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a person", description = "Finds a person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = 
							@Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unathorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
		)
	public ResponseEntity<PersonVO> findById(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@GetMapping(produces = {MediaType.APPLICATION_JSON,
					MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all people", description = "Find all people",
		tags = {"People"},
		responses = {
				@ApiResponse(description = "Success", responseCode = "200", content = {
								@Content(
										mediaType = "application/json",
										array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
										)
						}),
				@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
				@ApiResponse(description = "Unathorized", responseCode = "401", content = @Content),
				@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
				@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
				}
			)
	public ResponseEntity<List<PersonVO>> findAll() {
		return ResponseEntity.ok().body(service.findAll());
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}, 
						produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a new person", description = "Adds a new person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200", 
					content = 
							@Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unathorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
		)
	public ResponseEntity<PersonVO> create(@RequestBody PersonVO person) {
		return ResponseEntity.ok().body(service.create(person));
	}
	
	@PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
						produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Update one person", description = "Update one person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Updated", responseCode = "200", 
					content = 
							@Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unathorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
		)
	public ResponseEntity<PersonVO> update(@RequestBody PersonVO person) {
		return ResponseEntity.ok().body(service.update(person));
	}
	
	@DeleteMapping(consumes = {MediaType.APPLICATION_JSON, 
					MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
	@Operation(summary = "Delete person", description = "Delete person",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Deleted", responseCode = "200", content = @Content),
			@ApiResponse(description = "No Content", responseCode = "204", content = @Content),
			@ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
			@ApiResponse(description = "Unathorized", responseCode = "401", content = @Content),
			@ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
			@ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
			}
		)
	public ResponseEntity<?> delete(@PathVariable Long id) { 
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
 }
