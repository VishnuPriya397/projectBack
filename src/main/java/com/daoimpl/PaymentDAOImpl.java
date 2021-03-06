package com.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PaymentDAO;
import com.model.Payment;

@Repository
public class PaymentDAOImpl implements PaymentDAO {

	public PaymentDAOImpl(){}
	
	private static Logger log = LoggerFactory.getLogger(PaymentDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory ;
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}
	
	public PaymentDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Payment> getUserPaymentInfo(int userId) {
		log.info("PaymentDaoImpl : get Payment detail by User Id");
		List<Payment> paymentList=  sessionFactory.getCurrentSession().createQuery("from Payment where userId = :userId").setParameter("userId", userId).list();
		return paymentList;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<Payment> getUserCardPaymentInfo(int userId) {
		log.info("PaymentDaoImpl : get User Card Payment detail by User Id");
		List<Payment> paymentList=  sessionFactory.getCurrentSession().createQuery("from Payment where userId = :userId and paymentMethod IN ('creditcard','debitcard')").setParameter("userId", userId).list();
		return paymentList;
	}
	@Transactional
	public boolean savePaymentInfo(Payment payment) {
		try{
			log.info("PaymentDaoImpl : save or update Payment detail");
			sessionFactory.getCurrentSession().saveOrUpdate(payment);
			return true;
			}catch (Exception e) {
				return false;
			}
	}
	
	
	@Transactional
	public Payment getPaymentInfo(int paymentId) {
		log.info("PaymentDaoImpl : get Payment detail by payment Id");
		Payment payment= (Payment) sessionFactory.getCurrentSession().createQuery("from Payment where id = :Id")
				.setParameter("Id", paymentId).uniqueResult();
		
		log.info("PaymentDaoImpl : Fetched Payment detail by Id => "+payment.toString());
		return payment;
	}
	
}
