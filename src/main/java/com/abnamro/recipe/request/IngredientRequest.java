package com.abnamro.recipe.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.abnamro.recipe.config.ValidationConfig;
import com.abnamro.recipe.constants.MessageConstants;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {
	
    @NotBlank(message = MessageConstants.INGREDIENT_NOT_FOUND)
    @Size(max = ValidationConfig.MAXIMUM_LENGTH_NAME, message = MessageConstants.INGREDIENT_MAX_SIZE)
    @Pattern(regexp = ValidationConfig.RECIPE_INGREDIENT_NAME_PATTERN, message = MessageConstants.INGREDIENT_PATTERN)
    @ApiModelProperty(notes = "Name of ingredient", example = "Maggi")
    private String ingredientName;
    
    @NotBlank(message = MessageConstants.INGREDIENT_QUANTITY_IS_BLANK)
    @Size(max = ValidationConfig.MAXIMUM_LENGTH_NAME, message = MessageConstants.INGREDIENT_MAX_SIZE)
    @Pattern(regexp = ValidationConfig.TEXT_FREE_PATTERN, message = MessageConstants.INGREDIENT_PATTERN)
    @ApiModelProperty(notes = "Quantity of ingredient", example = "1 Kg")
    private String ingredientQuantity;

}
