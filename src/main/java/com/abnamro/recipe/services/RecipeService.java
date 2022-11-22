package com.abnamro.recipe.services;

import java.util.List;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.request.RecipeRequest;
import com.abnamro.recipe.request.SearchRecipeRequest;
import com.abnamro.recipe.request.UpdateRequest;
import com.abnamro.recipe.response.RecipesResponse;

public interface RecipeService {

	 public Recipe addRecipe(RecipeRequest recipeRequest);
	 
	 public List<RecipesResponse> fetchRecipes();
	 
	 public Recipe getRecipeById(int id);
	 
	 public void updateRecipe(UpdateRequest updateRecipeRequest);
	 
	 public void deleteRecipe(int id);
	 
	 public List<RecipesResponse> searchRecipeByCriteria(SearchRecipeRequest recipeSearchRequest);
	 
	 
}
