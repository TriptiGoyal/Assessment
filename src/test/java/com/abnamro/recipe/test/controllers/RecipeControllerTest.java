package com.abnamro.recipe.test.controllers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.abnamro.recipe.controllers.RecipeController;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.request.RecipeRequest;
import com.abnamro.recipe.response.RecipesResponse;
import com.abnamro.recipe.services.RecipeServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class RecipeControllerTest {
	@Mock
	private RecipeServiceImpl recipeService;

	@InjectMocks
	private RecipeController recipeController;

	@Test
	public void test_addRecipe_success() {
		Recipe recipe = new Recipe();
		recipe.setRecipeId(7);
		recipe.setName("Oil");
		RecipeRequest request = new RecipeRequest("Pasta", "OTHER", 4, null,
				"Boil Pasta, add fried tomatoes and ready to serve.");
		when(recipeService.addRecipe(any(RecipeRequest.class))).thenReturn(recipe);
		ResponseEntity<RecipesResponse> response = recipeController.addRecipe(request);
		assertThat(response).isNotNull();
		assertThat(response.getBody().getRecipeId()).isSameAs(7);
	}

	@Test
	public void test_getRecipeById_success() {
		Recipe recipeReq = new Recipe();
		recipeReq.setRecipeId(5);
		recipeReq.setName("name");
		when(recipeService.getRecipeById(anyInt())).thenReturn(recipeReq);
		ResponseEntity<RecipesResponse> response = recipeController.getRecipe(5);
		assertThat(response.getBody().getRecipeId()).isSameAs(recipeReq.getRecipeId());
		assertThat(response.getBody().getRecipeName()).isSameAs(recipeReq.getName());
	}

	@Test
	public void test_getRecipes_success() {
		RecipesResponse recipeResp1 = new RecipesResponse();
		recipeResp1.setRecipeId(5);
		recipeResp1.setRecipeName("Tomato Sauce");
		RecipesResponse recipeResp2 = new RecipesResponse();
		recipeResp2.setRecipeId(6);
		recipeResp2.setRecipeName("Maggi Sauce");
		List<RecipesResponse> recipeSaved = new ArrayList<>();
		recipeSaved.add(recipeResp1);
		recipeSaved.add(recipeResp2);
		when(recipeService.fetchRecipes()).thenReturn(recipeSaved);
		ResponseEntity<List<RecipesResponse>> recipeResponseList = recipeController.fetchRecipes();
		assertThat(recipeSaved.size()).isSameAs(recipeResponseList.getBody().size());
		assertThat(recipeSaved.get(0).getRecipeId()).isSameAs(recipeResponseList.getBody().get(0).getRecipeId());
		assertThat(recipeSaved.get(1).getRecipeId()).isSameAs(recipeResponseList.getBody().get(1).getRecipeId());
	}

}