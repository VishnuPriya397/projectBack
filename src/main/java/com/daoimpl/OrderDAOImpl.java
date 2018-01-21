package com.daoimpl;



import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.OrderDAO;
import com.model.Order;

@Repository
	public class OrderDAOImpl implements OrderDAO {

	public OrderDAOImpl(){}
	private static Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);

	@Autowired
		private SessionFactory sessionFactory;
		
		
		
	public OrderDAOImpl(SessionFactory sessionFactory) {
			
			log.info("AddressDaoImpl : getSessionFactory");
			this.sessionFactory = sessionFactory;
		}


		

		@Transactional
		public boolean deleteOrderById(int orderId) {
			try{
				
				Session session = sessionFactory.getCurrentSession();
				
				Object object = session.load(Order.class, orderId);
				
				if(object!=null){
					
					session.delete(object);
					log.info("OrderDaoImpl : Order Object deleted Successfully");				
					
				}
				return true;
			}catch(Exception e){
				return false;
			}
		}
		

		@Transactional
		public List<Order> getAllOrdersOfUser(int userId) {
			
			List<Order> orderList =  sessionFactory.getCurrentSession().createQuery("from Order where userId = :userId and orderStatus = 'PROCESSED'").setParameter("userId", userId).list();
			return orderList;
		}

		@Transactional

		public boolean saveOrUpdate(Order order) {
			// TODO Auto-generated method stub
			try{
				sessionFactory.getCurrentSession().saveOrUpdate(order);
				return true;
			}catch (Exception e) {
				return false;
			}
			
		}

		@Transactional
		
		public Order getOrderById(int orderId) {
			// TODO Auto-generated method stub
			Order order= (Order) sessionFactory.getCurrentSession().createQuery("from Order where id = :orderId")
					.setParameter("orderId", orderId).uniqueResult();
			return order;
		
		}

		
	}

