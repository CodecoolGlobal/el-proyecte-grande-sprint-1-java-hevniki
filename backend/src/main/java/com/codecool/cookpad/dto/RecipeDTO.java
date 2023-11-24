package com.codecool.cookpad.dto;

import java.util.Set;

public record RecipeDTO(String id, Set<IngredientMapForRecipeDTO> ingredients, String name, String description, boolean isVegan, boolean isVegetarian, boolean isGlutenFree, boolean isDairyFree){
}
