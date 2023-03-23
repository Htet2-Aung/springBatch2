package com.hostmdy.recipe.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.service.CategoryService;
import com.hostmdy.recipe.service.RecipeService;

class RecipeControllerTest {

	@Mock
	RecipeController recipeController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	CategoryService categoryService;
	
	@Mock
	Model model;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		recipeController = new RecipeController(recipeService, categoryService);
	}

	@Test
	void showReciopeMockMVC() throws Exception {
		Recipe recipe = new Recipe();
		recipe.setId(1L);
		Optional<Recipe> fakeRecipeOpt = Optional.of(recipe);

		when(recipeService.getRecipe(anyLong())).thenReturn(fakeRecipeOpt);

		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(recipeController).build();

		mockMvc.perform(get("/recipe/show/1")).andExpect(status().isOk()).andExpect(view().name("recipe/recipe-details"))
				.andReturn();
		
	}

}
