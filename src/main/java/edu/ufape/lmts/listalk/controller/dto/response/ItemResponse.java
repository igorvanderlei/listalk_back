package edu.ufape.lmts.listalk.controller.dto.response;

import java.util.*;
import java.math.*;

import org.modelmapper.ModelMapper;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import edu.ufape.lmts.listalk.config.SpringApplicationContext;
import edu.ufape.lmts.listalk.model.*;

@Getter @Setter @NoArgsConstructor
public  class ItemResponse  {
	private Long Id;
	private String content;

	public ItemResponse(Item obj) {
		ModelMapper modelMapper = (ModelMapper) SpringApplicationContext.getBean("modelMapper");
		modelMapper.map(obj, this);
	}
}
