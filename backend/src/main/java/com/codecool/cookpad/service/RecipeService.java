package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.service.dao.RecipeDAO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class RecipeService {
    private final RecipeDAO recipeDAO;

    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public Set<Recipe> getRecipes() {
        return recipeDAO.getRecipes();
    }

    public RecipeDTO getRecipeById(String id){
        Optional<Recipe> optionalRecipe = recipeDAO.getRecipeById(id);
        return optionalRecipe.map(this::mapToDTO).orElse(null);
    }

    private RecipeDTO mapToDTO(Recipe recipe){
        return new RecipeDTO(recipe.getId().toString(), recipe.getIngredients(), recipe.getName(), recipe.getDescription(), recipe.isVegan(), recipe.isVegetarian(), recipe.isGlutenFree(), recipe.isDairyFree());
    }
}
