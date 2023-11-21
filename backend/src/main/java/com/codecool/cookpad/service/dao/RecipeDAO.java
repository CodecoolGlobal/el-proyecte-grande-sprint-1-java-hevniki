package com.codecool.cookpad.service.dao;
import com.codecool.cookpad.model.Recipe;

import java.util.Set;
import java.util.UUID;

public interface RecipeDAO {
    Set<Recipe> getRecipes();
    //boolean addRecipe(Recipe recipe);
    //Recipe getRecipe(UUID id);
}
