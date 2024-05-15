package com.codecool.cookpad.dto;

import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
public record RecipeDTO(
        Long id,
        Set<IngredientForRecipeDTO> ingredients,
        String name,
        byte[] picture,
        String description,
        boolean isVegan,
        boolean isVegetarian,
        boolean isGlutenFree,
        boolean isDairyFree) {
}
