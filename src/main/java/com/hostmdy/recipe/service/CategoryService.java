package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.recipe.domain.Category;

public interface CategoryService  {
	
	List<Category> getCategories();
	
	Optional<Category> getId(Long id);
	
	Optional<Category> getCategoryByTitle(String title);

	Category createdCategory(Category category);

	void deleteCategoryById(Long id);
}
