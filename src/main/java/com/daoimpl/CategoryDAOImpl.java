package com.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CategoryDAO;
import com.model.Category;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
	
	public CategoryDAOImpl(){}
	
	@Autowired
    private SessionFactory sessionFactory;
	
	  public  CategoryDAOImpl(SessionFactory sessionFactory)
	  {
		  this.sessionFactory=sessionFactory;
	  }
	  	
	  
	  	@Transactional
	    public boolean addCategory(Category category) 
	    {
	        try
	        {
	        
	        Session session=sessionFactory.getCurrentSession();
			session.saveOrUpdate(category);
			return true;
	        }
	        catch(Exception e)
	        {
	        	System.out.println(e.getMessage());
	        return false;
	        }
	    }	
	  	
	  	 @Transactional
	     public List<Category> retrieveCategory() 
	     {
	         Session session=sessionFactory.openSession();
	         Query query=session.createQuery("from Category");
	         @SuppressWarnings("unchecked")
	         List<Category> listCategory=query.list();
	         session.close();
	         return listCategory;
	     }

	  	@Transactional
		public Category getCategoryById(int cid) {
			String q = "from"+" Category"+" where id=" + cid;
			Query query = sessionFactory.getCurrentSession().createQuery(q);
			
			@SuppressWarnings("unchecked")
			List<Category> listCategory = (List<Category>) query.list();
			
			if (listCategory != null && !listCategory.isEmpty()) {
				return listCategory.get(0);
			}
			
			return null;
		}

		@Transactional
		public Category removeCategoryById(int cid) {
			Category CategoryToDelete = new Category();
			CategoryToDelete.setCid(cid);
			sessionFactory.getCurrentSession().delete(CategoryToDelete);
			return CategoryToDelete;
		}
	  	
}

