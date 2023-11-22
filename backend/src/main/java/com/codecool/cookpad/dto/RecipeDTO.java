package com.codecool.cookpad.dto;

import com.codecool.cookpad.model.Ingredient;

import java.util.Map;
import java.util.UUID;

public record RecipeDTO (UUID id, Map<Ingredient, Double>ingredients, String name, String description){
}
