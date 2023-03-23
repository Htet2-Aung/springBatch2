package com.hostmdy.recipe.service;

import java.util.List;
import java.util.Optional;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Recipe;

public interface RecipeService {
	
	List<Recipe> getRecipes();//findAll
	
	Optional<Recipe> getRecipe(Long id);//find by id
	
	Recipe createRecipe(Recipe recipe);//Create
	
	void deleteRecipeById(Long id);
	
	List<Ingredient> getIngredientsByRecipe(Long recipeId);
	
	
	}


