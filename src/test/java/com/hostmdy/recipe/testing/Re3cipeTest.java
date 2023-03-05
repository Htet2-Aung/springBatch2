package com.hostmdy.recipe.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hostmdy.recipe.domain.Recipe;

class Re3cipeTest {

	Recipe recipe;
	
	@BeforeEach
	void setUp() throws Exception {
		recipe = new Recipe();
	}
	
	@Test
	void setTitle() {
		recipe.setTitle("Beef Burger");
		
		assertEquals("Beef Burger", recipe.getTitle());
	}

	@Test
	void setBlsnkTitle() {
		recipe.setTitle("");
		assertNull(recipe.getTitle());
	}

}
