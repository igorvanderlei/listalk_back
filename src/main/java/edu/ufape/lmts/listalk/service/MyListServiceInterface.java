package edu.ufape.lmts.listalk.service;

import java.util.List;

import edu.ufape.lmts.listalk.model.MyList;

public interface MyListServiceInterface {
	MyList saveMyList(MyList o);
	MyList findMyListById(long id);
	MyList updateMyList(MyList u);
	void deleteMyList(MyList u);
	void deleteMyList(long id);
	List<MyList> getAllMyList(String userId);
    
    

    
}