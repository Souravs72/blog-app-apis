package com.sourav.blog.services;

import java.util.List;

import com.sourav.blog.payloads.CategoryDTO;

public interface CategoryService {
	
	//	create
	CategoryDTO createCategoryDTO(CategoryDTO categoryDTO);
	
	// update
	CategoryDTO updateCategoryDTO(CategoryDTO categoryDTO, Integer categoryId);
	
	
	//delete
	void deleteCategoryDTO(Integer categoryId);
	
	// get
	CategoryDTO getCategoryDTO(Integer categoryId);
	
	// get All
	List<CategoryDTO> getCategories();
	
}
