package com.codecool.cookpad.dto;

import com.codecool.cookpad.model.Ingredient;

import java.util.Map;

public record RecipeDTO (String id, Map<Ingredient, Double>ingredients, String name, String description, boolean isVegan, boolean isVegetarian, boolean isGlutenFree, boolean isDairyFree){
}
