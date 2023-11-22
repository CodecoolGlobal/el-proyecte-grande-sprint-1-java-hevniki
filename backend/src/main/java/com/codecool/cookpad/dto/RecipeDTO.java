package com.codecool.cookpad.dto;

import com.codecool.cookpad.model.Ingredient;

import java.util.Map;
import java.util.Set;

public record RecipeDTO (String id, Set<String[]> ingredients, String name, String description, boolean isVegan, boolean isVegetarian, boolean isGlutenFree, boolean isDairyFree){
}
