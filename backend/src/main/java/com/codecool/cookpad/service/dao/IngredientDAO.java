package com.codecool.cookpad.service.dao;

import com.codecool.cookpad.model.entity.IngredientType;

import java.util.List;
import java.util.Optional;

public interface IngredientDAO {
    List<IngredientType> getAllIngredients();
    Optional<IngredientType> getIngredientById(String id);
    boolean addIngredient(IngredientType ingredientType);
    boolean deleteIngredient(IngredientType ingredientTypeToDelete);
}
