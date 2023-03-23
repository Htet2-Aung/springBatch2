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
import org.springframework.web.bind.annotation.RequestParam;

import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.UnitsOfMeasurement;
import com.hostmdy.recipe.service.IngredientService;
import com.hostmdy.recipe.service.UomService;

@Controller
@RequestMapping("/ingredient")
public class IngredientController {

	private final IngredientService ingredientService;
	private final UomService uomService;
	
	public IngredientController(IngredientService ingredientService, UomService uomService) {
		super();
		this.ingredientService = ingredientService;
		this.uomService = uomService;
	}

	@GetMapping("/new/{recipeId}")
	public String ingredientForm(@PathVariable Long recipeId,Model model) {
		System.out.println("RecipeId :"+recipeId);
		Ingredient ingredient = new Ingredient();
		
		model.addAttribute("ingredient",ingredient);
		model.addAttribute("recipeId",recipeId);
		
		List<UnitsOfMeasurement> uomList = uomService.getAllUom();
		model.addAttribute("uomList",uomList);
		
		return "ingredient/ingredient-form";
	}
	
	@PostMapping("/new")
	public String ingredientSubmit(@ModelAttribute("ingredient") Ingredient ingredient,@RequestParam Long recipeId,@RequestParam Long uomId) {
		Ingredient createdIngredient =	ingredientService.createIngredient(ingredient, recipeId, uomId);
		
		if(createdIngredient ==null)
			throw new NullPointerException("created Ingredient is null!");
		
		return "redirect:/recipe/" + recipeId + "/ingredients";
	}
	
	@GetMapping("/update/{id}")
	public String updateIngredient(@PathVariable Long id,Model model) {
		Optional<Ingredient> ingredientOpt = ingredientService.getIngredientId(id);
		
		
		if(ingredientOpt.isPresent()) {
			Ingredient ingredient = ingredientOpt.get();
			model.addAttribute("ingredient", ingredient);
			model.addAttribute("recipeId",ingredient.getRecipe().getId());
		}
		
		List<UnitsOfMeasurement> uomList = uomService.getAllUom();
		model.addAttribute("uomList",uomList);
		
		return "ingredient/ingredient-form";
		
	}
	
	@GetMapping("delete/{recipeId}/{id}")
	public String deleteIngredient(Model model,@PathVariable Long recipeId,@PathVariable Long id) {
		
		ingredientService.deleteIngredientById(id);
		return "redirect:/recipe/" + recipeId + "/ingredients";
		
		
	}
	
	
}
