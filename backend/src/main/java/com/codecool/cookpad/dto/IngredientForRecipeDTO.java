package com.codecool.cookpad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientForRecipeDTO(
        Long id,
        IngredientTypeDTO ingredient,
        double amount) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientForRecipeDTO that = (IngredientForRecipeDTO) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(id, that.id) && Objects.equals(ingredient, that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredient, amount);
    }
}

