package com.daoimpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AdressDAO;
import com.model.Adress;

@Repository
public class AdressDAOImpl implements AdressDAO{
	
	public AdressDAOImpl(){}

	@Autowired
	private SessionFactory sessionFactory;	
	
	public AdressDAOImpl(SessionFactory sessionFactory) {
		
		//log.info("AddressDaoImpl : getSessionFactory");
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean deleteAddressById(int addressId) {
		//log.info("AddressDaoImpl : get Session Factory");
		Session session = sessionFactory.getCurrentSession();
		//log.info("AddressDaoImpl : load Address detail by Id --"+addressId);
		Object persistenceInstance = session.load(Adress.class, addressId);
		
		if(persistenceInstance !=null){
			//log.info("AddressDaoImpl : delete Address detail by Id --"+addressId);
			session.delete(persistenceInstance);
			//log.info("AddressDaoImpl : Address deleted Successfully --"+addressId);
			System.out.println("Address deleted Successfully");
		}
		
		return true;
	}

	/*@Transactional
	public List<Adress> getAllAddressOfUser(int userId) {
		//log.info("AddressDaoImpl : get Session Factory");
		Session session = sessionFactory.getCurrentSession();
		//log.info("AddressDaoImpl : load all Address details of given user Id --"+userId);
		CriteriaBuilder cb =  ((Object) session).getCriteriaBuilder();
		CriteriaQuery<Adress> cq = cb.createQuery(Adress.class);
		
		Root<Adress> addressRoot  = cq.from(Adress.class);
		ParameterExpression<Integer> personId = cb.parameter(Integer.class);
		
		cq.select(addressRoot).where(cb.equal(addressRoot.get("personId"), userId));
		
		Query query = session.createQuery(cq);
		
		//log.info("AddressDaoImpl : criteria query of Address details of given user Id --"+query.toString());
		@SuppressWarnings(value="unchecked")
		List<Adress> results = (List<Adress>) query.getResultList();
		//log.info("AddressDaoImpl : Successful retrieval of Address details of given user Id --"+userId);
		return results;
	}
*/	

	@Transactional
	public boolean saveOrUpdate(Adress address) {
		//log.info("AddressDaoImpl : save or update Address detail");
		sessionFactory.getCurrentSession().saveOrUpdate(address);
		//log.info("AddressDaoImpl : Successful save or update Address detail");
		return true;
	}
	

	@Transactional
	public Adress getAddressById(int addressId) {
		//log.info("AddressDaoImpl : get Address detail by Id --"+addressId);
		Adress address =  (Adress) sessionFactory.getCurrentSession().createQuery("select a from Address a where a.id = :addressId").setParameter("addressId", addressId).uniqueResult();
		//log.info("AddressDaoImpl : Address detail by Id --"+address.toString());
		return address;
	}


	@Transactional
	public Adress getAddressOfUser(int userId) {
		Session session = sessionFactory.openSession();
		Adress address = (Adress) session.createQuery("from Address where personId = :userId").setParameter("userId", userId).uniqueResult();
		session.close();
		return address;
	}

}
