package com.infy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.infy.dao.AuthorDao;
import com.infy.dao.BookDao;
import com.infy.entity.Author;


public class AuthorServiceImpl implements AuthorService {

	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private BookDao bookDao;

	public void setAuthorDao(AuthorDao authorDao) {
		this.authorDao = authorDao;
	}

	public void setBookDao(BookDao bookDao) {
		this.bookDao = bookDao;
	}

	@Override
	@Transactional
	public Author addAuthor(Author author) {
		return this.authorDao.addAuthor(author);
	}

	@Override
	@Transactional
	public List<Author> getAuthors() {
		return this.authorDao.getAuthors();
	}

	@Override
	@Transactional
	public Author getAuthorById(int id) {
		return this.authorDao.getAuthorById(id);

	}

	@Override
	@Transactional
	public Object getBookById(int id) {
		return this.bookDao.getBookById(id);
	}

}
