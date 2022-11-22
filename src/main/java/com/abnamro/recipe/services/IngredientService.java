package com.abnamro.recipe.services;

import java.util.List;
import java.util.Set;

import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.request.IngredientRequest;

public interface IngredientService {

	public Set<Ingredient> getIngredientsByIds(List<Integer> ingredientIds);
	
    public Ingredient findById(int ingredientId);
    
    public List<Ingredient> fetchIngredients();
    
    public void deleteIngredient(int ingredientId);
    
	public Integer addIngredient(IngredientRequest request);

}
