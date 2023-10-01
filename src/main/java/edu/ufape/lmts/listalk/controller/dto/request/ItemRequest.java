package edu.ufape.lmts.listalk.controller.dto.request;

import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import edu.ufape.lmts.listalk.config.SpringApplicationContext;
import edu.ufape.lmts.listalk.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public  class ItemRequest  {
	private Long id;
	private String content;
	private MyListRequest myList; 

	public Item toItem() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		Item obj = modelMapper.map(this, Item.class);
		return obj;
	}

}
