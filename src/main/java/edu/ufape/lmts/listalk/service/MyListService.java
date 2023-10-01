package edu.ufape.lmts.listalk.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ufape.lmts.listalk.repository.MyListRepository;
import edu.ufape.lmts.listalk.model.MyList;

@Service
public class MyListService implements MyListServiceInterface {
	@Autowired
	private MyListRepository repository;

	public MyListService(MyListRepository repository) {
		this.repository = repository;
	}

	public MyListService() {
	}
	
	public MyList saveMyList(MyList newInstance) {
		return repository.save(newInstance);
	}

	public MyList updateMyList(MyList transientObject) {
		return repository.save(transientObject);
	}

	public MyList findMyListById(long id) {
		return repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist MyList with id = " + id));
	}

	public List<MyList> getAllMyList(String userId){
		return repository.findByuserId(userId);
	}

	public void deleteMyList(MyList persistentObject){
		this.deleteMyList(persistentObject.getId());
		
	}
	
	public void deleteMyList(long id){
		MyList obj = repository.findById(id).orElseThrow( () -> new RuntimeException("It doesn't exist MyList with id = " + id));
		repository.delete(obj);
	}	
	
	
	
}