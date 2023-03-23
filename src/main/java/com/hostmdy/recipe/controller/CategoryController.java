package com.hostmdy.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.CategoryService;
import com.hostmdy.recipe.service.RecipeService;

@Controller
@RequestMapping("/category")
public class CategoryController {

	private final CategoryService categoryService;
	private final RecipeService recipeService;

	public CategoryController(CategoryService categoryService, RecipeService recipeService) {
		super();
		this.categoryService = categoryService;
		this.recipeService = recipeService;
	}

	@GetMapping("/new/")
	public String categoryForm(Model model) {
		Category category = new Category();
		model.addAttribute("category", category);
		return "category/category-form";
	}
	
	@PostMapping("/new")
	public String categorySubmit(@ModelAttribute("category")Category category) {
		Category createdCategory = categoryService.createdCategory(category);	
		return "redirect:/category/show";
	}
	
	@GetMapping("/show")
	public String showCategory(Model model) {
		List<Category>  categories = categoryService.getCategories();
			model.addAttribute("categories",categories);
		
		return "category/category-show";
	}
	
	@GetMapping("/update/{id}")
	public String  updateCategory(Model model,@PathVariable Long id) {
		Optional<Category> categoryOpt = categoryService.getId(id);
		
		if(categoryOpt.isPresent()) {
		Category category = categoryOpt.get();
		model.addAttribute("category",category);
		}
		return "category/category-form";
	}
//	
//	@GetMapping("/delete/{id}")
//	public String deleteCategory(@PathVariable Long id) {
//		categoryService.deleteCategoryById(id);
//		return "redirect:/category/show";
//	}
	
	@GetMapping("delete/{id}")
	public String deleteCategory(Model model,@PathVariable Long id) {
	
		
		categoryService.deleteCategoryById(id);
		
		return "redirect:/category/show";
		
	}
		
		
	}


