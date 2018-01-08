package com.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.dao.SupplierDAO;
import com.model.Supplier;

public class SupplierDAOImpl implements SupplierDAO {
		 
		public SupplierDAOImpl(){}

		@Autowired
	    private SessionFactory sessionFactory;
		public SupplierDAOImpl(SessionFactory sessionFactory)
		  {
			  this.sessionFactory=sessionFactory;
		  }
		  	
		  
		  	@Transactional
		    public boolean addSupplier(Supplier supplier) 
		    {
		        try
		        {
		        
		        Session session=sessionFactory.getCurrentSession();
				session.saveOrUpdate(supplier);
				return true;
		        }
		        catch(Exception e)
		        {
		        	System.out.println(e.getMessage());
		        return false;
		        }
		    }	
		  	
		  	@Transactional
		     public List<Supplier> retrieveSupplier() 
		     {
		         Session session=sessionFactory.openSession();
		         Query query=(Query) session.createQuery("from Supplier");
		         @SuppressWarnings("unchecked")
		         List<Supplier> listSupplier=((org.hibernate.Query) query).list();
		         session.close();
		         return listSupplier;
		     }
		  	@Transactional
			public Supplier getSupplierById(int sid) {
				String hql = "from"+" Supplier"+" where id=" + sid;
				Query query = (Query) sessionFactory.getCurrentSession().createQuery(hql);
				
				@SuppressWarnings("unchecked")
				List<Supplier> listSupplier = (List<Supplier>) ((org.hibernate.Query) query).list();
				
				if (listSupplier != null && !listSupplier.isEmpty()) {
					return listSupplier.get(0);
				}
				
				return null;
			}

			@Transactional
			public Supplier removeSupplierById(int sid) {
				Supplier SupplierToDelete = new Supplier();
				SupplierToDelete.setSid(sid);
				sessionFactory.getCurrentSession().delete(SupplierToDelete);
				return SupplierToDelete;
			}
			
	}




