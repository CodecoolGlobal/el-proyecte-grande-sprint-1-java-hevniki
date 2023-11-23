package com.codecool.cookpad.dto;

import com.codecool.cookpad.model.Ingredient;

import java.util.List;
import java.util.Map;
import java.util.Set;

public record RecipeDTO(String id, Set<IngredientMapForRecipeDTO> ingredients, String name, String description, boolean isVegan, boolean isVegetarian, boolean isGlutenFree, boolean isDairyFree){
}
