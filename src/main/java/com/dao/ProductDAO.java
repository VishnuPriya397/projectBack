package com.dao;

import java.util.List;

import com.model.Product;

public interface ProductDAO {

	public void insertProduct(Product product);
	public List<Product> retrieve();
	public Product findByPID(int id);
	public List <Product> getProdByCatId(int cid);
	public void deleteProd(int pid);
	public void update(Product p);
	public List<Product> getProductByCategoryID(int cid);
	
}
