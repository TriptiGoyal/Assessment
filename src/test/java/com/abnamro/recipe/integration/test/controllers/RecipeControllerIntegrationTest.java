package com.abnamro.recipe.integration.test.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.repositories.IngredientRepository;
import com.abnamro.recipe.repositories.RecipeRepository;
import com.abnamro.recipe.request.RecipeRequest;
import com.abnamro.recipe.request.SearchCriteriaRequest;
import com.abnamro.recipe.request.SearchRecipeRequest;
import com.abnamro.recipe.response.RecipesResponse;
import com.abnamro.recipe.utils.builders.RecipeTestDataBuilder;

public class RecipeControllerIntegrationTest extends MvcControllerIntegrationTest {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private IngredientRepository ingredientRepository;


    @Test
    public void test_addRecipe_successfully() throws Exception {
        RecipeRequest request = new RecipeRequest("Omellete",
                "OTHER", 5, null, "Whisk eggs, water, salt and pepper, stir and fry, ready to serve.");

        MvcResult result = performPost("/api/v1/recipes", request)
                .andExpect(status().isCreated())
                .andReturn();

        Integer id = readByJsonPath(result, "$.recipeId");
        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
        assertTrue(optionalRecipe.isPresent());
        assertEquals(optionalRecipe.get().getName(), request.getName());
    }

    @Test
    public void test_getRecipe_successfully() throws Exception {
        Recipe Recipe = RecipeTestDataBuilder.createRecipe();
        Recipe savedRecipe = recipeRepository.save(Recipe);

        performGet("/api/v1/recipes/" + savedRecipe.getRecipeId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.recipeId").value(savedRecipe.getRecipeId()))
                .andExpect(jsonPath("$.recipeName").value(savedRecipe.getName()))
                .andExpect(jsonPath("$.recipeInstructions").value(savedRecipe.getRecipeInstructions()))
                .andExpect(jsonPath("$.servingCapacity").value(savedRecipe.getServingCapacity()));
    }

    @Test
    public void test_getRecipe_notFound() throws Exception {

        performGet("/api/v1/recipes/13")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_fetchRecipes_successfully() throws Exception {
        Recipe recipe1 = new Recipe();
        recipe1.setRecipeId(5);
        recipe1.setName("Sandwich");
        recipe1.setRecipeInstructions("Take two bread slices,add butter,tomato,cucumber,onion with spices, ready to serve.");
        recipe1.setRecipeType("OTHER");

        Recipe recipe2 = new Recipe();
        recipe2.setRecipeId(6);
        recipe2.setName("French Fries");
        recipe2.setRecipeInstructions("Chop the potato is shape of fries, take some oil in pan and boil, add chopped potato slices and fry. Ready to serve.");
        recipe2.setRecipeType("OTHER");

        List<Recipe> storedRecipeList = new ArrayList<>();
        storedRecipeList.add(recipe1);
        storedRecipeList.add(recipe2);

        recipeRepository.saveAll(storedRecipeList);

        MvcResult result = performGet("/api/v1/recipes")
                .andExpect(status().isOk())
                .andReturn();

        List<RecipesResponse> RecipeList = getListFromMvcResult(result, RecipesResponse.class);

        assertEquals(storedRecipeList.size()+4, RecipeList.size());
        assertEquals(storedRecipeList.get(0).getName(), RecipeList.get(4).getRecipeName());
        assertEquals(storedRecipeList.get(1).getName(), RecipeList.get(5).getRecipeName());
    }

    @Test
    public void test_updateRecipe_successfully() throws Exception {
        Recipe testRecipe = new Recipe();
        testRecipe.setName("Vegetable");
        testRecipe.setRecipeType("Vegetarian");
        testRecipe.setRecipeInstructions("chop the onion, potato");
        testRecipe.setServingCapacity(2);

        Recipe savedRecipe = recipeRepository.save(testRecipe);

        savedRecipe.setName("Vegetable");
        savedRecipe.setRecipeInstructions("chop the onion, potatoes");

        performPatch("/api/v1/recipes", savedRecipe)
                .andExpect(status().isOk());

        Optional<Recipe> updatedRecipe = recipeRepository.findById(savedRecipe.getRecipeId());

        assertTrue(updatedRecipe.isPresent());
        assertEquals(savedRecipe.getName(), updatedRecipe.get().getName());
        assertEquals(savedRecipe.getServingCapacity(), updatedRecipe.get().getServingCapacity());
        assertEquals(savedRecipe.getRecipeInstructions(), updatedRecipe.get().getRecipeInstructions());
    }

    @Test
    public void test_updateRecipe_idIsNull() throws Exception {
        Recipe testRecipe = new Recipe();
        testRecipe.setName("Biryani");
        testRecipe.setRecipeInstructions("take rice and meat, cook them");
        testRecipe.setServingCapacity(3);
        testRecipe.setRecipeType("OTHER");

        performPatch("/api/v1/recipes", testRecipe)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void test_updateRecipe_notFound() throws Exception {
        Recipe testRecipe = RecipeTestDataBuilder.createRecipe(14);

        performPatch("/api/v1/recipes", testRecipe)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_deleteRecipe_successfully() throws Exception {
        Recipe testRecipe = RecipeTestDataBuilder.createRecipe();
        Recipe savedRecipe = recipeRepository.save(testRecipe);

        performDelete("/api/v1/recipes", Pair.of("id", String.valueOf(savedRecipe.getRecipeId())))
                .andExpect(status().isOk());

        Optional<Recipe> deletedRecipe = recipeRepository.findById(savedRecipe.getRecipeId());

        assertTrue(!deletedRecipe.isPresent());
    }

    @Test
    public void test_deleteRecipe_notFound() throws Exception {
        performDelete("/api/v1/recipes", Pair.of("id", "14"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_SearchRecipeByCriteria_successfully() throws Exception {
        //create ingredient for recipe
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient("Salt");
        ingredient.setIngredientQuantity("6");
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        List<Integer> savedIngredientList=new ArrayList<>(savedIngredient.getIngredientId());
        //create the recipe
        RecipeRequest createRecipeRequest = new RecipeRequest("Pasta",
                "OTHER", 5, savedIngredientList, "Chop the onion and tomato, stir and fry for few minutes, then add the pasta with water and spices stir and boil, ready to serve");

        MvcResult createdRecipe = performPost("/api/v1/recipes", createRecipeRequest)
                .andExpect(status().isCreated())
                .andReturn();

        //prepare the search criteria and by newly created id
        Integer recipeId = readByJsonPath(createdRecipe, "$.recipeId");

        SearchRecipeRequest request = new SearchRecipeRequest();
        List<SearchCriteriaRequest> searchCriteriaList = new ArrayList<>();
        SearchCriteriaRequest searchCriteria = new SearchCriteriaRequest("name",
                "Pasta",
                "cn");
        searchCriteriaList.add(searchCriteria);
        
        request.setFilterOption("ALL");
        request.setSearchRequests(searchCriteriaList);
        MvcResult result = performPost("/api/v1/recipes/search", request)
                .andExpect(status().isOk())
                .andReturn();
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId);
        List<RecipesResponse> listRecipeList = getListFromMvcResult(result, RecipesResponse.class);
        Assert.assertTrue(optionalRecipe.isPresent());
        Assert.assertEquals(listRecipeList.get(0).getRecipeName(), optionalRecipe.get().getName());
        Assert.assertEquals(listRecipeList.get(0).getRecipeInstructions(), optionalRecipe.get().getRecipeInstructions());
        Assert.assertEquals(listRecipeList.get(0).getServingCapacity(), optionalRecipe.get().getServingCapacity());
    
        }

    @Test
    public void test_SearchRecipeByCriteria_fails() throws Exception {
        SearchRecipeRequest request = new SearchRecipeRequest();
        performPost("/api/v1/recipes/search", request)
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.message").exists())
                .andReturn();
    }

}
