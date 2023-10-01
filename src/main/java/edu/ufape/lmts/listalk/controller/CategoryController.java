package edu.ufape.lmts.listalk.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import jakarta.validation.Valid;
import edu.ufape.lmts.listalk.model.Category;
import edu.ufape.lmts.listalk.controller.dto.*;
import edu.ufape.lmts.listalk.controller.dto.request.CategoryRequest;
import edu.ufape.lmts.listalk.controller.dto.response.CategoryResponse;
import edu.ufape.lmts.listalk.facade.Facade;


@CrossOrigin (origins = "http://localhost:8081/" )
@RestController
@RequestMapping("/")
public class CategoryController {
	@Autowired
	private Facade facade;
	
	@GetMapping("category")
	public List<CategoryResponse> getAllCategory() {
		//Apenas para referência e debug
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			Jwt principal = (Jwt) authentication.getPrincipal();
			System.out.println("Email: " + principal.getClaimAsString("email"));
			System.out.println("Id (sessão): " + principal.getId());
			System.out.println("Authorities: " + authentication.getAuthorities());
			System.out.println("Credentials: " + authentication.getCredentials());
			System.out.println("Name: " + authentication.getName());
			System.out.println("Subject (id do usuário): " + principal.getSubject());
		//---------------
		
		return facade.getAllCategory()
			.stream()
			.map(CategoryResponse::new)
			.toList();
	}
	
	@PostMapping("category")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")	
	public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest newObj) {
		return new CategoryResponse(facade.saveCategory(newObj.convertToEntity()));
	}
	
	@GetMapping("category/{id}")
	public CategoryResponse getCategoryById(@PathVariable Long id) {
		try {
			return new CategoryResponse(facade.findCategoryById(id));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + id + " not found.");
		}
	}
	
	@PostMapping("category/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")	
	public CategoryResponse updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryRequest obj) {
		try {
			Category o = obj.convertToEntity();
			o.setId(id);
			return new CategoryResponse(facade.updateCategory(o));
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + id + " not found.");
		}
		
	}
	
	@DeleteMapping("category/{id}")
	@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")	
	public String deleteCategory(@PathVariable Long id) {
		try {
			facade.deleteCategory(id);
			return "";
		} catch (RuntimeException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category " + id + " not found.");
		}
		
	}
	

	
	
	
	

}
