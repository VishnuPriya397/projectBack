package com.daoimpl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.dao.UserDAO;
import com.model.User;

@Repository
public class UserDAOImpl implements UserDAO
{
	public UserDAOImpl(){}
	
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
	
	public User findUserByEmail(String email)
	{
		Session session= sessionFactory.openSession();
		User u=null;
		try{
			session.beginTransaction();
			u=(User) session.get(User.class,email);
			session.getTransaction().commit();
		}
		catch(HibernateException e)
		{
			e.printStackTrace();
		}
		return u;
	}

}


