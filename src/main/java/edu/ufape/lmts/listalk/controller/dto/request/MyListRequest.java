package edu.ufape.lmts.listalk.controller.dto.request;

import java.util.*;

import org.modelmapper.ModelMapper;

import java.math.*;

import edu.ufape.lmts.listalk.config.SpringApplicationContext;
import edu.ufape.lmts.listalk.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public  class MyListRequest  {
	private Long id;
	private String name;
	private String description;
	private String userId;
	private CategoryRequest category; 


	public MyList toMyList() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		MyList obj = modelMapper.map(this, MyList.class);
		return obj;
	}

}
