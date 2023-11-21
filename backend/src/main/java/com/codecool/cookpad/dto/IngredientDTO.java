package com.codecool.cookpad.dto;

import java.util.UUID;

public record IngredientDTO(UUID id, String name, String unitOfMeasure,boolean isGlutenFree, boolean isDairyFree, boolean isMeatFree, boolean isEggFree) {
}
