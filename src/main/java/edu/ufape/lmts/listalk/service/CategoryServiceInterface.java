package edu.ufape.lmts.listalk.service;

import java.util.List;

import edu.ufape.lmts.listalk.model.Category;

public interface CategoryServiceInterface {
	Category saveCategory(Category o);
	Category findCategoryById(long id);
	Category updateCategory(Category u);
	void deleteCategory(Category u);
	void deleteCategory(long id);
	List<Category> getAllCategory();
    
    

    
}