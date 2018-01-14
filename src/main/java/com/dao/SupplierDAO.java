package com.dao;

import java.util.List;

import com.model.Supplier;

public interface SupplierDAO {
	
	public void insertSupplier(Supplier supplier);
	public List<Supplier> retrieve();
	public Supplier findBySuppId(int sid);
	public void updateSupp(Supplier s);
	public void deleteSupp(int sid);

}
