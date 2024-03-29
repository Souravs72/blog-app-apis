package com.sourav.blog.controllers;

import java.text.SimpleDateFormat; 
import java.util.Date;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sourav.blog.payloads.ApiResponse;
import com.sourav.blog.payloads.CategoryDTO;
import com.sourav.blog.services.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {
	
	private final CategoryService categoryService;
	
	
	//create
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/")
	public ResponseEntity<CategoryDTO> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
		CategoryDTO creaCategoryDTO = this.categoryService.createCategoryDTO(categoryDTO);
		
		return new ResponseEntity<CategoryDTO>(creaCategoryDTO, HttpStatus.CREATED);
	}
	
	//update
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> updateCategory(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable Integer categoryId) {
		CategoryDTO creaCategoryDTO = this.categoryService.updateCategoryDTO(categoryDTO, categoryId);
		
		return new ResponseEntity<CategoryDTO>(creaCategoryDTO, HttpStatus.OK);
	}
	
	
	///delete
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId) {
		this.categoryService.deleteCategoryDTO(categoryId);
		
		String pattern = "MM-dd-yyyy HH:mm:ss";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("category is deleted successfully", true, date), HttpStatus.OK);
	}
	
	
	
	//get
	@PreAuthorize("hasAnyRole('USER', 'ADMIN')")
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDTO> getCategory(@PathVariable Integer categoryId) {
		CategoryDTO categoryDTO = this.categoryService.getCategoryDTO(categoryId);
		
		return new ResponseEntity<CategoryDTO>(categoryDTO, HttpStatus.OK);
	} 
	
	
	// get all
	@PreAuthorize("hasRole('USER')")
	@GetMapping("/")
	public ResponseEntity<List<CategoryDTO>> getCategories() {
		List<CategoryDTO> categoryDTOs = this.categoryService.getCategories();
		
		return ResponseEntity.ok(categoryDTOs);
	}
}
