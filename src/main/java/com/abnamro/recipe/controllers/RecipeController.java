package com.abnamro.recipe.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.recipe.constants.MessageConstants;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.request.RecipeRequest;
import com.abnamro.recipe.request.SearchRecipeRequest;
import com.abnamro.recipe.request.UpdateRequest;
import com.abnamro.recipe.response.RecipesResponse;
import com.abnamro.recipe.services.RecipeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "RecipeController", tags = "Recipe Controller")
@RestController
@RequestMapping(value = "api/v1/recipes")
public class RecipeController {
	private final Logger logger = LoggerFactory.getLogger(RecipeController.class);

	private final RecipeService recipeService;

	@Autowired
	public RecipeController(RecipeService recipeService) {
		this.recipeService = recipeService;
	}

	@ApiOperation(value = "Add recipe")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Recipe Added"),
			@ApiResponse(code = 400, message = "Bad input") })
	@PostMapping
	public ResponseEntity<RecipesResponse> addRecipe(
			@ApiParam(value = "Recipe Params", required = true) @Valid @RequestBody RecipeRequest request) {
		logger.info("Adding recipe initiated..");
		Recipe recipe = recipeService.addRecipe(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(new RecipesResponse(recipe));
	}

	@ApiOperation(value = "Fetch All Recipes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"), })
	@GetMapping
	public ResponseEntity<List<RecipesResponse>> fetchRecipes() {
		logger.info("Fetching recipes");
		List<RecipesResponse> listOfRecipe = recipeService.fetchRecipes();
		return ResponseEntity.status(HttpStatus.OK).body(listOfRecipe);
	}

	@ApiOperation(value = "Search recipes by selecting various parameters")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
			@ApiResponse(code = 404, message = "Different error messages related to criteria")

	})
	@PostMapping(path = "/search")
	public List<RecipesResponse> searchRecipeByCriteria(
			@ApiParam(value = "Search Params") @RequestBody @Valid SearchRecipeRequest searchRequest) {
		logger.info("Searching Recipe");
		return recipeService.searchRecipeByCriteria(searchRequest);
	}

	@ApiOperation(value = "Fetch one recipe by its ID", response = Recipe.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Response"),
			@ApiResponse(code = 404, message = "Recipe not found by given ID") })
	@GetMapping(value = "/{recipeId}")
	public ResponseEntity<RecipesResponse> getRecipe(
			@ApiParam(value = "Recipe ID", required = true) @PathVariable(name = "recipeId") Integer recipeId) {
		Recipe recipeResp = recipeService.getRecipeById(recipeId);
		logger.info("Fetching recipe having recipeId :", recipeId);
		return ResponseEntity.status(HttpStatus.OK).body(new RecipesResponse(recipeResp));
	}

	@ApiOperation(value = "Delete recipe")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Operation, Recipe Deleted"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Recipe not found by given ID") })
	@DeleteMapping
	public void deleteRecipe(
			@ApiParam(value = "Recipe ID", required = true) @NotNull(message = MessageConstants.NULL_ID) @RequestParam(name = "id") Integer id) {
		logger.info("Deleting Recipe having Id" + id);
		recipeService.deleteRecipe(id);
	}

	@ApiOperation(value = "Update recipe")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Ingredient has been updated"),
			@ApiResponse(code = 400, message = "Bad Request") })
	@PatchMapping
	public void updateRecipe(
			@ApiParam(value = "Properties of Recipe", required = true) @Valid @RequestBody UpdateRequest updateRecipeRequest) {
		logger.info("Updating Recipe started..");
		recipeService.updateRecipe(updateRecipeRequest);
	}

}
