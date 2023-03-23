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
import com.hostmdy.recipe.domain.UnitsOfMeasurement;
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
		if(americanOpt.isEmpty())throw new RuntimeException("American category not found!");
		
        Optional<Category> fastFoodOpt = categoryRepository.findByTitle("FastFood");
		if(fastFoodOpt .isEmpty())throw new RuntimeException("FastFoodOpt  category not found!");
	
		 Optional<Category> italianOpt = categoryRepository.findByTitle("Italian");
		 if(italianOpt .isEmpty())throw new RuntimeException("Italian  category not found!");
		 
		 Optional<Category> seaFoodOpt = categoryRepository.findByTitle("SeaFood");
		 if(seaFoodOpt .isEmpty())throw new RuntimeException("SeaFood  category not found!");
		
		//Get UOM Optional
		 
		 Optional< UnitsOfMeasurement> poundOpt = uomRepository.findByUom("pound");
		 if (poundOpt.isEmpty()) throw new RuntimeException("Unit-Pound not found");
		
		 Optional< UnitsOfMeasurement> teaspoonOpt = uomRepository.findByUom("teaspoon");
		 if (teaspoonOpt.isEmpty()) throw new RuntimeException("TeaSpoon not found");
		 
		 Optional< UnitsOfMeasurement> tablespoonOpt = uomRepository.findByUom("tablespoon");
		 if (tablespoonOpt.isEmpty()) throw new RuntimeException("TablespoonOpt not found");
		 
		 Optional< UnitsOfMeasurement> cupOpt = uomRepository.findByUom("cup");
		 if (cupOpt.isEmpty()) throw new RuntimeException("Cup not found");
		 
		 Optional< UnitsOfMeasurement> ounceOpt = uomRepository.findByUom("ounce");
		 if (ounceOpt.isEmpty()) throw new RuntimeException("Ounce not found");
		 
		 Optional< UnitsOfMeasurement> pinchOpt = uomRepository.findByUom("pinch");
		 if (pinchOpt.isEmpty()) throw new RuntimeException("Pinch not found");
		 
		 Optional< UnitsOfMeasurement> unitOpt = uomRepository.findByUom("unit");
		 if (unitOpt.isEmpty()) throw new RuntimeException("Unit not found");
		 
		 //Note -> Recipe connect
		 
		 Notes notes = new Notes();
		 notes.setRecipeNote("Nutriations Facts (per servaing)\r\n"
				 +"Calories 740\r\n"
				 +"Fat 34g\r\n"
				 +"Carbs 67\r\n"
				 +"Protein 45g\r\n");
		
		 //onetoone recipe not-e
		 
		 Recipe recipe = new Recipe();
		 
		 recipe.setNotes(notes);
		 notes.setRecipe(recipe);
		 
		 recipe.setTitle("True CheeseBurger Pizza");
		 recipe.setSource("https://www.allrecipes.com");
		 recipe.setPreTime(15);
		 recipe.setCookTime(25);
		 recipe.setServings(3);
		 recipe.setDescription("This is a unique twist on two favorites: pizza and cheeseburgers! It is super fast to make, and you can easily add your favorite burger toppings such as lettuce and tomato. This is a family favorite and disappears fast!");
		 recipe.setDirections("1. Preheat oven to 375 degrees F (190 degrees C).\r\n"
		 		+ "\r\n"
		 		+ "2.Heat a large skillet over medium-high heat. Cook and stir beef, salt, and pepper in the hot skillet until meat is browned and crumbly, 5 to 7 minutes; drain and discard grease.\r\n"
		 		+ "\r\n"
		 		+ "3.Mix ketchup and mustard in a bowl; spread mixture over pizza crust. Spread mozzarella cheese and Cheddar cheese over ketchup mixture and top with beef and onions. Transfer pizza to a large baking sheet.\r\n"
		 		+ "\r\n"
		 		+ "4.Bake in preheated oven until cheese is melted, about 20 minutes. Top with pickles and allow pizza to cool for 5 minutes. Top with shredded lettuce and tomatoes before slicing and serving. ");
		 recipe.setDifficulty(Difficulty.EASY);
		 
		 //get category object
		 Category american = americanOpt.get();
		 Category fastFood = fastFoodOpt.get();
		 Category italian = italianOpt.get();
		 Category seaFood = seaFoodOpt.get();
		 
		 
		 Set<Category> categories = new HashSet<>();
		 categories.add(american);
		 categories.add(fastFood);
		 categories.add(italian);
		 categories.add(seaFood);
		 
		 //category -recipe many to many two ways
		 
		 recipe.setCategories(categories);
		 american.getRecipes().add(recipe);
		 fastFood.getRecipes().add(recipe);
		 italian.getRecipes().add(recipe);
		 seaFood.getRecipes().add(recipe);
		 
		 //get UOM objects
		 
		 UnitsOfMeasurement pound = poundOpt.get();
		 UnitsOfMeasurement teaspoon = teaspoonOpt.get();
		 UnitsOfMeasurement tablespoon = tablespoonOpt.get();
		 UnitsOfMeasurement cup = cupOpt.get();
		 UnitsOfMeasurement ounce = ounceOpt.get();
		 UnitsOfMeasurement pinch = pinchOpt.get();
		 UnitsOfMeasurement unit =  unitOpt.get();
		 
		 //ingredient -recipe objects
		 //True CheeseBurger Pizza
			Ingredient beef = new Ingredient("ground beef chuck", new BigDecimal(3.0 / 4.0), recipe, pound);
			Ingredient salt = new Ingredient("salt", new BigDecimal(1 / 4), recipe, teaspoon);
			Ingredient blackPepper = new Ingredient(" ground black pepper", new BigDecimal(1 / 4), recipe,
					teaspoon);
			Ingredient ketchup = new Ingredient(" ketchup", new BigDecimal(2 / 3), recipe, cup);
			Ingredient yellowMustard = new Ingredient(" prepared yellow mustard", new BigDecimal(2), recipe,
					tablespoon);
			Ingredient mozzarellaCheese = new Ingredient("shredded mozzarella cheese", new BigDecimal(3 / 2),
					recipe, cup);
			Ingredient cheddarCheese = new Ingredient(" shredded sharp Cheddar cheese", new BigDecimal(1), recipe,
					cup);
			Ingredient onions = new Ingredient(" chopped onions", new BigDecimal(1 / 2), recipe, cup);
			Ingredient pickleSlices = new Ingredient(" dill pickle slices", new BigDecimal(1 / 2), recipe, cup);
			Ingredient shreddedLettuce = new Ingredient(" shredded lettuce (Optional)", new BigDecimal(1), recipe,
					cup);
			Ingredient dicedTomatoes = new Ingredient(" diced tomatoes (Optional)", new BigDecimal(1), recipe, cup);
			Ingredient pizzaCrust = new Ingredient("pre-baked pizza crust", new BigDecimal(14), recipe, ounce);

				
			
//			Set<Ingredient> ingredients=new HashSet<>();
//			
//			ingredients.add(beef);
//			ingredients.add(salt);
//			ingredients.add(blackPepper);
//			ingredients.add(pizzaCrust);
//			ingredients.add(dicedTomatoes);
//			ingredients.add(pickleSlices);
//			ingredients.add(onions);
//			ingredients.add(shreddedLettuce);
//			ingredients.add(cheddarCheese);
//			ingredients.add(mozzarellaCheese);
//			ingredients.add(ketchup);
//			ingredients.add(yellowMustard);
//			
//			recipe.setIngredients(ingredients);
			
			recipe.getIngredients().add(beef);
			recipe.getIngredients().add(salt);
			recipe.getIngredients().add(blackPepper);
			recipe.getIngredients().add(yellowMustard);
			recipe.getIngredients().add(ketchup);
			recipe.getIngredients().add(mozzarellaCheese);
			recipe.getIngredients().add(cheddarCheese);
			recipe.getIngredients().add(onions);
			recipe.getIngredients().add(pickleSlices);
			recipe.getIngredients().add(dicedTomatoes);
			recipe.getIngredients().add(pizzaCrust);
			
			recipes.add(recipe);
			
			//Tuna Fish Salad
			 Notes notes1 = new Notes();
			 notes1.setRecipeNote("Nutrition Facts (per serving)\r\n"
			 		+ "Calories 240\r\n"
			 		+ "Fat 22g\r\n"
			 		+ "Carbs 2g\r\n"
			 		+ "Protein 9g\r\n");
			 
			 Recipe recipe1 = new Recipe();
			 
			 recipe1.setNotes(notes1);
			 notes1.setRecipe(recipe1);
			 
			 recipe1.setTitle("Tuna Fish Salad");
			 recipe1.setSource("https://www.allrecipes.com");
			 recipe1.setPreTime(15);
			 recipe1.setCookTime(15);
			 recipe1.setServings(5);
			 recipe1.setDescription("This tuna fish is excellent served on top of a green salad or between two pieces of bread with fresh lettuce. Sprinkle a little paprika on top to add a little flavor and color");
			 recipe1.setDirections("1.Combine tuna, mayonnaise, celery, onion, parsley, lemon juice, garlic powder, salt, and pepper in a large bowl; mix well. Season with paprika; refrigerate until chilled. ");
			 
			 recipe1.setDifficulty(Difficulty.EASY);
			 
			 
			 recipe1.setCategories(categories);
			 american.getRecipes().add(recipe1);
			 fastFood.getRecipes().add(recipe1);
			 italian.getRecipes().add(recipe1);
			 seaFood.getRecipes().add(recipe1);
			 
			//Tuna Fish Salad
				Ingredient tuna = new Ingredient("drained truna", new BigDecimal(5), recipe1, ounce);
				Ingredient mayonnaise = new Ingredient("mayonnaise", new BigDecimal(1 / 2), recipe1, cup);
				Ingredient choppedCelery = new Ingredient("chopped celery", new BigDecimal(1/4), recipe1, cup);
				Ingredient parsley = new Ingredient("chopped fresh parsley", new BigDecimal(1 ), recipe1, teaspoon);
				Ingredient lemonJuice = new Ingredient("lemon juice", new BigDecimal(1/2), recipe1, teaspoon);
				Ingredient salt1 = new Ingredient("salt", new BigDecimal(1 / 8), recipe1, teaspoon);
				Ingredient onions1 = new Ingredient("chopped onion", new BigDecimal(1), recipe1, tablespoon);
				Ingredient powder = new Ingredient("garlic powder", new BigDecimal(1/8), recipe1, tablespoon);
				Ingredient pepper = new Ingredient("ground black pepper ", new BigDecimal(1/8), recipe1, teaspoon);
				Ingredient paprika = new Ingredient("paprika", new BigDecimal(1), recipe1, pinch);
			 
				Set<Ingredient> ingredients1=new HashSet<>();
				
				//Tuna Fish Salad
				ingredients1.add(tuna);
				ingredients1.add(mayonnaise);
				ingredients1.add(choppedCelery);
				ingredients1.add(onions1);
				ingredients1.add(paprika);
				ingredients1.add(parsley);
				ingredients1.add(pepper);
				ingredients1.add(lemonJuice);
				ingredients1.add(salt1);
				ingredients1.add(powder);			
				
				recipe1.setIngredients(ingredients1);
				
				recipes.add(recipe1);
				
				//Baked Dijon Salmon
				
				 Notes notes2 = new Notes();
				 notes2.setRecipeNote("Nutrition Facts (per serving)\r\n"
				 		+ "Calories 422\r\n"
				 		+ "Fat 29g\r\n"
				 		+ "Carbs 18g\r\n"
				 		+ "Protein24g\r\n");
				 
				 Recipe recipe2 = new Recipe();
				 
				 recipe2.setNotes(notes2);
				 notes2.setRecipe(recipe2);
				 
				 recipe2.setTitle("Baked Dijon Salmon");
				 recipe2.setSource("https://www.allrecipes.com");
				 recipe2.setPreTime(15);
				 recipe2.setCookTime(15);
				 recipe2.setServings(2);
				 recipe2.setDescription("This mustard-crusted salmon is a wonderful way to prepare fresh salmon fillets in the oven. Be sure to make extra — your family will be begging for more!");
				 recipe2.setDirections("1. Preheat the oven to 400 degrees F (200 degrees C).\r\n"
				 		+ "2.Stir together butter, mustard, and honey in a small bowl. Set aside.\r\n"
				 		+ "3.Mix together bread crumbs, pecans, and parsley in another bowl.  \r\n"
				 		+ "4.Brush each salmon fillet lightly with honey mustard mixture.\r\n"
				 		+ "5.Sprinkle the tops of fillets with bread crumb mixture.\r\n"
				 		+ "6.Bake salmon in the preheated oven until it flakes easily with a fork, 12 to 15 minutes. Season with salt and pepper, and garnish with a wedge of lemon. \r\n");
				 
				 recipe2.setDifficulty(Difficulty.EASY);
				 
				 
				 recipe2.setCategories(categories);
				 american.getRecipes().add(recipe2);
				 fastFood.getRecipes().add(recipe2);
				 italian.getRecipes().add(recipe2);
				 seaFood.getRecipes().add(recipe2);
				 
				//Baked Dijon Salmon
					Ingredient butter = new Ingredient("melted butter", new BigDecimal(1/4), recipe2, cup);
					Ingredient dijonMustard = new Ingredient("Dijon Mustard", new BigDecimal(3), recipe2, tablespoon);
					Ingredient honey = new Ingredient("honey", new BigDecimal(1.5), recipe2, tablespoon);
					Ingredient breadCrumbs = new Ingredient("bread crumbs", new BigDecimal(1/4 ), recipe2, cup);
					Ingredient pecans = new Ingredient("chopped pecans", new BigDecimal(1/4), recipe2, cup);
					Ingredient parsley1 = new Ingredient("chopped fesh parsley", new BigDecimal(4), recipe2, teaspoon);
					Ingredient saltAndPepper = new Ingredient("salt and peper ", new BigDecimal(1/8), recipe2, tablespoon);
					Ingredient salmon = new Ingredient("fillets salmon", new BigDecimal(4), recipe2, ounce);
				 
					Set<Ingredient> ingredients2=new HashSet<>();
					
					//Baked Dijon Salmon
					ingredients2.add(salmon);
					ingredients2.add(saltAndPepper);
					ingredients2.add(parsley1);
					ingredients2.add(pecans);
					ingredients2.add(breadCrumbs);
					ingredients2.add(honey);
					ingredients2.add(dijonMustard);
					ingredients2.add(butter);
					
					recipe2.setIngredients(ingredients2);
					
					recipes.add(recipe2);
					
					
					//Pork Schnitzel
					
					 Notes notes3 = new Notes();
					 notes3.setRecipeNote("Nutrition Facts (per serving)\r\n"
					 		+ "Calories 599\r\n"
					 		+ "Fat 39g\r\n"
					 		+ "Carbs 35g\r\n"
					 		+ "Protein 36\r\n");
					 
					 Recipe recipe3 = new Recipe();
					 
					 recipe3.setNotes(notes3);
					 notes3.setRecipe(recipe3);
					 
					 recipe3.setTitle("Pork Schnitzel");
					 recipe3.setSource("https://www.allrecipes.com");
					 recipe3.setPreTime(15);
					 recipe3.setCookTime(15);
					 recipe3.setServings(6);
					 recipe3.setDescription("To make German-style pork schnitzel, pork loin chops are pounded thin, breaded, and quickly pan fried until crisp and delicious.");
					 recipe3.setDirections("1. Place 1 pork chop between 2 sheets of plastic wrap on a clean work surface or cutting board. Pound pork chop using a meat mallet until 1/4-inch thick. Transfer to a clean plate. Repeat with remaining pork chops. Evenly sprinkle pork chops with 1/2 teaspoon of the salt and 1/4 teaspoon black pepper. .\r\n"
					 		+ "2.Mix together flour, garlic powder, onion powder, 1/4 teaspoon salt and remaining 1/4 teaspoon pepper in a shallow dish. Place eggs and milk in a separate shallow dish and whisk to combine. Place breadcrumbs in a separate shallow dish. Dip a pork chop in flour, dusting off excess flour; dip in egg mixture, allowing excess to drip off. Place into breadcrumbs; flip and press pork into breadcrumbs until well covered. Repeat with remaining pork chops. Place prepared pork chops on a plate or sheet tray and transfer to the refrigerator, uncovered, for 5 to 10 minutes. \r\n"
					 		+ "3.Heat 3 tablespoons of the oil and 1 tablespoon of the butter in a large skillet over medium-high. Add 2 pork chops and cook, flipping once, until golden brown on each side and no longer pink in the center, 2 to 3 minutes per side. An instant-read thermometer inserted into the center should read at least 145 degrees F (63 degrees C). Adjust heat to prevent burning. \r\n"
					 		+ "4.Transfer to a clean plate or sheet tray. Pour off and discard oil from pan; wipe skillet clean and place back on stovetop over medium-high. Add remaining 3 tablespoons oil and 1 tablespoon butter and repeat with remaining 2 pork chops. Sprinkle cooked pork chops with remaining 1/4 teaspoon salt and thyme. Serve chops with lemon wedges. \r\n");
					 
					 recipe3.setDifficulty(Difficulty.EASY);
					 
					 
					 recipe3.setCategories(categories);
					 american.getRecipes().add(recipe3);
					 italian.getRecipes().add(recipe3);
					 
					//Pork Schnitzel
						Ingredient pork = new Ingredient("boneless pork loin chops", new BigDecimal(5), recipe3, ounce);
						Ingredient  tableSalt= new Ingredient("tableSalt", new BigDecimal(1), recipe3, tablespoon);
						Ingredient blackPepper3	 = new Ingredient("Black pepper", new BigDecimal(1/2), recipe3, tablespoon);
						Ingredient flour = new Ingredient("all-purpose flour", new BigDecimal(1/4 ), recipe3, cup);
						Ingredient garlicPowder = new Ingredient("garlic powder", new BigDecimal(1/4), recipe3, teaspoon);
						Ingredient onionPowder = new Ingredient("onion powder", new BigDecimal(1/2), recipe3, teaspoon);
						Ingredient eggs = new Ingredient("Large eggs ", new BigDecimal(2), recipe3, unit);
						Ingredient milk = new Ingredient("milk", new BigDecimal(1/4), recipe3, cup);
						Ingredient canolaOil = new Ingredient("canola oil", new BigDecimal(6), recipe3, tablespoon);
						Ingredient breadCrumbs3 = new Ingredient("panko breadcrumbs", new BigDecimal(1.5), recipe3, cup);
						Ingredient unsaltedButter = new Ingredient("unsaltedButter", new BigDecimal(2), recipe3, tablespoon);
						
						
						Set<Ingredient> ingredients3=new HashSet<>();
						
						//Pork Schnitzel
						ingredients3.add(pork);
						ingredients3.add(tableSalt);
						ingredients3.add(blackPepper3);
						ingredients3.add(flour);
						ingredients3.add(garlicPowder);
						ingredients3.add(onionPowder);
						ingredients3.add(eggs);
						ingredients3.add(milk);
						ingredients3.add(canolaOil);
						ingredients3.add(breadCrumbs3);
						ingredients3.add(unsaltedButter);
						
						recipe3.setIngredients(ingredients3);
						
						recipes.add(recipe3);
				
			return recipes;
		}

	}
