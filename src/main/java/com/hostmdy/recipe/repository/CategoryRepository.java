package com.hostmdy.recipe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Recipe;

public interface CategoryRepository extends CrudRepository<Category, Long>{

	Optional<Category> findByTitle(String title);//query Method

	void deleteById( Long id);

	
}
