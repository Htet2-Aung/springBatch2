package com.hostmdy.recipe.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.service.ImageService;
import com.hostmdy.recipe.service.RecipeService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class ImageController {

	private final ImageService imageService;
	private final RecipeService recipeService;
	
	
	public ImageController(ImageService imageService, RecipeService recipeService) {
		super();
		this.imageService = imageService;
		this.recipeService = recipeService;
	}

	@GetMapping("/recipe/{id}/image")
	public String showImageForm(@PathVariable Long id, Model model) {
		model.addAttribute("recipeId",id);
		return "recipe/image-upload";
	}
	
	@PostMapping("/recipe/{id}/image")
	public String submitImage(@PathVariable Long id,@RequestParam MultipartFile imagefile) {
		imageService.saveImageToDb(id, imagefile);
		
		return "redirect:/recipe/show/"+id;
	}
	
	@GetMapping("/recipe/{id}/recipeImage")
	public void rederImageFromDB(@PathVariable Long id, HttpServletResponse response) {
		Optional<Recipe> recipeOpt = recipeService.getRecipe(id);
		
		if(recipeOpt.isPresent()) {
			Recipe recipe = recipeOpt.get();
			
			if(recipe.getImage() != null) {
			byte[] byteArray = new byte[recipe.getImage().length];
			
			int i = 0;
			
			for(final byte b : recipe.getImage()) {
				byteArray[i++] = b;
			}
			
			try {
				InputStream ls = new ByteArrayInputStream(byteArray);
				response.setContentType("image/jpeg");
				
				IOUtils.copy(ls, response.getOutputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				System.out.println("#####Error");
			}

		}else {
			System.out.println("$$$$ Recipe is null");
		}
		
	
	}
	
}
