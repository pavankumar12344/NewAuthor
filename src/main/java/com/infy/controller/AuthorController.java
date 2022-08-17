package com.infy.controller;

import com.infy.service.AuthorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infy.entity.Author;
import com.infy.exception.IdNotFoundException;

@RestController
public class AuthorController {

	@Autowired
	private AuthorService authorService;

	@Qualifier(value = "authorService")
	public void setAuthorService(AuthorService as) {
		this.authorService = as;
	}

	@PostMapping(value = "/addAuthor", consumes = "application/json")
	public ResponseEntity<Author> addAuthor(@RequestBody Author author) {
		return new ResponseEntity<Author>(authorService.addAuthor(author), HttpStatus.CREATED);
	}

	@GetMapping(value = "/getAuthors", consumes = "application/json", produces = "application/json")
	public ResponseEntity<List<Author>> getAuthors() {
		return new ResponseEntity<List<Author>>(authorService.getAuthors(), HttpStatus.OK);
	}

	@GetMapping(value = "/getAuthorById/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Author> getAuthorById(@PathVariable int id) {
		Author author = this.authorService.getAuthorById(id);
		if (author == null) {
			throw new IdNotFoundException("Author with id " + id + " is not found");
		}
		return new ResponseEntity<Author>(authorService.getAuthorById(id), HttpStatus.OK);
	}

	@GetMapping(value = "/getBookById/{id}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> getBookById(@PathVariable int id) {
		Object book = this.authorService.getBookById(id);
		if (book == null) {
			throw new IdNotFoundException("Book with id " + id + " is not found");
		}
		return new ResponseEntity<Object>(authorService.getBookById(id), HttpStatus.OK);
	}

	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<String> notFound(IdNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
