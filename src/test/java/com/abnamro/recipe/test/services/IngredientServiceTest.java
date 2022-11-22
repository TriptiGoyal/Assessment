package com.abnamro.recipe.test.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.abnamro.recipe.constants.MessageConstants;
import com.abnamro.recipe.exceptions.DataNotFoundException;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.repositories.IngredientRepository;
import com.abnamro.recipe.request.IngredientRequest;
import com.abnamro.recipe.services.IngredientServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class IngredientServiceTest {
	@Mock
	private IngredientRepository ingredientRepository;

	@Mock
	private MessageConstants messageConstants;

	@InjectMocks
	private IngredientServiceImpl ingredientService;

	@Test
	public void test_addIngredient_success() {
		Ingredient ingredient = new Ingredient();
		ingredient.setIngredientId(7);
		ingredient.setIngredient("Pasta");
		ingredient.setIngredientQuantity("2 KG");
		IngredientRequest ingredientRequest = new IngredientRequest();
		ingredientRequest.setIngredientName("Pasta");
		ingredientRequest.setIngredientQuantity("2KG");
		when(ingredientRepository.save(any(Ingredient.class))).thenReturn(ingredient);
		Integer ingredient1 = ingredientService.addIngredient(ingredientRequest);
		assertThat(ingredient1).isSameAs(ingredient.getIngredientId());
	}

	@Test(expected = DataNotFoundException.class)
	public void test_deleteIngredient_failure() {
		when(ingredientRepository.existsById(anyInt())).thenReturn(false);
		ingredientService.deleteIngredient(1);
	}

	@Test
	public void test_deleteIngredient_success() {
		when(ingredientRepository.existsById(anyInt())).thenReturn(true);
		doNothing().when(ingredientRepository).deleteById(anyInt());
		ingredientService.deleteIngredient(5);
	}

}