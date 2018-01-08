package com.dao;

import java.util.List;

import com.model.Supplier;

public interface SupplierDAO {
	

	public boolean addSupplier(Supplier supplier);
	public List<Supplier> retrieveSupplier();
	public Supplier getSupplierById(int sid);
	public Supplier removeSupplierById(int sid);


}
