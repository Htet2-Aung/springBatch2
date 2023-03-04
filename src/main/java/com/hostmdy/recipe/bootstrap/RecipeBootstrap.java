package com.hostmdy.recipe.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hostmdy.recipe.domain.Category;
import com.hostmdy.recipe.domain.Difficulty;
import com.hostmdy.recipe.domain.Ingredient;
import com.hostmdy.recipe.domain.Notes;
import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.domain.UnitOfMeasurement;
import com.hostmdy.recipe.repository.CategoryRepository;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.repository.UomRepository;

@Component
public class RecipeBootstrap implements ApplicationListener<ContextRefreshedEvent>{

	private final RecipeRepository recipeRepository;
	private final CategoryRepository categoryRepository;
	private final UomRepository uomRepository;
	
	
	
	public RecipeBootstrap(RecipeRepository recipeRepository, CategoryRepository categoryRepository,
			UomRepository uomRepository) {
		super();
		this.recipeRepository = recipeRepository;
		this.categoryRepository = categoryRepository;
		this.uomRepository = uomRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		// TODO Auto-generated method stub
		recipeRepository.saveAll(getRecipes());
		
	}
	
	List<Recipe> getRecipes(){
		
		List<Recipe> recipes = new ArrayList<>();
		
		//get category optional
		Optional<Category> americanOpt = categoryRepository.findByTitle("American");
		if(americanOpt.isEmpty()) throw new RuntimeException("American category is not found.");
		
		Optional<Category> fastFoodOpt = categoryRepository.findByTitle("FastFood");
		if(americanOpt.isEmpty()) throw new RuntimeException("FastFood category is not found.");
		
		Optional<Category> italianOpt = categoryRepository.findByTitle("Italian");
		if(americanOpt.isEmpty()) throw new RuntimeException("Italian category is not found.");
		
		//Get UOM-Optional
		Optional<UnitOfMeasurement> poundOpt = uomRepository.findByUom("pound");
		if(poundOpt.isEmpty()) throw new RuntimeException("Unit pound is not found.");
		
		Optional<UnitOfMeasurement> teaSpoonOpt = uomRepository.findByUom("teaspoon");
		if(poundOpt.isEmpty()) throw new RuntimeException("Unit teaspoon is not found.");
		
		Optional<UnitOfMeasurement> tablespoonOpt = uomRepository.findByUom("tablespoon");
		if(poundOpt.isEmpty()) throw new RuntimeException("Unit tablespoon is not found.");
		
		Optional<UnitOfMeasurement> ounceOpt = uomRepository.findByUom("ounce");
		if(poundOpt.isEmpty()) throw new RuntimeException("Unit ounce is not found.");
		
		Optional<UnitOfMeasurement> cupOpt = uomRepository.findByUom("cup");
		if(cupOpt.isEmpty()) throw new RuntimeException("Unit cup is not found.");
		
		
		
		//Note-> Recipe connect
		Notes notes = new Notes();
		notes.setRecipeNote("343\r\n"
				+ "Calories\r\n"
				+ "20g\r\n"
				+ "Fat\r\n"
				+ "14g\r\n"
				+ "Carbs\r\n"
				+ "24g\r\n"
				+ "Protein");
		
		Recipe recipe = new Recipe();
		
		//one to one (two ways)
		recipe.setTitle("True CheeseBurger Pizza");
		recipe.setSource("https://www.allrecipes.com/recipes/");
		recipe.setNotes(notes);
		notes.setRecipe(recipe);
		recipe.setPrepTime(15);
		recipe.setCookTime(30);
		recipe.setDescription("These baked meatballs are tender and tasty. I also freeze these meatballs and take out how many servings I need for each meal.");
		recipe.setDirection("1.Preheat the oven to 350 degrees F (175 degrees C).\r\n"
				+ "\r\n"
				+ "2.Mix bread crumbs, Romano cheese, parsley, salt, pepper, garlic powder, and onion powder together in a large bowl; stir in water and eggs. Add ground beef and mix until well combined. Form mixture into balls and place on a nonstick baking sheet.\r\n"
				+ "\r\n"
				+ "3.Bake in the preheated oven cooked through and evenly browned, about 30 minutes.");
		recipe.setDifficulty(Difficulty.EASY);
		
		//Get Category Object
		Category american = americanOpt.get();
		System.out.println(american);
		Category fastFood = fastFoodOpt.get();
		Category italian = italianOpt.get();
		
		Set<Category> categories = new HashSet<>();
		categories.add(american);
		categories.add(fastFood);
		categories.add(italian);
		
		//category - recipe manytomany two ways
		recipe.setCategories(categories);
		american.getRecipe().add(recipe);
		fastFood.getRecipe().add(recipe);
		italian.getRecipe().add(recipe);
		
		//Get UOM Objects
		UnitOfMeasurement ounce = ounceOpt.get();
		UnitOfMeasurement pound = poundOpt.get();
		UnitOfMeasurement teaspoon = teaSpoonOpt.get();
		UnitOfMeasurement tablespoon = tablespoonOpt.get();
		UnitOfMeasurement cup = cupOpt.get();
		
		
		//Create Ingeridients Objects
		
		Ingredient beef = new Ingredient("pound ground beef chuck", new BigDecimal(3/4), recipe, pound);
	    Ingredient salt = new Ingredient("salt", new BigDecimal(1/4), recipe, teaspoon);
	    Ingredient blackPepper = new Ingredient("ground black pepper", new BigDecimal(1/4), recipe, teaspoon);
	    Ingredient ketchup = new Ingredient("ketchup", new BigDecimal(2/3), recipe, cup);
	    Ingredient yellowMustard = new Ingredient("prepared yellow mustard", new BigDecimal(2), recipe, tablespoon);
	    Ingredient pizzaCrust = new Ingredient("(14 ounce) pre-baked pizza crust", new BigDecimal(14), recipe, ounce);
	    Ingredient mozzarellaCheese = new Ingredient("shredded mozzarella cheese", new BigDecimal(3/2), recipe, cup);
	    Ingredient cheddarCheese = new Ingredient("shredded sharp Cheddar cheese", new BigDecimal(1), recipe, cup);
	    Ingredient onions = new Ingredient("chopped onions", new BigDecimal(1/2), recipe, cup);
	    Ingredient pickleSlices = new Ingredient("dill pickle slices", new BigDecimal(1/2), recipe, cup);
	    Ingredient shreddedLettuce = new Ingredient("shredded lettuce (Optional)", new BigDecimal(1), recipe, cup);
	    Ingredient dicedTomatoes  = new Ingredient("diced tomatoes (Optional)", new BigDecimal(1), recipe, cup);
		
	    Set<Ingredient> ingredients = new HashSet<>();
	    ingredients.add(beef);
	    ingredients.add(salt);
	    ingredients.add(blackPepper);
	    ingredients.add(ketchup);
	    ingredients.add(yellowMustard);
	    ingredients.add(pizzaCrust);
	    ingredients.add(mozzarellaCheese);
	    ingredients.add(cheddarCheese);
	    ingredients.add(onions);
	    ingredients.add(pickleSlices);
	    ingredients.add(shreddedLettuce);
	    ingredients.add(dicedTomatoes);
	    
	    recipe.setIngredients(ingredients);
	    
	    recipes.add(recipe);
	    
		return recipes;
	}

}
