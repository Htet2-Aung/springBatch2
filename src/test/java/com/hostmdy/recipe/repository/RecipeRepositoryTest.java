package com.hostmdy.recipe.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hostmdy.recipe.domain.Recipe;

@DataJpaTest
class RecipeRepositoryTest {

	@Autowired
	RecipeRepository recipeRepository;
	
	@Test
	void findAllTest() {
		List<Recipe> recipes = (List<Recipe>) recipeRepository.findAll();
		
		assertEquals(0, recipes.size());
	}

}
