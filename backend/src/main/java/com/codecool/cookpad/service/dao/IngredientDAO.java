package com.codecool.cookpad.service.dao;

import com.codecool.cookpad.model.Ingredient;

import java.util.List;
import java.util.Optional;

public interface IngredientDAO {
    List<Ingredient> getAllIngredients();
    Optional<Ingredient> getIngredientById(String id);
    boolean addIngredient(Ingredient ingredient);
    boolean deleteIngredient(Ingredient ingredientToDelete);
}
