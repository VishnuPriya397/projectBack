package com.dao;

import java.util.List;

import com.model.Category;

public interface CategoryDAO {

	public boolean addCategory(Category category);
	public List<Category> retrieveCategory();
	public Category getCategoryById(int cid);
	public Category removeCategoryById(int cid);
}
