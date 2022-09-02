package com.infy.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.infy.exception.HibernateException;


public class BookDaoImpl implements BookDao {

	private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public Object getBookById(int id) {
		try {
			Session session = sessionFactory.getCurrentSession();
			Object book = session
					.createQuery("SELECT b.bid, b.title, a.name FROM Book b JOIN b.author a WHERE b.bid = :bid")
					.setParameter("bid", id).uniqueResult();
			logger.info("Book with author successfully, Book Details :" + book);
			return book;
		} catch (HibernateException re) {
			logger.error("error occured with exception", re);
		}
		return id;

	}

}
