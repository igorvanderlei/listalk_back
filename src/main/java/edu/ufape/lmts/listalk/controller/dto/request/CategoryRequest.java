package edu.ufape.lmts.listalk.controller.dto.request;

import edu.ufape.lmts.listalk.config.SpringApplicationContext;
import edu.ufape.lmts.listalk.model.*;

import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor 
public  class CategoryRequest  {
	@Size(min = 4, max=128, message="name must have between 4 and 128 characteres")
	private String name;
	private Long id;
	
	public Category convertToEntity() {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		Category obj = modelMapper.map(this, Category.class);
		return obj;
	}

}
