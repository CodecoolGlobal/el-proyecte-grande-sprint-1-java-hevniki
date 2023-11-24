package com.codecool.cookpad.dto;


public record IngredientDTO(String id, String name, String unitOfMeasure,boolean isGlutenFree, boolean isDairyFree, boolean isMeatFree, boolean isEggFree) {
}
