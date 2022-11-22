package com.abnamro.recipe.response;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.abnamro.recipe.models.Recipe;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipesResponse {
    @ApiModelProperty(notes = "Recipe Id of Response", example = "1")
    private int recipeId;

    @ApiModelProperty(notes = "Recipe Name of Response", example = "Pasta")
    private String recipeName;

    @ApiModelProperty(notes = "Recipe Type of Response", example = "VEGETARIAN")
    private String recipeType;

    @ApiModelProperty(notes = "Serving Capacity", example = "1")
    private int servingCapacity;

    @JsonIgnoreProperties("ingredients")
    private Set<IngredientsResponse> ingredients;

    @ApiModelProperty(notes = "Recipe Instructions", example = "Chop the onion, add to pasta and boil it")
    private String recipeInstructions;

    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDateTime;

    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedDateTime;
  
    public RecipesResponse(Recipe recipe) {
        this.recipeId = recipe.getRecipeId();
        this.recipeName = recipe.getName();
        this.recipeType = recipe.getRecipeType();
        this.recipeInstructions = recipe.getRecipeInstructions();
        this.createdDateTime = recipe.getCreatedDateTime();
        this.updatedDateTime = recipe.getUpdatedDateTime();
        this.servingCapacity = recipe.getServingCapacity();

        this.ingredients = recipe.getRecipeIngredients() != null ?
                recipe.getRecipeIngredients().stream()
                        .map(IngredientsResponse::new)
                        .collect(Collectors.toSet())
                : null;
    }

  }
