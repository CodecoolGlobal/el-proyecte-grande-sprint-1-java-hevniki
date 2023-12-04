package com.codecool.cookpad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientForRecipeDTO(
        IngredientTypeDTO ingredient,
        double amount) {
}
