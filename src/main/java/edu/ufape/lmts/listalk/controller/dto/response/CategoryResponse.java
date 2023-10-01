package edu.ufape.lmts.listalk.controller.dto.response;

import edu.ufape.lmts.listalk.config.SpringApplicationContext;
import edu.ufape.lmts.listalk.model.*;

import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public  class CategoryResponse  {
	private String name;
	private Long id;

	public CategoryResponse(Category obj) {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		modelMapper.map(obj, this);
	}


}
