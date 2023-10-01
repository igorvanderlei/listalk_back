package edu.ufape.lmts.listalk.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.ufape.lmts.listalk.model.*;
import edu.ufape.lmts.listalk.service.*;

@Service
public class Facade {
	//Item--------------------------------------------------------------
	@Autowired
	private ItemServiceInterface  itemService;
		
	public Item saveItem(Item newInstance) {
		return itemService.saveItem(newInstance);
	}

	public Item updateItem(Item transientObject) {
		return itemService.updateItem(transientObject);
	}

	public Item findItemById(long id) {
		return itemService.findItemById(id);
	}

	public List<Item> getAllItem() {
		return itemService.getAllItem();
	}

	public void deleteItem(Item persistentObject) {
		itemService.deleteItem(persistentObject);
	}

	public void deleteItem(long id) {
		itemService.deleteItem(id);
	}
	
	

	public void deleteItemMyList(Long myListId) {
		itemService.deleteItemMyList(myListId);
	}

	public List<Item> listItemMyList(Long myListId) {
		return itemService.listItemMyList(myListId);
	}



	//Category--------------------------------------------------------------
	@Autowired
	private CategoryService  categoryService;
		
	public Category saveCategory(Category newInstance) {
		return categoryService.saveCategory(newInstance);
	}

	public Category updateCategory(Category transientObject) {
		return categoryService.updateCategory(transientObject);
	}

	public Category findCategoryById(long id) {
		return categoryService.findCategoryById(id);
	}

	public List<Category> getAllCategory() {
		return categoryService.getAllCategory();
	}

	public void deleteCategory(Category persistentObject) {
		categoryService.deleteCategory(persistentObject);
	}

	public void deleteCategory(long id) {
		categoryService.deleteCategory(id);
	}
	

	//MyList--------------------------------------------------------------
	@Autowired
	private MyListService  myListService;
		
	public MyList saveMyList(MyList newInstance) {
		return myListService.saveMyList(newInstance);
	}

	public MyList updateMyList(MyList transientObject) {
		return myListService.updateMyList(transientObject);
	}

	public MyList findMyListById(long id) {
		return myListService.findMyListById(id);
	}

	public List<MyList> getAllMyList(String userId) {
		return myListService.getAllMyList(userId);
	}

	public void deleteMyList(MyList persistentObject) {
		myListService.deleteMyList(persistentObject.getId());
	}

	public void deleteMyList(Long myListId) {
		deleteItemMyList(myListId);
		myListService.deleteMyList(myListId);
	}
	

}