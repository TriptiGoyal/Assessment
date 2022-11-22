package com.abnamro.recipe.test.controllers;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.abnamro.recipe.controllers.IngredientController;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.request.IngredientRequest;
import com.abnamro.recipe.response.AddIngredientResponse;
import com.abnamro.recipe.response.IngredientsResponse;
import com.abnamro.recipe.services.IngredientService;


@RunWith(MockitoJUnitRunner.class)
public class IngredientControllerTest {
	@InjectMocks
    private IngredientController ingredientController;
	
    @Mock
    private IngredientService ingredientService;


    @Test
    public void test_addIngredient_success() {
        when(ingredientService.addIngredient(any(IngredientRequest.class))).thenReturn(7);
        IngredientRequest ingredientRequest = new IngredientRequest();
        ingredientRequest.setIngredientName("Pasta");
        ingredientRequest.setIngredientQuantity("2KG");
        ResponseEntity<AddIngredientResponse> response = ingredientController.addIngredient(ingredientRequest);
        assertThat(response).isNotNull();
        assertThat(response.getBody().getIngredientId()).isSameAs(7);
    }

    
    @Test
    public void test_getIngredients_successfully() {
        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setIngredientId(8);
        ingredient1.setIngredient("Maggi");
        ingredient1.setIngredientQuantity("5 Packet");
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setIngredientId(7);
        ingredient2.setIngredient("Macroni");
        ingredient2.setIngredientQuantity("2 KG");
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        when(ingredientService.fetchIngredients()).thenReturn(ingredientList);
        ResponseEntity<List<IngredientsResponse>> listofIngredients = ingredientController.getIngredients();
        assertThat(ingredientList.size()).isSameAs(listofIngredients.getBody().size());
        assertThat(ingredientList.get(0).getIngredientId()).isSameAs(listofIngredients.getBody().get(0).getIngredientId());
        assertThat(ingredientList.get(1).getIngredientId()).isSameAs(listofIngredients.getBody().get(1).getIngredientId());
    }

	@Test
    public void test_deleteIngredient_success() {
        doNothing().when(ingredientService).deleteIngredient(anyInt());
        ingredientController.deleteIngredient(6);
    }

    @Test
    public void test_getIngredientById_successfully() {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(7);
        ingredient.setIngredient("Pasta");
        ingredient.setIngredientQuantity("2 KG");
        when(ingredientService.findById(anyInt())).thenReturn(ingredient);
        ResponseEntity<IngredientsResponse> response = ingredientController.getIngredientById(7);
        assertThat(response.getBody().getIngredientId()).isSameAs(7);
    }


}