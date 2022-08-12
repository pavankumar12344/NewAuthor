package com.infy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import com.infy.entity.Author;

@Repository
public class AuthorDaoImpl implements AuthorDao {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);
	
	
    private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public Object addAuthor(Author a) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(a);
		logger.info("Author saved successfully, Author Details="+a);
		return a;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAuthors() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Author> authorList = session.createQuery("from Author").list();
		for(Author a : authorList){
			logger.info("Author List:"+a);
		}
		return authorList;
	}

	@Override
	public Author getAuthorById(int id) {
	    Session session = this.sessionFactory.getCurrentSession();
	    Author a = (Author) session.get(Author.class, id); 
		logger.info("Author fetched successfully, Author details="+a);
		return a; 	
	}

	
}	
	
	
	
	
	