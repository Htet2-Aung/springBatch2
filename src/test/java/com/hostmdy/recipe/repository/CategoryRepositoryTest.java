package com.hostmdy.recipe.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.hostmdy.recipe.domain.Category;

@DataJpaTest
class CategoryRepositoryTest {

	@Autowired
	CategoryRepository categoryRepository;
	
	
	@Test
	void diTest() {
		assertNotNull(categoryRepository,"category repository is not null.");
	}
	
	@Test
	void findByAmericanTitleTest() {
		String testTitle = "American";
		Optional<Category> category = categoryRepository.findByTitle(testTitle);
		
		if(category.isPresent())
			assertEquals(testTitle, category.get().getTitle());
		else
			fail("category with "+testTitle+" is not found.");
	}
	
	@Test
	void findByItalianTitleTest() {
		String testTitle = "Italian";
		Optional<Category> category = categoryRepository.findByTitle(testTitle);
		
		if(category.isPresent())
			assertEquals(testTitle, category.get().getTitle());
		else
			fail("category with "+testTitle+" is not found.");
	}
	
	@Test
	void findById() {
		Optional<Category> category = categoryRepository.findById(3L);
		
		assertTrue(category.isPresent());
	}
	
	
}
