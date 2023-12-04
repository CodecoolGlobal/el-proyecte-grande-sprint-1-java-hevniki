package com.codecool.cookpad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientForRecipeDTO(
        Long id,
        IngredientTypeDTO ingredient,
        double amount) {
}
