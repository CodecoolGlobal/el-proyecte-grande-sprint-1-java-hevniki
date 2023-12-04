package com.codecool.cookpad.dto;

public record IngredientTypeDTO(
        String name,
        String unitOfMeasure,
        boolean isGlutenFree,
        boolean isDairyFree,
        boolean isMeatFree,
        boolean isEggFree) {
}

