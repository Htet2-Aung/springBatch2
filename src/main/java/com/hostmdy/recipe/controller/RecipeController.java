package com.hostmdy.recipe.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.service.CategoryService;
import com.hostmdy.recipe.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {
	
	private final RecipeService recipeService;
	private final CategoryService categoryService;
	
	public RecipeController(RecipeService recipeService, CategoryService categoryService) {
		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
	}

	@GetMapping("/show/{id}")
	public String showRecipe(@PathVariable Long id, Model model) {
		Optional<Recipe>  recipeOpt = recipeService.getRecipe(id);
		
		if (recipeOpt.isPresent())
			model.addAttribute("recipe",recipeOpt.get());
		
		return "recipe/recipe-details";
	}
	
	@GetMapping("/new")
	public String recipeForm(Model model) {
		
		List<Category> categories = categoryService.getCategories();
		
		Recipe recipe = new Recipe();
		
		model.addAttribute("recipe",recipe);
		model.addAttribute("categories",categories);
		
		return "recipe/add-recipe";
	}
	
	@PostMapping("/new")
	public String recipeSubmit(@ModelAttribute("recipe")Recipe recipe,@RequestParam String[] categoryTitle) {
	
		for(final String title : categoryTitle) {
			recipe.getCategories().add(categoryService.getCategoryByTitle(title).get());
		}
		
		Recipe createdRecipe = recipeService.createRecipe(recipe);
		
		return "redirect:/index";
	}
	
	@GetMapping("/update/{id}")
	public String  updateRecipe(Model model,@PathVariable Long id) {
		Recipe recipe = recipeService.getRecipe(id).get();
		List<Category> categories = categoryService.getCategories();
		
		model.addAttribute("recipe", recipe);
		model.addAttribute("categories",categories);
		
		return "recipe/add-recipe";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteRecipe(@PathVariable Long id) {
		recipeService.deleteRecipeById(id);
		return "redirect:/";
	}
	
	@GetMapping("/{id}/ingredients")
	public String showIngredients(Model model,@PathVariable Long id) {
		List<Ingredient> ingredients = recipeService.getIngredientsByRecipe(id);
		model.addAttribute("ingredients",ingredients);
		model.addAttribute("recipeId",id);
		
		return "ingredient/show";
		
	}
	
}
