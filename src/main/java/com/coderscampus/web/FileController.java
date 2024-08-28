package com.coderscampus.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.domain.Recipe;
import com.coderscampus.service.FileService;

@RestController
public class FileController {

	@Autowired
	private FileService fileService;
	
	 private List<Recipe> getAllRecipes() throws IOException {
	        return fileService.readFile();
	    }

	@GetMapping("/all-recipes")
	public List<Recipe> getAllRecipesEndpoint() throws IOException {
		return  getAllRecipes();

	}

	@GetMapping("/gluten-free")
	public List<Recipe> getGultenFreeRecipes() throws IOException {
		List<Recipe> allRecipes = getAllRecipes();
		return allRecipes.stream()
				         .filter(recipe -> recipe.getGlutenFree())
				         .collect(Collectors.toList());

	}

	@GetMapping("/vegan")
	public List<Recipe> getVeganRecipes() throws IOException {
		List<Recipe> allRecipes = getAllRecipes();
		return allRecipes.stream()
						 .filter(recipe -> recipe.getVegan())
				         .collect(Collectors.toList());

	}

	@GetMapping("/vegan-and-gluten-free")
	public List<Recipe> getVeganAndGultenFreeRecipes() throws IOException {
		List<Recipe> allRecipes = getAllRecipes();
		return allRecipes.stream()
				         .filter(recipe -> recipe.getVegan())
				         .filter(recipe -> recipe.getGlutenFree())
				         .collect(Collectors.toList());

	}

	@GetMapping("/vegetarian")
	public List<Recipe> getVegetarian() throws IOException {
		List<Recipe> allRecipes = getAllRecipes();
		return allRecipes.stream()
				         .filter(recipe -> recipe.getVegetarian())
				         .collect(Collectors.toList());

	}

}
