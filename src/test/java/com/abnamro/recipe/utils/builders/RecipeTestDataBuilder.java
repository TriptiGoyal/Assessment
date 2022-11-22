package com.abnamro.recipe.utils.builders;

import com.abnamro.recipe.models.Recipe;

public class RecipeTestDataBuilder {
    public static Recipe createRecipe() {
        return createRecipe(null);
    }

    public static Recipe createRecipe(Integer id) {
        Recipe recipe = new Recipe();
        recipe.setRecipeId(id);
        recipe.setName("Red Sauce Pasta");
        recipe.setServingCapacity(5);
        recipe.setRecipeType("OTHER");
        recipe.setRecipeInstructions("someInstruction");

        return recipe;
    }
}
