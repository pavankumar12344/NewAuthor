package com.infy.dao;

import java.util.List;

import com.infy.entity.Author;
import com.infy.entity.Book;

public interface AuthorDao {

	public Object addAuthor(Author a);
	public List<Author> getAuthors();
	public Author getAuthorById(int id);
}
