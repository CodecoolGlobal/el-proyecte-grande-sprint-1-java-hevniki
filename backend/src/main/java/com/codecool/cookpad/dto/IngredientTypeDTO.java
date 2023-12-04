package com.codecool.cookpad.dto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientTypeDTO(
        Long id,
        String name,
        String unitOfMeasure,
        boolean isGlutenFree,
        boolean isDairyFree,
        boolean isMeatFree,
        boolean isEggFree) {
}

