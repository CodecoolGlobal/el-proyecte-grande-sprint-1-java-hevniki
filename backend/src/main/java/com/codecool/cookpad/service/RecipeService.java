package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.model.Ingredient;
import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.service.dao.RecipeDAO;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeDAO recipeDAO;

    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;
    }

    public Set<RecipeDTO> getRecipes() {
        return recipeDAO.getRecipes().stream().map(this::mapToDTO).collect(Collectors.toSet());
    }

    public RecipeDTO getRecipeById(String id) {
        Optional<Recipe> optionalRecipe = recipeDAO.getRecipeById(id);
        return optionalRecipe.map(this::mapToDTO).orElse(null);
    }

    private RecipeDTO mapToDTO(Recipe recipe) {
        return new RecipeDTO(recipe.getId().toString(), mapIngredientsToStrings(recipe.getIngredients()), recipe.getName(), recipe.getDescription(), recipe.isVegan(), recipe.isVegetarian(), recipe.isGlutenFree(), recipe.isDairyFree());
    }

    private Set<String[]> mapIngredientsToStrings(Map<Ingredient, Double> ingredients) {
        return ingredients.entrySet().stream()
                .map(ingredient -> new String[]{ingredient.getKey().getId().toString(), ingredient.getKey().getName(), ingredient.getValue().toString(), ingredient.getKey().getUnitOfMeasure()})
                .collect(Collectors.toSet());
    }
    public boolean deleteRecipe(RecipeDTO recipeToDelete) {
        Optional<Recipe> optionalRecipe = recipeDAO.getRecipeById(recipeToDelete.id());
        return optionalRecipe.filter(recipeDAO::deleteRecipe).isPresent();
    }
}
