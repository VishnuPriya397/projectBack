package com.dao;

import java.util.List;

import com.model.Product;

public interface ProductDAO {

	public boolean saveProduct(Product product);
	public List<Product> retrieveProduct();
	public Product getProductById(int id);
	public void updateProduct(Product p);
	public void deleteProduct(int pid);
}
