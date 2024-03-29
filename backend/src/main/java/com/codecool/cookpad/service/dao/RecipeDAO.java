package com.codecool.cookpad.service.dao;
import com.codecool.cookpad.model.entity.Recipe;

import java.util.Optional;
import java.util.Set;

public interface RecipeDAO {
    Set<Recipe> getRecipes();
    Optional<Recipe> getRecipeById(String id);
    boolean deleteRecipe(Recipe recipeToDelete);
    boolean createRecipe(Recipe recipeToAdd);
}
