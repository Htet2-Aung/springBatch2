package com.hostmdy.recipe.service.Impl;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hostmdy.recipe.domain.Recipe;
import com.hostmdy.recipe.repository.RecipeRepository;
import com.hostmdy.recipe.service.ImageService;

import jakarta.transaction.Transactional;

@Service
public class ImageServiceImpl implements ImageService {

	private final RecipeRepository recipeRepository;
	public ImageServiceImpl(RecipeRepository recipeRepository) {
		super();
		this.recipeRepository = recipeRepository;
	}



	@Override
	@Transactional
	public void saveImageToDb(Long recipeId, MultipartFile file) {
		// TODO Auto-generated method stub  
		if(recipeId != null) {
			Recipe recipe = recipeRepository.findById(recipeId).get();
			
			try {
				byte[] byteObjects = new byte[file.getBytes().length];
				
				int i =0;
				for(final byte b : file.getBytes()) {
					byteObjects[i++] = b;
				}
				
				recipe.setImage(byteObjects);
				recipeRepository.save(recipe);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(" #### Error Occurs!");
			} 
			
		}else {
			System.out.println("###### RecipeId is null");
		}
	}

}
