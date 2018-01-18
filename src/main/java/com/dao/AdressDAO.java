package com.dao;

import com.model.Adress;

public interface AdressDAO {
	
	public boolean saveOrUpdate(Adress address);
	
	public Adress getAddressById(int addressId);
	
	public boolean deleteAddressById(int addressId);
	
	//public List<Adress> getAllAddressOfUser(int userId);
	
	public Adress getAddressOfUser(int userId);

}
