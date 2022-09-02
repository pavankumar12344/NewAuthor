package com.infy;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.infy.dao.AuthorDao;
import com.infy.dao.BookDao;
import com.infy.entity.Author;
import com.infy.entity.Book;
import com.infy.service.AuthorServiceImpl;

public class ServiceTest {

	@Mock
	AuthorDao authorDao;

	@Mock
	BookDao bookDao;

	@InjectMocks
	AuthorServiceImpl authorService;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void GetAuthorTest() {
		Book b1 = new Book(1, "k1");

		Book b2 = new Book(2, "k2");

		Author author = new Author(1, "kartik", Arrays.asList(b1, b2));

		when(authorDao.getAuthorById(1)).thenReturn(author);

		assertNotNull(authorDao.getAuthorById(1));

	}

	@Test
	public void getAllRecordsSuccess() throws Exception {

		Book b1 = new Book(1, "k1");

		Book b2 = new Book(2, "k2");

		Author a1 = new Author(1, "kartik", Arrays.asList(b1, b2));

		Author a2 = new Author(2, "vennela", Arrays.asList(b1, b2));

		List<Author> author = new ArrayList<Author>(Arrays.asList(a1, a2));

		Mockito.when(authorDao.getAuthors()).thenReturn(author);

		assertNotNull(authorDao.getAuthors());

		assertEquals(author.size(), 2);
	}

	@Test
	public void addAuthorSuccess() throws Exception {

		Book b1 = new Book(1, "k1");

		Book b2 = new Book(2, "k2");

		Author author = new Author(1, "kartik", Arrays.asList(b1, b2));

		Mockito.when(authorDao.addAuthor(Mockito.any(Author.class))).thenReturn(author);

		Author author1 = authorDao.addAuthor(author);
		
		assertNotNull(author1);
	}

	@Test
	public void getBookTest() throws Exception {

		Book book = new Book(1, "k1");

		when(bookDao.getBookById(1)).thenReturn(book);

		assertNotNull(bookDao.getBookById(1));
	}
}
