package com.hostmdy.recipe.service.Impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService{

	private final CategoryRepository categoryRepository;
	private final RecipeRepository recipeRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository, RecipeRepository recipeRepository) {
		super();
		this.categoryRepository = categoryRepository;
		this.recipeRepository = recipeRepository;
	}

	@Override
	public List<Category> getCategories() {
		// TODO Auto-generated method stub
		return (List<Category>) categoryRepository.findAll();
	}

	@Override
	public Optional<Category> getCategoryByTitle(String title) {
		// TODO Auto-generated method stub
		return categoryRepository.findByTitle(title) ;
	}

	@Override
	public Optional<Category> getId(Long id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}

	@Override
	public Category createdCategory(Category category) {
		// TODO Auto-generated method stub
		return categoryRepository.save(category);
	}

	@Override
	public void deleteCategoryById(Long id) {
		// TODO Auto-generated method stub
		
		Category category=categoryRepository.findById(id).get();
		Set<Recipe> recipes=category.getRecipes();
		//System.out.println("All recipies of this deleted categories are: ");
		for(Recipe r: recipes) {
			
			r.getCategores().remove(category);
		}
		categoryRepository.save(category);
		
		categoryRepository.deleteById(id);
	}



}
