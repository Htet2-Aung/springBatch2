package com.hostmdy.recipe.service;

import java.util.Optional;

import com.hostmdy.recipe.domain.Ingredient;

public interface IngredientService {

	Ingredient createIngredient(Ingredient ingredient,Long recipeId,Long uomId);
	
	Optional<Ingredient> getIngredientId(Long id);
	
	void deleteIngredientById(Long id);
	
}
