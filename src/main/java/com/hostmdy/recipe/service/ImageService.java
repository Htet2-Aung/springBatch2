package com.hostmdy.recipe.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImageService  {

	void saveImageToDb(Long recipeId,MultipartFile  file);
}
