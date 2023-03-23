package com.hostmdy.recipe.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.h2.command.dml.MergeUsing.When;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.IngredientRepository;
import com.hostmdy.recipe.repository.RecipeRepository;

class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;
	IngredientServiceImpl ingredientService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@Mock
	CategoryRepository categoryRepository;
	
	@Mock
	IngredientRepository ingredientRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository,ingredientRepository,categoryRepository);
	}

	@Test
	void getRecipesMock() {
		
		List<Recipe> fakeRecipes = new ArrayList<>();
		Recipe recipe = new Recipe();
		Recipe recipe2 = new Recipe();
		fakeRecipes.add(recipe);
		fakeRecipes.add(recipe2);
		
	    when(recipeRepository.findAll()).thenReturn(fakeRecipes);
		
		List<Recipe> recipes = recipeService.getRecipes();
		assertEquals(2, recipes.size());
		verify(recipeRepository,times(1)).findAll();
	}

	@Test
	void getRecipeMock() {
		
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		

		 when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));
		 
		 Optional<Recipe> reicpeOpt = recipeService.getRecipe(2L);
		 
		 assertTrue(reicpeOpt.isPresent());
		 verify(recipeRepository,times(1)).findById(anyLong());
		
	}
	
}
