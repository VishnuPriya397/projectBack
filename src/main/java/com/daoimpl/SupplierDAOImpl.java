package com.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.SupplierDAO;
import com.model.Supplier;

@Repository
public class SupplierDAOImpl implements SupplierDAO {
		 
		public SupplierDAOImpl(){}

		@Autowired
	    SessionFactory sessionFactory;
		public SupplierDAOImpl(SessionFactory sessionFactory)
		  {
			  this.sessionFactory=sessionFactory;
		  }
		  	
		  

		public void insertSupplier(Supplier supplier)
		{
			Session session=sessionFactory.openSession();
		    session.beginTransaction();
		    session.saveOrUpdate(supplier);
			session.getTransaction().commit();

		}
		
		@SuppressWarnings("unchecked")
		public List<Supplier> retrieve()
		{
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			List<Supplier> li= session.createQuery("from Supplier").list();
			session.getTransaction().commit();
			return li;
		}
		public Supplier findBySuppId(int sid)
		{
			Session session=sessionFactory.openSession();
			Supplier s=null;
			try
			{
				session.beginTransaction();
				s= (Supplier) session.get(Supplier.class, sid);
				session.getTransaction().commit();
			}
			catch(HibernateException e)
			{
				System.out.println(e.getMessage());
				session.getTransaction().rollback();
			}
			return s;
		}
		
		public void updateSupp(Supplier s)
		{
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		session.update(s);
		session.getTransaction().commit();
		}

		public void deleteSupp(int sid) {
			Session session=sessionFactory.openSession();
			session.beginTransaction();
			Supplier s=(Supplier)session.get(Supplier.class,sid);
			session.delete(s);
			session.getTransaction().commit();
		}

	}




