package com.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.ProductDAO;
import com.model.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	public ProductDAOImpl(){}

	@Autowired
	SessionFactory sessionFactory;

	public ProductDAOImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}
	@Transactional
    public boolean saveProduct(Product product) 
    {
        try
        {
        
        Session session=sessionFactory.getCurrentSession();
		session.saveOrUpdate(product);
		return true;
        }
        catch(Exception e)
        {
        	System.out.println(e.getMessage());
        return false;
        }
    }	
	
	 @Transactional
     public List<Product> retrieveProduct() 
     {
         Session session=sessionFactory.openSession();
         Query query=session.createQuery("from Product");
         @SuppressWarnings("unchecked")
         List<Product> listProduct=query.list();
         session.close();
         return listProduct;
     }
	 @Transactional
		public Product getProductById(int id) {
			String hql = "from" + " Product" + " where id=" + id;
			Query query = sessionFactory.getCurrentSession().createQuery(hql);

			@SuppressWarnings("unchecked")
			List<Product> listProduct = (List<Product>) query.list();

			if (listProduct != null && !listProduct.isEmpty()) {
				return listProduct.get(0);
			}

			return null;
		}
	 
	 @Transactional
	 public void updateProduct(Product p){
		 Session session=sessionFactory.openSession();
		try{
		 session.beginTransaction();
		 session.update(p);
		 session.getTransaction().commit();
		}
		catch(HibernateException e){
			e.getStackTrace();
			session.getTransaction().rollback();
		}
	 }
	 
	 @Transactional
	 public void deleteProduct(int id){
		 Session session=sessionFactory.openSession();
		try{
		 session.beginTransaction();
		 session.delete(id);
		 session.getTransaction().commit();
		}
		catch(HibernateException e){
			e.getStackTrace();
			session.getTransaction().rollback();
		}
	 }

		
}
