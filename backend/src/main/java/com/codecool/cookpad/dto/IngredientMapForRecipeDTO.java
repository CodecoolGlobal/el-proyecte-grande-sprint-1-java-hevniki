package com.codecool.cookpad.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientMapForRecipeDTO(String id, double amount, String name, String unitOfMeasure){}
