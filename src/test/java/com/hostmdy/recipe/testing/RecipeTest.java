package com.hostmdy.recipe.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.hostmdy.recipe.domain.Recipe;

class RecipeTest {
	Recipe recipe;

	@BeforeEach
	void setUp() throws Exception {
		recipe=new Recipe();
	}

	 
	  @Test
	  void setTtile() {
	    recipe.setTitle("Beef Burger");
	    
	    assertEquals("Beef Burger", recipe.getTitle());
	  }
	  
	  @Test
	  void setBlankTitle() {
	    recipe.setTitle(" ");
	    
	    assertNull(recipe.getTitle());
	  }

}

