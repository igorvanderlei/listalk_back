package edu.ufape.lmts.listalk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import edu.ufape.lmts.listalk.model.MyList;
import edu.ufape.lmts.listalk.controller.dto.*;
import edu.ufape.lmts.listalk.controller.dto.request.MyListRequest;
import edu.ufape.lmts.listalk.controller.dto.response.MyListResponse;
import edu.ufape.lmts.listalk.facade.Facade;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/")
public class MyListController {
	@Autowired
	private Facade facade;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping("myList")
	public List<MyListResponse> getAllMyList() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt principal = (Jwt) authentication.getPrincipal();
		
		return facade.getAllMyList(principal.getSubject())
			.stream()
			.map(MyListResponse::new)
			.toList();
	}
	
	@PostMapping("myList")
	public MyListResponse createMyList(@Valid @RequestBody MyListRequest newObj) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt principal = (Jwt) authentication.getPrincipal();
		MyList o = newObj.toMyList();
		o.setUserId(principal.getSubject());
		return new MyListResponse(facade.saveMyList(o));
	}
	
	@GetMapping("myList/{id}")
	public MyListResponse getMyListById(@PathVariable Long id) {
		try {
			return new MyListResponse(facade.findMyListById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MyList " + id + " not found.");
		}
	}
	
	@PostMapping("myList/{id}")
	public MyListResponse updateMyList(@PathVariable Long id, @Valid @RequestBody MyListRequest obj) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt principal = (Jwt) authentication.getPrincipal();
			
			MyList o = obj.toMyList();
			MyList oldObject = facade.findMyListById(id);
			
			if(!principal.getSubject().equals(oldObject.getUserId())) {
				throw new AccessDeniedException("This is not your list");
			}
			
			TypeMap<MyList, MyList> typeMapper = modelMapper
													.typeMap(MyList.class, MyList.class)
													.addMappings(mapper -> mapper.skip(MyList::setId));
			
			typeMapper.map(o, oldObject);	

			return new MyListResponse(facade.updateMyList(oldObject));
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "MyList " + id + " not found.");
		}
		
	}
	
	@DeleteMapping("myList/{id}")
	public String deleteMyList(@PathVariable Long id) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt principal = (Jwt) authentication.getPrincipal();
			MyList oldObject = facade.findMyListById(id);
			if(!principal.getSubject().equals(oldObject.getUserId())) {
				throw new AccessDeniedException("This is not your list");
			}
			facade.deleteMyList(id);
			return "";
		} 
		catch (RuntimeException ex) {
			ex.printStackTrace();
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	

}
