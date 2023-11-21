package com.codecool.cookpad.service;

import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.service.dao.RecipeDAO;
import org.springframework.stereotype.Service;

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

}
