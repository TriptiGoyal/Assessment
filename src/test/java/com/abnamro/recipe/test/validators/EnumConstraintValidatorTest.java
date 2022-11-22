package com.abnamro.recipe.test.validators;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.junit.BeforeClass;
import org.junit.Test;

import com.abnamro.recipe.constants.MessageConstants;
import com.abnamro.recipe.request.IngredientRequest;

public class EnumConstraintValidatorTest {
    private static Validator validator;

    @BeforeClass
    public static void test_setupValidator() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    public void test_whenNotBlankName_thenNoConstraintViolations() {
        IngredientRequest request = new IngredientRequest("pasta", "5 kg");
        Set<ConstraintViolation<IngredientRequest>> violations = validator.validate(request);
        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void test_whenEmptyName() {
        IngredientRequest request = new IngredientRequest();
        request.setIngredientName(null);
        request.setIngredientQuantity("1");
        Set<ConstraintViolation<IngredientRequest>> violations = validator.validate(request);
        String resultViolation = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        assertThat(violations.size()).isEqualTo(1);
        assertEquals(MessageConstants.INGREDIENT_NOT_FOUND,resultViolation);
    }

    @Test
    public void test_whenIngredientNameDoesNotFitPattern() {
        IngredientRequest request = new IngredientRequest();
        request.setIngredientName("-.1!@$!#@");
        request.setIngredientQuantity("1");
        Set<ConstraintViolation<IngredientRequest>> violations = validator.validate(request);
        String resultViolation = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        assertEquals(MessageConstants.INGREDIENT_PATTERN, resultViolation);
        assertThat(violations.size()).isEqualTo(1);
    }
    

    @Test
    public void test_whenBlankName_ConstraintViolation() {
        IngredientRequest request = new IngredientRequest();
        request.setIngredientName(null);
        request.setIngredientQuantity("1");
        Set<ConstraintViolation<IngredientRequest>> violations = validator.validate(request);
        String resultViolation = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        assertThat(violations.size()).isEqualTo(1);
        assertEquals(MessageConstants.INGREDIENT_NOT_FOUND,resultViolation);
    }
}
