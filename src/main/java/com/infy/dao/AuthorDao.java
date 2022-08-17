package com.infy.dao;

import java.util.List;

import com.infy.entity.Author;

public interface AuthorDao {

	public Author addAuthor(Author a);

	public List<Author> getAuthors();

	public Author getAuthorById(int id);
}
