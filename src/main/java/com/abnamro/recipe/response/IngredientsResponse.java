package com.abnamro.recipe.response;

import java.time.LocalDateTime;
import java.util.Objects;

import com.abnamro.recipe.models.Ingredient;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IngredientsResponse {
    @ApiModelProperty(notes = "Ingredient ID", example = "1")
    private int ingredientId;
    
    @ApiModelProperty(notes = "Ingredient Name", example = "Momos")
    private String ingredient;

    @ApiModelProperty(notes = "Ingredient Quantity", example = "Momos")
    private String ingredientQuantity;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime createdDateTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime updatedDateTime;

    public IngredientsResponse(Ingredient ingredient) {
        this.ingredientId = ingredient.getIngredientId();
        this.ingredient = ingredient.getIngredient();
        this.ingredientQuantity = ingredient.getIngredientQuantity();
        this.createdDateTime = ingredient.getCreatedDateTime();
        this.updatedDateTime = ingredient.getUpdatedDateTime();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientsResponse that = (IngredientsResponse) o;
        return ingredientId == that.ingredientId && Objects.equals(ingredient, that.ingredient)  && Objects.equals(ingredientQuantity, that.ingredientQuantity) && Objects.equals(createdDateTime, that.createdDateTime) && Objects.equals(updatedDateTime, that.updatedDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientId, ingredient,ingredientQuantity, createdDateTime, updatedDateTime);
    }
}
