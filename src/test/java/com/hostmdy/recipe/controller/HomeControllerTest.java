package com.hostmdy.recipe.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyIterable;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import com.hostmdy.recipe.controller.HomeController;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.service.RecipeService;

class HomeControllerTest {

	HomeController homeController;
	
	@Mock
	RecipeService recipeService;
	
	@Mock
	Model model;
	
	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		homeController = new HomeController(recipeService);	
		
	}

	@Test
	void indexMockMVC() throws Exception {
		MockMvc mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
		
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())//200 ok
		.andExpect(view().name("index"));//template name -> index.html
	}
	
	@Test
	void indexMock() {
		List<Recipe> fakeRecipes =new ArrayList<>();
		
		Recipe recipe1 = new Recipe();
		recipe1.setId(1L);
		
		Recipe recipe2 = new Recipe();
		recipe2.setId(2L);
		
		fakeRecipes.add(recipe1);
		fakeRecipes.add(recipe2);
		
		when(recipeService.getRecipes()).thenReturn(fakeRecipes);
		
		List<Recipe> recipes = recipeService.getRecipes();
		
		String indexPage = homeController.index(model);
		
		assertEquals(2, recipes.size());
		assertEquals("index", indexPage);
		verify(recipeService,times(2)).getRecipes();
		
		ArgumentCaptor<List> argCaptor = ArgumentCaptor.forClass(List.class);
		//verify(model, times(1)).addAttribute(eq("recipes"),anyIterable());
		verify(model, times(1)).addAttribute(eq("recipes"),argCaptor.capture());
		
		List<Recipe> captorRecipes = argCaptor.getValue();
		
		assertEquals(2, captorRecipes.size());
		
	}

}
