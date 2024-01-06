package com.sourav.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.bytebuddy.asm.Advice.This;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sourav.blog.entities.Category;
import com.sourav.blog.exceptions.ResourceNotFoundException;
import com.sourav.blog.payloads.CategoryDTO;
import com.sourav.blog.repositories.CategoryRepository;
import com.sourav.blog.services.CategoryService;


@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private ModelMapper modelMapper;
	

	@Override
	public CategoryDTO createCategoryDTO(CategoryDTO categoryDTO) {
		Category category =  this.modelMapper.map(categoryDTO, Category.class);
		Category addedCategory = this.categoryRepository.save(category);
		return this.modelMapper.map(addedCategory, CategoryDTO.class);
	}

	@Override
	public CategoryDTO updateCategoryDTO(CategoryDTO categoryDTO, Integer categoryId) {
		
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category", "Category Id", categoryId));
		category.setCategoryTitle(categoryDTO.getCategoryTitle());
		category.setCategoryDescription(categoryDTO.getCategoryDescription());
		
		Category updatedCategory = this.categoryRepository.save(category);
		
		return this.modelMapper.map(updatedCategory, CategoryDTO.class);
	}

	@Override
	public void deleteCategoryDTO(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		this.categoryRepository.delete(category);
	}

	@Override
	public CategoryDTO getCategoryDTO(Integer categoryId) {
		Category category = this.categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "Category Id", categoryId));
		
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getCategories() {
		List<Category> categories = this.categoryRepository.findAll();
		
		List<CategoryDTO> categoryDTOs =  categories.stream().map((cat) -> this.modelMapper.map(cat, CategoryDTO.class))
		.collect(Collectors.toList());
		
		return categoryDTOs;
	}

}
