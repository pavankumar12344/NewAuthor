package com.infy.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infy.entity.Author;
import com.infy.exception.HibernateException;


public class AuthorDaoImpl implements AuthorDao {

	private static final Logger logger = LoggerFactory.getLogger(AuthorDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public Author addAuthor(Author author) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(author);
			logger.info("Author saved successfully, Author Details=" + author);
			return author;
		} catch (HibernateException re) {
			logger.error("error occured with exception", re);
		}
		return author;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Author> getAuthors() {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			List<Author> authorList = session.createQuery("from Author").list();
			for (Author author : authorList) {
				logger.info("Author List:" + author);
			}
			return authorList;
		} catch (HibernateException re) {
			logger.error("error occured with exception", re);
		}
		return null;
		
	}

	@Override
	public Author getAuthorById(int id) {
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Author author = (Author) session.get(Author.class, id);
			logger.info("Author fetched successfully, Author details=" + author);
			return author;
		} catch (HibernateException re) {
			logger.error("error occured with exception", re);
		}
		return null;

	}

}
