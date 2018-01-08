package com.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.dao.CategoryDAO;
import com.dao.ProductDAO;
import com.dao.SupplierDAO;
import com.dao.UserDAO;
import com.daoimpl.CategoryDAOImpl;
import com.daoimpl.ProductDAOImpl;
import com.daoimpl.SupplierDAOImpl;
import com.daoimpl.UserDAOImpl;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class DBconfig 
{
	 @Bean(name = "dataSource")
		public DataSource getDataSource() 
	 {
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.h2.Driver");
			dataSource.setUrl("jdbc:h2:tcp://localhost/~/project");
			dataSource.setUsername("sa");
			dataSource.setPassword("");
			System.out.println("Datasource");
			return dataSource;

		}

		private Properties getHibernateProperties() 
		{
			Properties properties = new Properties();
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			//properties.put("hibernate.hbm2ddl.auto", "create");
		    properties.put("hibernate.hbm2ddl.auto", "update");
			System.out.println("Hibernate Properties");
			return properties;

		}

		@Autowired
		@Bean(name = "sessionFactory")
		public SessionFactory getSessionFactory(DataSource dataSource)
		{
			LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
			sessionBuilder.addProperties(getHibernateProperties());
			sessionBuilder.scanPackages("com");
			System.out.println("Session");
			
			return sessionBuilder.buildSessionFactory();
			
		}

		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory)
		{
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			System.out.println("Transaction");
			return transactionManager;
		}
		
	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory)
	{

		return new UserDAOImpl(sessionFactory);
	}	
	
	@Autowired
	@Bean(name="supplierDAO")
	public SupplierDAO getSupplier(SessionFactory sessionFactory)
	{
		return new SupplierDAOImpl(sessionFactory);
		
	}
	
	@Autowired
	@Bean(name="categoryDAO")
	public CategoryDAO getCategory(SessionFactory sessionFactory)
	{
		return new CategoryDAOImpl(sessionFactory);
		
	}
	
	@Autowired
	@Bean(name="productDAO")
	public ProductDAO getProduct(SessionFactory sessionFactory)
	{
		return new ProductDAOImpl(sessionFactory);
		
	}
}




	
