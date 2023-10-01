package edu.ufape.lmts.listalk.controller.dto.response;

import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import edu.ufape.lmts.listalk.config.SpringApplicationContext;
import edu.ufape.lmts.listalk.model.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public  class MyListResponse  {
	private Long id;
	private String name;
	private String description;
	private String userId;
	private CategoryResponse category; 

	public MyListResponse(MyList obj) {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		modelMapper.map(obj, this);
	}

	
}
