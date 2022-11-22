package com.abnamro.recipe.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddIngredientResponse {

    private int ingredientId;
  
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AddIngredientResponse that = (AddIngredientResponse) o;

        return ingredientId == that.ingredientId;
    }

    @Override
    public int hashCode() {
        return ingredientId;
    }
}
