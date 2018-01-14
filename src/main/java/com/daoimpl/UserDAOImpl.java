package com.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dao.UserDAO;
import com.model.User;

@Repository
public class UserDAOImpl implements UserDAO
{
	public UserDAOImpl(){
		
	}
	@Autowired
    SessionFactory sessionFactory;

	public UserDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	public void insertUser(User user)
	{
	
		Session session=sessionFactory.openSession();
	    session.beginTransaction();
	    session.saveOrUpdate(user);
		session.getTransaction().commit();

	}

}


