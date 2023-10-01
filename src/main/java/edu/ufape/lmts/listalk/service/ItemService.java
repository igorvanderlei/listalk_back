package edu.ufape.lmts.listalk.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ufape.lmts.listalk.repository.ItemRepository;
import edu.ufape.lmts.listalk.model.Item;
import edu.ufape.lmts.listalk.model.MyList;

@Service
public class ItemService implements ItemServiceInterface {
	@Autowired
	ItemRepository repository;

	public ItemService(ItemRepository repository) {
		this.repository = repository;
	}

	public ItemService() {
	}
	
	public ItemRepository getRepository() {
		return repository;
	}

	public void setDao(ItemRepository repository) {
		this.repository = repository;
	}	

	public Item saveItem(Item newInstance) {
		return repository.save(newInstance);
	}

	public Item updateItem(Item transientObject) {
		return repository.save(transientObject);
	}

	public Item findItemById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Item with id = " + id));
	}

	public List<Item> getAllItem(){
		return repository.findAll();
	}

	public void deleteItem(Item persistentObject){
		this.deleteItem(persistentObject.getId());
		
	}
	
	public void deleteItem(long id){
		Item obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist Item with id = " + id));
		repository.delete(obj);
	}	
	
	@Override
	public List<Item> listItemMyList(Long myListId) {
		return repository.findByMyList_id(myListId);
	}
	
	@Override
	public void deleteItemMyList(Long myListId) {
		List<Item> listItem = repository.findByMyList_id(myListId);
		repository.deleteAll(listItem);
	}
	
	
	
}