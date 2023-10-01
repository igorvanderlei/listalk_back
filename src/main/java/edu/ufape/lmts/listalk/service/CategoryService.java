package edu.ufape.lmts.listalk.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ufape.lmts.listalk.repository.CategoryRepository;
import edu.ufape.lmts.listalk.model.Category;

@Service
public class CategoryService implements CategoryServiceInterface {
	@Autowired
	CategoryRepository repository;

	public CategoryService(CategoryRepository repository) {
		this.repository = repository;
	}

	public CategoryService() {
	}
	
	public CategoryRepository getRepository() {
		return repository;
	}

	public void setDao(CategoryRepository repository) {
		this.repository = repository;
	}	

	public Category saveCategory(Category newInstance) {
		return repository.save(newInstance);
	}

	public Category updateCategory(Category transientObject) {
		return repository.save(transientObject);
	}

	public Category findCategoryById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Category with id = " + id));
	}

	public List<Category> getAllCategory(){
		return repository.findAll();
	}

	public void deleteCategory(Category persistentObject){
		this.deleteCategory(persistentObject.getId());
		
	}
	
	public void deleteCategory(long id){
		Category obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Category with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}