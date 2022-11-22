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
public class UpdateRequest extends BasicRequest{
	
	public UpdateRequest(){
		
	}
	
    @NotBlank(message = MessageConstants.BLANK_RECIPE_NAME)
    @Size(max = ValidationConfig.MAXIMUM_LENGTH_NAME, message = MessageConstants.RECIPE_MAX_INSTRUCTIONS_SIZE)
    @Pattern(regexp = ValidationConfig.RECIPE_INGREDIENT_NAME_PATTERN, message = MessageConstants.RECIPE_NAME_PATTERN)
    @ApiModelProperty(notes = "Name of the ingredient", example = "Potato")
    private String name;

    @Pattern(regexp = "vegetarian|non-vegetarian|other", flags = Pattern.Flag.CASE_INSENSITIVE)
    @ApiModelProperty(notes = "Type of recipe", example = "VEGETARIAN")
    private String recipeType;

    @NotNull(message = MessageConstants.NULL_ID)
    @Positive(message = MessageConstants.NEGATIVE_ID)
    @ApiModelProperty(notes = "Serving Capacity", example = "7")
    private int servingCapacity;

    @ApiModelProperty(notes = "List of ids for the ingredients to be updated", example = "[3,4]")
    private List<Integer> ingredientIds;

    @NotBlank(message = MessageConstants.INGREDIENT_NOT_BLANK)
    @Size(max = ValidationConfig.MAXIMUM_LENGTH_DEFAULT, message = MessageConstants.INGREDIENT_MAX_SIZE)
    @Pattern(regexp = ValidationConfig.TEXT_FREE_PATTERN, message = MessageConstants.VALID_INSTRUCTION_PATTERN)
    @ApiModelProperty(notes = "The instructions to update the recipe", example = "Chop the potatoes and deep fry, Ready to serve !!")

    private String recipeInstructions;

    public UpdateRequest(Integer recipeId, String recipeName, String recipeType, int servingCapacity, List<Integer> ingredientIds, String recipeInstructions) {
        super(recipeId);
        this.name = recipeName;
        this.recipeType = recipeType;
        this.servingCapacity = servingCapacity;
        this.ingredientIds = ingredientIds;
        this.recipeInstructions = recipeInstructions;
    }

}
