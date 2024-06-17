package com.codecool.cookpad.dto;
import com.codecool.cookpad.model.IngredientCategory;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record IngredientTypeDTO(
        Long id,
        String name,
        IngredientCategory category,
        boolean approved
        ) {
}

