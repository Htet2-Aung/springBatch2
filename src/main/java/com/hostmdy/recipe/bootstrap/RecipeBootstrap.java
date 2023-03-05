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
		Notes note1 = new Notes();
		note1.setRecipeNote("343\r\n"
				+ "Calories\r\n"
				+ "20g\r\n"
				+ "Fat\r\n"
				+ "14g\r\n"
				+ "Carbs\r\n"
				+ "24g\r\n"
				+ "Protein");
		 
		Notes note2 = new Notes();
		note2.setRecipeNote("I used four 6-ounce cube steaks for this recipe.\r\n"
				+ "\r\n"
				+ "Worcestershire sauce is quite salty. Be sure to allow for that, when adding salt or seasoned salt.");
		
		Recipe recipe1 = new Recipe();
		
		//one to one (two ways)
		recipe1.setTitle("True CheeseBurger Pizza");
		recipe1.setSource("https://www.allrecipes.com/recipes/");
		recipe1.setNotes(note1);
		note1.setRecipe(recipe1);
		recipe1.setPrepTime(15);
		recipe1.setCookTime(30);
		recipe1.setDescription("These baked meatballs are tender and tasty. I also freeze these meatballs and take out how many servings I need for each meal.");
		recipe1.setDirection("1.Preheat the oven to 350 degrees F (175 degrees C).\r\n"
				+ "\r\n"
				+ "2.Mix bread crumbs, Romano cheese, parsley, salt, pepper, garlic powder, and onion powder together in a large bowl; stir in water and eggs. Add ground beef and mix until well combined. Form mixture into balls and place on a nonstick baking sheet.\r\n"
				+ "\r\n"
				+ "3.Bake in the preheated oven cooked through and evenly browned, about 30 minutes.");
		recipe1.setDifficulty(Difficulty.EASY);
		
		
		Recipe recipe2 = new Recipe();
		
		recipe2.setTitle("Pan-Fried Cube Steaks with Simple Pan Sauce");
		recipe2.setSource("https://www.allrecipes.com");
		recipe2.setNotes(note2);
		note2.setRecipe(recipe2);
		recipe2.setPrepTime(5);
		recipe2.setCookTime(10);
		recipe2.setDescription("Cube steak is a cut of meat from either the top round or the bottom round, that is run through the store's mechanical tenderizer. It is usually sold in individual portions, from 4-ounce up to 8-ounces each. Sometimes the cut is called minute steak, perhaps because it takes only minutes to prepare. I used to watch my Mom prepare these for dinner, and it's kind of a challenge to write down what I've always done without a recipe, but here it goes! You may use garlic salt, or your favorite seasoning salt, to replace the regular salt and pepper.");
		recipe2.setDirection("");
		
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
		recipe1.setCategories(categories);
		american.getRecipe().add(recipe1);
		fastFood.getRecipe().add(recipe1);
		italian.getRecipe().add(recipe1);
		
		//Get UOM Objects
		UnitOfMeasurement ounce = ounceOpt.get();
		UnitOfMeasurement pound = poundOpt.get();
		UnitOfMeasurement teaspoon = teaSpoonOpt.get();
		UnitOfMeasurement tablespoon = tablespoonOpt.get();
		UnitOfMeasurement cup = cupOpt.get();
		
		
		//Create Ingeridients Objects
		
		Ingredient beef = new Ingredient("pound ground beef chuck", new BigDecimal(3/4), recipe1, pound);
	    Ingredient salt = new Ingredient("salt", new BigDecimal(1/4), recipe1, teaspoon);
	    Ingredient blackPepper = new Ingredient("ground black pepper", new BigDecimal(1/4), recipe1, teaspoon);
	    Ingredient ketchup = new Ingredient("ketchup", new BigDecimal(2/3), recipe1, cup);
	    Ingredient yellowMustard = new Ingredient("prepared yellow mustard", new BigDecimal(2), recipe1, tablespoon);
	    Ingredient pizzaCrust = new Ingredient("(14 ounce) pre-baked pizza crust", new BigDecimal(14), recipe1, ounce);
	    Ingredient mozzarellaCheese = new Ingredient("shredded mozzarella cheese", new BigDecimal(3/2), recipe1, cup);
	    Ingredient cheddarCheese = new Ingredient("shredded sharp Cheddar cheese", new BigDecimal(1), recipe1, cup);
	    Ingredient onions = new Ingredient("chopped onions", new BigDecimal(1/2), recipe1, cup);
	    Ingredient pickleSlices = new Ingredient("dill pickle slices", new BigDecimal(1/2), recipe1, cup);
	    Ingredient shreddedLettuce = new Ingredient("shredded lettuce (Optional)", new BigDecimal(1), recipe1, cup);
	    Ingredient dicedTomatoes  = new Ingredient("diced tomatoes (Optional)", new BigDecimal(1), recipe1, cup);
		
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
	    
	    recipe1.setIngredients(ingredients);
	    
	    recipes.add(recipe1);
	    
		return recipes;
	}

}
