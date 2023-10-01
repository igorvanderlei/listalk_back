package edu.ufape.lmts.listalk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import edu.ufape.lmts.listalk.model.Item;
import edu.ufape.lmts.listalk.model.MyList;
import edu.ufape.lmts.listalk.controller.dto.*;
import edu.ufape.lmts.listalk.controller.dto.request.ItemRequest;
import edu.ufape.lmts.listalk.controller.dto.response.ItemResponse;
import edu.ufape.lmts.listalk.facade.Facade;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/myList/")
public class ItemController {
	@Autowired
	private Facade facade;
	
	@GetMapping("{myListId}/item")
	public List<ItemResponse> getAllItem(@PathVariable Long myListId) {
		return facade.listItemMyList(myListId)
			.stream()
			.map(ItemResponse::new)
			.toList();
	}
	
	@PostMapping("{myListId}/item")
	public ItemResponse createItem(@PathVariable Long myListId, @Valid @RequestBody ItemRequest newObj) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Jwt principal = (Jwt) authentication.getPrincipal();
		MyList myList = facade.findMyListById(myListId);
		if(!principal.getSubject().equals(myList.getUserId())) {
			throw new AccessDeniedException("This is not your list");
		}
		Item it = newObj.toItem();
		it.setMyList(myList);
		return new ItemResponse(facade.saveItem(it));
	}
	
	@GetMapping("{myListId}/item/{id}")
	public ItemResponse getItemById(@PathVariable Long myListId, @PathVariable Long id) {
		try {
			return new ItemResponse(facade.findItemById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item " + id + " not found.");
		}
	}
	
	@PostMapping("{myListId}/item/{id}")
	public ItemResponse updateItem(@PathVariable Long id, @Valid @RequestBody ItemRequest obj) {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt principal = (Jwt) authentication.getPrincipal();
			Item oldObj = facade.findItemById(id);
			if(! oldObj.getMyList().getUserId().equals(principal.getSubject())) {
				throw new AccessDeniedException("This is not your list");
			}
			oldObj.setContent(obj.getContent());

			return new ItemResponse(facade.updateItem(oldObj));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.CONFLICT, ex.getMessage());
		}
		
	}
	
	@DeleteMapping("{myListId}/item/{id}")
	public String deleteItem(@PathVariable Long id) {
		try {
			facade.deleteItem(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item " + id + " not found.");
		}
		
	}
	

}
