package com.infy;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.infy.dao.AuthorDaoImpl;
import com.infy.dao.BookDaoImpl;
import com.infy.entity.Author;
import com.infy.entity.Book;

public class DaoTest {

	@Mock
	private Query query;

	@Mock
	private SessionFactory sessionFactory;

	@Mock
	private Session session;

	@InjectMocks
	AuthorDaoImpl authorDao;

	@InjectMocks
	BookDaoImpl bookDao;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		authorDao.setSessionFactory(sessionFactory);
		bookDao.setSessionFactory(sessionFactory);
	}

	@Test
	public void GetAuthorTest() {
		
		Book b1 = new Book(1, "k1");
		Book b2 = new Book(2, "k2");

		Author author = new Author(1, "kartik", Arrays.asList(b1, b2));

		when(session.get(Author.class, 1)).thenReturn(author);
		assertEquals(author, authorDao.getAuthorById(1));
	}

	@Test
	public void getAllRecordsSuccess() throws Exception {

		Book b1 = new Book(1, "k1");
		Book b2 = new Book(2, "k2");

		Author a1 = new Author(1, "kartik", Arrays.asList(b1, b2));
		Author a2 = new Author(2, "vennela", Arrays.asList(b1, b2));

		List<Author> author = new ArrayList<Author>(Arrays.asList(a1, a2));

		when(session.createQuery(Mockito.anyString())).thenReturn(query);
		when(query.list()).thenReturn(author);
		assertEquals(author, authorDao.getAuthors());

	}

	@Test
	public void addAuthorSuccess() throws Exception {

		Book b1 = new Book(1, "k1");
		Book b2 = new Book(2, "k2");

		Author author = new Author(1, "kartik", Arrays.asList(b1, b2));

		doNothing().when(session).persist(author);
		assertEquals(authorDao.addAuthor(any(Author.class)), author);
	}

	@Test
	public void getBookTest() throws Exception {

		Author a1 = new Author(1, "kartik");

		Book book = new Book(1, "k1", a1);

		when(session.createQuery(Mockito.anyString())).thenReturn(query);
		when(query.setParameter(anyString(), anyInt()).uniqueResult()).thenReturn(book);
		assertEquals(book, bookDao.getBookById(1));
	}
}























//assertEquals(author, authorDao.addAuthor(Mockito.any(Author.class)));
// Mockito.verify(session, Mockito.times(1)).persist(ArgumentMatchers.any(Author.class));
//when(a1.getName()).thenReturn(author.get(0).getName());
//MyList myList = mock(MyList.class);
//doNothing().when(myList).add(isA(Integer.class), isA(String.class));
//myList.add(0, "");
//
//verify(myList, times(1)).add(0, "");
