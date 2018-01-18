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

import com.dao.*;
import com.daoimpl.*;
import com.model.*;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class DBconfig {
	
	@Autowired
	@Bean(name="datasource")
public DataSource getH2Data()
{
	DriverManagerDataSource dsource=new DriverManagerDataSource();
	dsource.setDriverClassName("org.h2.Driver");
	dsource.setUrl("jdbc:h2:tcp://localhost/~/project");
	dsource.setUsername("sa");
	dsource.setPassword("");
	System.out.println("H2 connected");
	return dsource;
 }
private Properties getHiber()
{
	Properties p= new Properties();
	p.put("hibernate.dialect","org.hibernate.dialect.H2Dialect");
	p.put("hibernate.hbm2ddl.auto","update");
	p.put("hibernate.show_sql","true");
	System.out.println("Hibernate Config");
	return p;
}
    @Autowired
    @Bean(name="sessionFactory")
    public SessionFactory getHibersession(DataSource datasource)
    {
    	LocalSessionFactoryBuilder lsfb= new LocalSessionFactoryBuilder(datasource);
    	lsfb.addProperties(getHiber());
    	lsfb.addAnnotatedClass(User.class);
    	lsfb.addAnnotatedClass(Supplier.class);
    	lsfb.addAnnotatedClass(Category.class);
    	lsfb.addAnnotatedClass(Product.class);
    	lsfb.addAnnotatedClass(Cart.class);
    	lsfb.addAnnotatedClass(Orders.class);
    	lsfb.addAnnotatedClass(Payment.class);
    	lsfb.addAnnotatedClass(Adress.class);
    	return lsfb.buildSessionFactory();
    }
    
    
    @Autowired
    @Bean(name="transactionManager")
    public HibernateTransactionManager getTrans(SessionFactory sessionFactory)
    {
    HibernateTransactionManager tm= new HibernateTransactionManager(sessionFactory);
    return tm;
    }   
   
    
    @Autowired
    @Bean(name="userDAO")
    public UserDAO saveUser(SessionFactory sessionFactory)
    {
    	return new UserDAOImpl(sessionFactory);
    }
    
   @Autowired
    @Bean(name="supplierDAO")
    public SupplierDAO  saveSuppData(SessionFactory sessionFactory)
    {
    	return new SupplierDAOImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name="categoryDAO")
    public CategoryDAO saveCatData(SessionFactory sessionFactory)
    {
    	return new CategoryDAOImpl(sessionFactory);
    }
 
    @Autowired
    @Bean(name="productDAO")
    public ProductDAO saveprod(SessionFactory sessionFactory)
    {
    	return new ProductDAOImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name="cartDAO")
    public CartDAO saveCart(SessionFactory sessionFactory)
    {
    	return new CartDAOImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name="ordersDAO")
    public OrdersDAO saveOrders(SessionFactory sessionFactory)
    {
    	return new OrdersDAOImpl(sessionFactory);
    }

    @Autowired
    @Bean(name="paymentDAO")
    public PaymentDAO savePayment(SessionFactory sessionFactory)
    {
    	return new PaymentDAOImpl(sessionFactory);
    }

    @Autowired
    @Bean(name="adressDAO")
    public AdressDAO saveAdress(SessionFactory sessionFactory)
    {
    	return new AdressDAOImpl(sessionFactory);
    }

}
