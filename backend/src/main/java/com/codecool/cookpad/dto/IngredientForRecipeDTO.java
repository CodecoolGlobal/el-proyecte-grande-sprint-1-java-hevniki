package com.codecool.cookpad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientForRecipeDTO(
        String ingredient,
        String amount) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientForRecipeDTO that = (IngredientForRecipeDTO) o;
        return Objects.equals(ingredient, that.ingredient) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredient, amount);
    }
}

