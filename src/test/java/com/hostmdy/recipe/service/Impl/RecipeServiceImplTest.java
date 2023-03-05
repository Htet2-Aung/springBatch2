package com.hostmdy.recipe.service.Impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.RecipeRepository;

class RecipeServiceImplTest {

	RecipeServiceImpl recipeService;
	
	@Mock
	RecipeRepository recipeRepository;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		recipeService = new RecipeServiceImpl(recipeRepository);
	}

	@Test
	void getRecipesMock() {
		
		List<Recipe> fakeRecipes = new ArrayList<>();
		Recipe recipe1 = new Recipe();
		Recipe recipe2 = new Recipe();
		fakeRecipes.add(recipe1);
		fakeRecipes.add(recipe2);
		
		
		when(recipeRepository.findAll()).thenReturn(fakeRecipes);
		
		List<Recipe> recipes = recipeService.getRecipes();
		
		assertEquals(2, recipes.size());
		verify(recipeRepository,times(2)).findAll();
	}

}
