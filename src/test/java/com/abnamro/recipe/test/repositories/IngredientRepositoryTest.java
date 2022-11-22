package com.abnamro.recipe.test.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import com.abnamro.recipe.models.Ingredient;
import com.abnamro.recipe.repositories.IngredientRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class IngredientRepositoryTest {
    @Autowired
    private IngredientRepository ingredientRepository;

    @Test
    public void test_addIngredientSuccess() {
        Ingredient entity = new Ingredient();
        entity.setIngredient("Maggi");
        entity.setIngredientQuantity("5");
        Ingredient savedIngredient = ingredientRepository.save(entity);
        assertNotNull(savedIngredient);
        assertEquals("Maggi", savedIngredient.getIngredient());
        assertNotNull(savedIngredient.getIngredientId());
    }

    
    @Test(expected = DataIntegrityViolationException.class)
    public void test_adddingSameIngredientTwice_Fails() {
        Ingredient entity1 = new Ingredient();
        entity1.setIngredient("Tomato");
        entity1.setIngredientQuantity("9");

        Ingredient entity2 = new Ingredient();
        entity2.setIngredient("Tomato");
        entity2.setIngredientQuantity("9");

        ingredientRepository.save(entity1);
        ingredientRepository.save(entity2);

    }
    
    
    @Test
    public void test_fetchIngredientSuccess() {
        Ingredient entity1 = new Ingredient();
        entity1.setIngredient("Grapes");
        entity1.setIngredientQuantity("5");

        Ingredient entity2 = new Ingredient();
        entity2.setIngredient("Cucumber");
        entity2.setIngredientQuantity("8");

        Ingredient firstSavedEntity = ingredientRepository.save(entity1);
        Ingredient secondSavedEntity = ingredientRepository.save(entity2);
        assertNotNull(firstSavedEntity);
        assertNotNull(secondSavedEntity);

        assertFalse(ingredientRepository.findAll().isEmpty());
        assertEquals(8, ingredientRepository.findAll().size());
    }


}