package edu.ufape.lmts.listalk.service;

import java.util.List;

import edu.ufape.lmts.listalk.model.Item;
import edu.ufape.lmts.listalk.model.MyList;

public interface ItemServiceInterface {
	Item saveItem(Item o);
	Item findItemById(long id);
	Item updateItem(Item u);
	void deleteItem(Item u);
	void deleteItem(long id);
	List<Item> getAllItem();
	void deleteItemMyList(Long myListId);
	List<Item> listItemMyList(Long myListId);
    
    

    
}