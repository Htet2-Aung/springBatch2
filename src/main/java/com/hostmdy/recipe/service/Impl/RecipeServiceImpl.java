package com.hostmdy.recipe.service.Impl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.IngredientRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.RecipeService;

@Service
public class RecipeServiceImpl implements RecipeService{

	private final RecipeRepository recipeRepository;
	private final IngredientRepository ingredientRepository ;
	private final CategoryRepository categoryRepository;
	

	public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
			CategoryRepository categoryRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
		this.categoryRepository = categoryRepository;
	}


	@Override
	public List<Recipe> getRecipes() {
		// TODO Auto-generated method stub
		return (List<Recipe>) recipeRepository.findAll();
	}


	@Override
	public Optional<Recipe> getRecipe(Long id) {
		// TODO Auto-generated method stub
		return recipeRepository.findById(id);
	}


	//Create
	@Override
	public Recipe createRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return recipeRepository.save(recipe);
	}


	@Override
	public void deleteRecipeById(Long id) {
		// TODO Auto-generated method stub
		recipeRepository.deleteById(id);
	}


	@Override
	public List<Ingredient> getIngredientsByRecipe(Long recipeId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOpt = getRecipe(recipeId);
		
		if(recipeOpt.isPresent()) 
			return ingredientRepository.findByRecipeOrderById(recipeOpt.get());
		
		else
		return Collections.emptyList();
	}

	

}
