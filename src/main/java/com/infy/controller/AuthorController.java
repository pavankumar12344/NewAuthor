package com.infy.controller;
import com.infy.service.AuthorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.infy.entity.Author;



@RestController
public class AuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	
	@Qualifier(value="authorService")
	public void setAuthorService(AuthorService as){
		this.authorService = as;
	}
	
	@PostMapping(value="/addAuthor",consumes = "application/json")
	public Object addAuthor(@RequestBody Author author) {
		 authorService.addAuthor(author);
		 return "successfully added"; 
	}
	
	@GetMapping(value="/getAuthors",consumes="application/json",produces="application/json")
	public List<Author> getAuthors(){
		return authorService.getAuthors();
	}
	
	@GetMapping(value="/getAuthorById/{id}",consumes="application/json",produces="application/json")
	public Author getAuthorById(@PathVariable int id) {
		return authorService.getAuthorById(id);
	}
	
	@GetMapping(value="/getBookById/{id}",consumes="application/json",produces="application/json")
	public Object getBookById(@PathVariable int id) {
		return authorService.getBookById(id);
	}
	
}

