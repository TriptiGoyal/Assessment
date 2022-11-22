package com.abnamro.recipe.request;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.abnamro.recipe.constants.MessageConstants;

public class BasicRequest {

    @NotNull(message = MessageConstants.NULL_ID)
    @Positive(message = MessageConstants.NEGATIVE_ID)
    @ApiModelProperty(notes = "Id of Recipe", example = "1")
    private Integer recipeId;

    public Integer getRecipeId() {
        return recipeId;
    }

    public BasicRequest() {
    }

    public BasicRequest(Integer recipeId) {
        this.recipeId = recipeId;
    }
}
