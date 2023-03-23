package com.hostmdy.recipe.service.Impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.domain.UnitsOfMeasurement;
import com.hostmdy.recipe.repository.IngredientRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.repository.UomRepository;
import com.hostmdy.recipe.service.IngredientService;

@Service
public class IngredientServiceImpl implements IngredientService{

	private final RecipeRepository recipeRepository;
	private final IngredientRepository ingredientRepository;
	private final UomRepository uomRepository;

	public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository,
			UomRepository uomRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
		this.uomRepository = uomRepository;
	}

	@Override
	public Ingredient createIngredient(Ingredient ingredient, Long recipeId,Long uomId) {
		// TODO Auto-generated method stub
		Optional<Recipe> recipeOpt = recipeRepository.findById(recipeId);
		
		Optional<UnitsOfMeasurement> uomOpt = uomRepository.findById(uomId);
		
		if(recipeOpt.isPresent() && uomOpt.isPresent()) {
			Recipe recipe = recipeOpt.get();
			
			if(ingredient.getId() == null)
			recipe.getIngredients().add(ingredient);
			
			ingredient.setRecipe(recipe);
			ingredient.setUom(uomOpt.get());
			return ingredientRepository.save(ingredient);
		}
		
		return null;
	}

	@Override
	public Optional<Ingredient> getIngredientId(Long id) {
		// TODO Auto-generated method stub
		return ingredientRepository.findById(id);
	}

	@Override
	public void deleteIngredientById(Long id) {
		// TODO Auto-generated method stub
		
		Optional<Ingredient> ingredientOpt = getIngredientId(id);
		
		if(ingredientOpt.isPresent()) {
			Ingredient ingredient = ingredientOpt.get();
			ingredient.setRecipe(null);
			ingredient.setUom(null);
			Ingredient updatedIngredient =ingredientRepository.save(ingredient);
			
			
			ingredientRepository.deleteById(updatedIngredient.getId());
		}else 
			System.out.println("$$$$$cannot be deletected by entity");
		
		ingredientRepository.deleteById(id);
	}

}
