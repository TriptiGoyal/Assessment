package com.abnamro.recipe.integration.test.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.repositories.IngredientRepository;
import com.abnamro.recipe.request.IngredientRequest;
import com.abnamro.recipe.response.IngredientsResponse;

public class IngredientControllerIntegrationTest extends MvcControllerIntegrationTest {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    protected MockMvc mockMvc;


    @Test
    public void test_addIngredient_success() throws Exception {
        IngredientRequest ingredientRequest = new IngredientRequest();
        ingredientRequest.setIngredientName("EGG");
        ingredientRequest.setIngredientQuantity("2 Dozen");
        MvcResult result = performPost("/api/v1/ingredients", ingredientRequest)
                .andExpect(status().isCreated())
                .andReturn();
        Integer ingredientId = readByJsonPath(result, "$.ingredientId");
        Optional<Ingredient> ingredient = ingredientRepository.findById(ingredientId);
        assertTrue(ingredient.isPresent());
        assertEquals(ingredientRequest.getIngredientName(),ingredient.get().getIngredient());
    }

    @Test
    public void test_getIngredient_success() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient("Maggi Sauce");
        ingredient.setIngredientQuantity("2 Dozen");
        Ingredient storedIngredient = ingredientRepository.save(ingredient);
        System.out.println(storedIngredient.getIngredientId());
        performGet("/api/v1/ingredients/")
                .andExpect(status().isOk());
      }

    @Test
    public void test_getIngredient_notFound() throws Exception {
        performGet("/api/v1/ingredients/9")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_getIngredients_success() throws Exception {
        List<Ingredient> ingredientList = new ArrayList<>();
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setIngredientId(7);
        ingredient1.setIngredient("Maggi");
        ingredient1.setIngredientQuantity("5 Packet");
        ingredient1.setCreatedDateTime(LocalDateTime.now());
        ingredient1.setUpdatedDateTime(LocalDateTime.now());
        
        Ingredient ingredient2 = new Ingredient();
        ingredient2.setIngredientId(8);
        ingredient2.setIngredient("Macroni");
        ingredient2.setIngredientQuantity("2 KG");
        ingredient2.setCreatedDateTime(LocalDateTime.now());
        ingredient2.setUpdatedDateTime(LocalDateTime.now());
        ingredientList.add(ingredient1);
        ingredientList.add(ingredient2);
        ingredientRepository.saveAll(ingredientList);
        MvcResult performGetResult = performGet("/api/v1/ingredients")
                .andExpect(status().isOk())
                .andReturn();
        List<IngredientsResponse> response = getListFromMvcResult(performGetResult, IngredientsResponse.class);
        assertEquals(8, response.size());
    }

    @Test
    public void test_deleteIngredients_success() throws Exception {
        Ingredient ingredient1 = new Ingredient();
        ingredient1.setIngredientId(9);
        ingredient1.setIngredient("Oranges");
        ingredient1.setIngredientQuantity("5 KG");
        Ingredient savedIngredient = ingredientRepository.save(ingredient1);
        performDelete("/api/v1/ingredients?ingredientId=" + savedIngredient.getIngredientId())
                .andExpect(status().isOk());
        Optional<Ingredient> deletedIngredient = ingredientRepository.findById(savedIngredient.getIngredientId());
        assertTrue(!deletedIngredient.isPresent());
    }

    @Test
    public void test_getIngredientById_success() throws Exception {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientId(9);
        ingredient.setIngredient("Pasta Sauces");
        ingredient.setIngredientQuantity("2 KG");
        ingredient.setCreatedDateTime(LocalDateTime.now());
        ingredient.setUpdatedDateTime(LocalDateTime.now());
        Ingredient savedIngredient = ingredientRepository.save(ingredient);
        MvcResult performGetResult = performGet("/api/v1/ingredients/" + savedIngredient.getIngredientId())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ingredientId").value(savedIngredient.getIngredientId()))
                .andExpect(jsonPath("$.ingredient").value(savedIngredient.getIngredient()))
                .andExpect(jsonPath("$.ingredientQuantity").value(savedIngredient.getIngredientQuantity()))
                .andReturn();
        IngredientsResponse response = getFromMvcResult(performGetResult, IngredientsResponse.class);
        assertEquals(savedIngredient.getIngredient(), response.getIngredient());
    }

    @Test
    public void test_findIngredientById_notSuccess() throws Exception {

        performGet("/api/v1/ingredients/" + 12)
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    public void test_deleteIngredient_notFound() throws Exception {
        performDelete("/api/v1/ingredients?ingredientId=7")
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").exists());
    }
}
