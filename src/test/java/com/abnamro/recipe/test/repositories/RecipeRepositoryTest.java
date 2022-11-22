package com.abnamro.recipe.test.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.abnamro.recipe.models.Recipe;
import com.abnamro.recipe.repositories.RecipeRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    public void test_saveRecipe_Success() {
        Recipe entity = new Recipe();
        entity.setRecipeType("Other");
        entity.setRecipeInstructions("some instructions");
        entity.setName("pasta");
        Recipe savedRecipe = recipeRepository.save(entity);
        assertNotNull(savedRecipe);

        assertEquals("Other", savedRecipe.getRecipeType());
        assertNotNull(savedRecipe.getRecipeId());
    }

    @Test
    public void test_fetchRecipe_Success() {
        Recipe entity1 = new Recipe();
        entity1.setRecipeType("Other");
        entity1.setName("Non-Veg biryani");

        Recipe entity2 = new Recipe();
        entity2.setRecipeType("Other");
        entity2.setName("pizza");


        Recipe firstSavedEntity = recipeRepository.save(entity1);
        Recipe secondSavedEntity = recipeRepository.save(entity2);
        assertNotNull(firstSavedEntity);
        assertNotNull(secondSavedEntity);

        assertFalse(recipeRepository.findAll().isEmpty());
        assertEquals(6, recipeRepository.findAll().size());
    }
}