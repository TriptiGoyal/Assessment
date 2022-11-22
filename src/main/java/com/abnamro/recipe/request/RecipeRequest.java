package com.abnamro.recipe.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.abnamro.recipe.config.ValidationConfig;
import com.abnamro.recipe.constants.MessageConstants;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecipeRequest {
    @NotBlank(message = MessageConstants.BLANK_RECIPE_NAME)
    @Size(max = ValidationConfig.MAXIMUM_LENGTH_NAME, message = MessageConstants.MAX_RECIPE_NAME)
    @Pattern(regexp = ValidationConfig.RECIPE_INGREDIENT_NAME_PATTERN, message = MessageConstants.RECIPE_NAME_PATTERN)
    @ApiModelProperty(notes = "Recipe Name", example = "Mix Vegetable")
    private String name;

    @ApiModelProperty(notes = "Recipe Type", example = "Vegetarian")
    @Pattern(regexp = "vegetarian|non-vegetarian|other", flags = Pattern.Flag.CASE_INSENSITIVE)
    private String recipeType;

    @NotNull(message = MessageConstants.RECIPE_TYPE_INVALID)
    @Positive(message = MessageConstants.RECIPE_TYPE_INVALID)
    @ApiModelProperty(notes = "Serving Capacity", example = "4")
    private int servingCapacity;

    @ApiModelProperty(notes = "IngredientIds of the ingredients to make the recipe", example = "[1,2]")
    private List<Integer> ingredientIds;

    @NotBlank(message = MessageConstants.BLANK_RECIPE_NAME)
    @Size(max = ValidationConfig.MAXIMUM_LENGTH_DEFAULT, message = MessageConstants.RECIPE_MAX_INSTRUCTIONS_SIZE)
    @Pattern(regexp = ValidationConfig.RECIPE_INGREDIENT_NAME_PATTERN, message = MessageConstants.VALID_INSTRUCTION_PATTERN)
    @ApiModelProperty(notes = "Recipe Instructions", example = "Chop the onion, potato and tomato, stir and fry, ready to serve")
    private String recipeInstructions;

 
}
