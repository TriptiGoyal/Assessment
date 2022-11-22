package com.abnamro.recipe.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abnamro.recipe.constants.MessageConstants;
import com.abnamro.recipe.exceptions.DataNotFoundException;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.repositories.RecipeRepository;
import com.abnamro.recipe.request.RecipeRequest;
import com.abnamro.recipe.request.SearchRecipeRequest;
import com.abnamro.recipe.request.SearchCriteriaRequest;
import com.abnamro.recipe.request.UpdateRequest;
import com.abnamro.recipe.response.RecipesResponse;
import com.abnamro.recipe.search.RecipeCriteriaSpecificationsBuilder;
import com.abnamro.recipe.search.SearchCriteria;

@Service
@Transactional
public class RecipeServiceImpl implements RecipeService {
	private final RecipeRepository recipeRepository;
	private final IngredientServiceImpl ingredientService;

	@Autowired
	public RecipeServiceImpl(RecipeRepository recipeRepo, IngredientServiceImpl ingredientService) {
		this.recipeRepository = recipeRepo;
		this.ingredientService = ingredientService;
	}

	public Recipe addRecipe(RecipeRequest recipeRequest) {
		Set<Ingredient> ingredients = Optional.ofNullable(recipeRequest.getIngredientIds())
				.map(ingredientService::getIngredientsByIds).orElse(null);
		Recipe recipe = new Recipe();
		recipe.setName(recipeRequest.getName());
		recipe.setServingCapacity(recipeRequest.getServingCapacity());
		recipe.setRecipeIngredients(ingredients);
		recipe.setRecipeInstructions(recipeRequest.getRecipeInstructions());
		recipe.setRecipeType(recipeRequest.getRecipeType());
		Recipe addRecipe = recipeRepository.save(recipe);
		return addRecipe;
	}

	public List<RecipesResponse> fetchRecipes() {
		return recipeRepository.findAll().stream().map(RecipesResponse::new).collect(Collectors.toList());
	}

	public Recipe getRecipeById(int recipeId) {
		return recipeRepository.findById(recipeId)
				.orElseThrow(() -> new DataNotFoundException(MessageConstants.RECIPE_NOT_FOUND));
	}

	public void updateRecipe(UpdateRequest updateRecipeRequest) {
		Recipe recipe = recipeRepository.findById(updateRecipeRequest.getRecipeId())
				.orElseThrow(() -> new DataNotFoundException(MessageConstants.RECIPE_NOT_FOUND));
		Set<Ingredient> ingredients = Optional.ofNullable(updateRecipeRequest.getIngredientIds())
				.map(ingredientService::getIngredientsByIds).orElse(null);
		recipe.setName(updateRecipeRequest.getName());
		recipe.setRecipeType(updateRecipeRequest.getRecipeType());
		recipe.setServingCapacity(updateRecipeRequest.getServingCapacity());
		recipe.setRecipeInstructions(updateRecipeRequest.getRecipeInstructions());
		if (Optional.ofNullable(ingredients).isPresent())
			recipe.setRecipeIngredients(ingredients);
		recipeRepository.save(recipe);
	}

	public void deleteRecipe(int recipeId) {
		if (!recipeRepository.existsById(recipeId)) {
			throw new DataNotFoundException(MessageConstants.RECIPE_NOT_FOUND);
		}
		recipeRepository.deleteById(recipeId);
	}

	public List<RecipesResponse> searchRecipeByCriteria(SearchRecipeRequest searchRecipeRequest) {
		List<SearchCriteria> searchRequests = new ArrayList<>();
		RecipeCriteriaSpecificationsBuilder builder = new RecipeCriteriaSpecificationsBuilder(searchRequests);
		Specification<Recipe> recipeSpecification = addSpecification(searchRecipeRequest, builder);
		List<Recipe> recipes = recipeRepository.findAll(recipeSpecification);
		return recipes.stream().map(RecipesResponse::new).collect(Collectors.toList());
	}

	private Specification<Recipe> addSpecification(SearchRecipeRequest recipeSearchRequest,
			RecipeCriteriaSpecificationsBuilder builder) {
		List<SearchCriteriaRequest> searchCriteriaRequests = recipeSearchRequest.getSearchRequests();
		if (Optional.ofNullable(searchCriteriaRequests).isPresent()) {
			List<SearchCriteria> searchCriteriaList = searchCriteriaRequests.stream().map(SearchCriteria::new)
					.collect(Collectors.toList());
			if (!searchCriteriaList.isEmpty())
				searchCriteriaList.forEach(criteria -> {
					criteria.setFilterOption(recipeSearchRequest.getFilterOption());
					builder.with(criteria);
				});
		}
		return builder.build().orElseThrow(() -> new DataNotFoundException(MessageConstants.SEARCH_CRITERIA_NOT_FOUND));
	}
}
