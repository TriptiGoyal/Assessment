package com.abnamro.recipe.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.abnamro.recipe.constants.MessageConstants;
import com.abnamro.recipe.exceptions.DataNotFoundException;
import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.repositories.RecipeRepository;
import com.abnamro.recipe.request.RecipeRequest;
import com.abnamro.recipe.request.SearchRecipeRequest;
import com.abnamro.recipe.request.UpdateRequest;
import com.abnamro.recipe.services.IngredientServiceImpl;
import com.abnamro.recipe.services.RecipeServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class RecipeServiceTest {
    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientServiceImpl ingredientService;

    @Mock
    private MessageConstants messageConstants;

    @InjectMocks
    private RecipeServiceImpl recipeService;

    @Test
    public void test_addRecipe_successfully() {
        RecipeRequest request = new RecipeRequest("pizza", "OTHER", 4, null, "Take a pizza base, add the veggie and cheese, bake");
        Recipe response = new Recipe();
        response.setName("pizzat");
        response.setRecipeInstructions("Take a pizza base, add the veggie and cheese, bake");
        response.setServingCapacity(4);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(response);
        Recipe id = recipeService.addRecipe(request);
        assertThat(id.getRecipeId()).isSameAs(response.getRecipeId());
    }

    @Test
    public void test_updateRecipe_successfully() {
        Recipe response = new Recipe();
        response.setName("Name");
        response.setRecipeType("OTHER");
        response.setServingCapacity(4);
        response.setRecipeId(5);
        UpdateRequest request = new UpdateRequest(9,"pizza", "OTHER", 7, null, "Chop the onions and potatoes, deep fry");
        when(recipeRepository.save(any(Recipe.class))).thenReturn(response);
        when(recipeRepository.findById(anyInt())).thenReturn(Optional.of(response));
        recipeService.updateRecipe(request);
    }

    @Test(expected = DataNotFoundException.class)
    public void test_updateRecipe_notFound() {
        UpdateRequest request = new UpdateRequest(10,"macroni", "Vegetarian", 4, null, "add instructions");
        when(recipeRepository.findById(anyInt())).thenReturn(Optional.empty());
        recipeService.updateRecipe(request);
    }

    @Test
    public void test_deleteRecipe_successfully() {
        when(recipeRepository.existsById(anyInt())).thenReturn(true);
        doNothing().when(recipeRepository).deleteById(anyInt());
        recipeService.deleteRecipe(1);
    }

    @Test(expected = DataNotFoundException.class)
    public void test_deleteRecipe_notFound() {
        when(recipeRepository.existsById(anyInt())).thenReturn(false);

        recipeService.deleteRecipe(1);
    }

    @Test(expected = DataNotFoundException.class)
    public void test_findBySearchCriteria_notFound() {
        SearchRecipeRequest recipeSearchRequest = mock(SearchRecipeRequest.class);
        recipeService.searchRecipeByCriteria(recipeSearchRequest);
    }

}