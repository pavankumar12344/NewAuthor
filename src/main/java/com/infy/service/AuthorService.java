package com.infy.service;

import java.util.List;


import com.infy.entity.Author;


public interface AuthorService {

	public Object addAuthor(Author a);
	public List<Author> getAuthors();
	public Author getAuthorById(int id);
	public Object getBookById(int id);
	
}

