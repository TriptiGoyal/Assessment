package com.abnamro.recipe.services;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abnamro.recipe.constants.MessageConstants;
import com.abnamro.recipe.exceptions.DataNotFoundException;
import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.repositories.IngredientRepository;
import com.abnamro.recipe.request.IngredientRequest;

@Service
@Transactional
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    public IngredientServiceImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    
    public List<Ingredient> fetchIngredients() {
        return ingredientRepository.findAll();
   }

    public Integer addIngredient(IngredientRequest ingredientRequest) {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredient(ingredientRequest.getIngredientName());
        ingredient.setIngredientQuantity(ingredientRequest.getIngredientQuantity());
        Ingredient addedIngredient = ingredientRepository.save(ingredient);
        return addedIngredient.getIngredientId();
    }

    public Set<Ingredient> getIngredientsByIds(List<Integer> ingredientIds) {
        return ingredientIds.stream()
                .map(this::findById)
                .collect(Collectors.toSet());
    }

    public Ingredient findById(int id) {
        return ingredientRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(MessageConstants.INGREDIENT_NOT_FOUND));
    }

    public void deleteIngredient(int id) {
        if (!ingredientRepository.existsById(id)) {
            throw new DataNotFoundException(MessageConstants.INGREDIENT_NOT_FOUND);
        }
        ingredientRepository.deleteById(id);
    }


}
