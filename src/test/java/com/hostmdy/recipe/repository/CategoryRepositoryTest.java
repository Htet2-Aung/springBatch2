package com.hostmdy.recipe.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.repository.CategoryRepository;


//@ContextConfiguration
//@SpringBootTest
@DataJpaTest
class CategoryRepositoryTest {

	@Autowired
	CategoryRepository categoryRepository;

	@Test
	void diTest() {
		assertNotNull(categoryRepository, "Category repository is null");
	}

	@Test
	void findByTitleTest() {
		String testTitle = "Asian";
		Optional<Category> category = categoryRepository.findByTitle(testTitle);

		if (category.isPresent())
			assertEquals(testTitle, category.get().getTitle());
		else
			fail("Catregory with" + testTitle + "is not found");
	}

	@Test
	void findByItilianTitleTest() {
		String testTitle = "Asian";
		Optional<Category> category = categoryRepository.findByTitle(testTitle);

		if (category.isPresent())
			assertEquals(testTitle, category.get().getTitle());
		else
			fail("Catregory with" + testTitle + "is not found");
	}
	
	@Test
	void findById1Test() {
		
		Optional<Category> category = categoryRepository.findById(1L);
		assertTrue(category.isPresent());
	}

}