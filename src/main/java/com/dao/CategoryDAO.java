package com.dao;

import java.util.List;

import com.model.Category;

public interface CategoryDAO {

	public void insertCategory(Category category);
	public List<Category> retrieve();
	public Category findByCatId(int cid);
	public void updateCat(Category c);
	public void deleteCat(int cid);

}
