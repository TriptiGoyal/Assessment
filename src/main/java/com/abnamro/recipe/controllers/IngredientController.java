package com.abnamro.recipe.controllers;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abnamro.recipe.constants.MessageConstants;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.request.IngredientRequest;
import com.abnamro.recipe.response.AddIngredientResponse;
import com.abnamro.recipe.response.IngredientsResponse;
import com.abnamro.recipe.services.IngredientService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "IngredientController", tags = "Ingredient Controller")
@RestController
@RequestMapping(value = "api/v1/ingredients")
public class IngredientController {

	private final Logger logger = LoggerFactory.getLogger(IngredientController.class);

	private final IngredientService ingredientService;

	@Autowired
	public IngredientController(IngredientService ingredientService) {
		this.ingredientService = ingredientService;
	}

	@ApiOperation(value = "Add ingredient")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Ingredient Added"),
			@ApiResponse(code = 400, message = "Bad input") })
	@PostMapping
	public ResponseEntity<AddIngredientResponse> addIngredient(
			@ApiParam(value = "Ingredient Params", required = true) @Valid @RequestBody IngredientRequest ingredientRequest) {
		logger.info("Adding ingredient");
		Integer ingredientId = ingredientService.addIngredient(ingredientRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(new AddIngredientResponse(ingredientId));
	}

	@ApiOperation(value = "Fetch Ingredients")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success Response"), })
	@GetMapping
	public ResponseEntity<List<IngredientsResponse>> getIngredients() {
		logger.info("Fetching of ingredients have started..");
		List<Ingredient> ingredientList = ingredientService.fetchIngredients();
		return ResponseEntity.status(HttpStatus.OK)
				.body(ingredientList.stream().map(IngredientsResponse::new).collect(Collectors.toList()));
	}

	@ApiOperation(value = "Fetch Ingredient By ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success Response"),
			@ApiResponse(code = 404, message = "Ingredient was not found by ID") })
	@GetMapping(value = "/{ingredientId}")
	public ResponseEntity<IngredientsResponse> getIngredientById(
			@ApiParam(value = "Ingredient ID", required = true) @PathVariable(name = "ingredientId") Integer ingredientId) {
		logger.info("Fetching ingredient by ingredientId : ", ingredientId);
		Ingredient ingredient = ingredientService.findById(ingredientId);
		return ResponseEntity.status(HttpStatus.OK).body(new IngredientsResponse(ingredient));
	}

	@ApiOperation(value = "Delete Ingredient by ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successful Operation, Ingredient has been Deleted"),
			@ApiResponse(code = 400, message = "Invalid Request"),
			@ApiResponse(code = 404, message = "Ingredient not found") })
	@DeleteMapping
	public void deleteIngredient(
			@ApiParam(value = "ingredient ID", required = true) @NotNull(message = MessageConstants.NULL_ID) @RequestParam(name = "ingredientId") Integer ingredientId) {
		logger.info("Ingredient to be deleted : ", ingredientId);
		ingredientService.deleteIngredient(ingredientId);
	}

}
