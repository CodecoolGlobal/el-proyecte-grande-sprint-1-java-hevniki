package com.codecool.cookpad.dto;

import java.util.Set;

public record RecipeDTO(
        Set<IngredientForRecipeDTO> ingredients,
        String name,
        String description,
        boolean isVegan,
        boolean isVegetarian,
        boolean isGlutenFree,
        boolean isDairyFree) {
}
