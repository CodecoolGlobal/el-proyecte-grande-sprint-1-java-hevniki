package com.codecool.cookpad.dto;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record RecipeDTO(
        Long id,
        Set<IngredientForRecipeDTO> ingredients,
        String name,
        String description,
        boolean isVegan,
        boolean isVegetarian,
        boolean isGlutenFree,
        boolean isDairyFree) {
}
