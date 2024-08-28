package com.coderscampus.service;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.coderscampus.domain.Recipe;

@Service
public class FileService {

	public List<Recipe> readFile() throws IOException {

		List<Recipe> recipe = new ArrayList<>();
		try (Reader in = new FileReader("recipes.txt")) {
			@SuppressWarnings("deprecation")
			Iterable<CSVRecord> records = CSVFormat.DEFAULT
			.withHeader("Cooking Minutes", "Dairy Free", "Gluten Free",
					"Instructions", "Preparation Minutes", "Price Per Serving", "Ready In Minutes", "Servings",
					"Spoonacular Score", "Title", "Vegan", "Vegetarian")
			.withIgnoreSurroundingSpaces(true)
			.parse(in);

			for (CSVRecord record : records) {
				Recipe recipes = new Recipe();
				recipes.setCookingMinutes(Integer.parseInt(record.get("Cooking Minutes")));
				recipes.setDairyFree(Boolean.parseBoolean(record.get("Dairy Free")));
				recipes.setGlutenFree(Boolean.parseBoolean(record.get("Gluten Free")));
				recipes.setInstructions(record.get("Instructions"));
				recipes.setPreparationMinutes(Double.parseDouble(record.get("Preparation Minutes")));
				recipes.setPricePerServing(Double.parseDouble(record.get("Price Per Serving")));
				recipes.setReadyInMinutes(Integer.parseInt(record.get("Ready In Minutes")));
				recipes.setServings(Integer.parseInt(record.get("Servings")));
				recipes.setSpoonacularScore(Double.parseDouble(record.get("Spoonacular Score")));
				recipes.setTitle(record.get("Title"));
				recipes.setVegan(Boolean.parseBoolean(record.get("Vegan")));
				recipes.setVegetarian(Boolean.parseBoolean(record.get("Vegetarian")));

				recipe.add(recipes);

			}
			return recipe;
		}

	}
}
