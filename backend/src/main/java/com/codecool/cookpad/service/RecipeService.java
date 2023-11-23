package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientMapForRecipeDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.model.Ingredient;
import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.service.dao.RecipeDAO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeDAO recipeDAO;
    private final IngredientService ingredientService;

    public RecipeService(RecipeDAO recipeDAO, IngredientService ingredientService) {
        this.recipeDAO = recipeDAO;
        this.ingredientService = ingredientService;
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

    private Set<IngredientMapForRecipeDTO> mapIngredientsToStrings(Map<Ingredient, Double> ingredients) {
        return ingredients.entrySet().stream()
                .map(ingredient -> new IngredientMapForRecipeDTO(ingredient.getKey().getId().toString(), ingredient.getValue(), ingredient.getKey().getName(), ingredient.getKey().getUnitOfMeasure()))
                .collect(Collectors.toSet());
    }

    public boolean deleteRecipe(RecipeDTO recipeToDelete) {
        Optional<Recipe> optionalRecipe = recipeDAO.getRecipeById(recipeToDelete.id());
        return optionalRecipe.filter(recipeDAO::deleteRecipe).isPresent();
    }

    public boolean createRecipe(RecipeDTO postedRecipe) {
        Map<Ingredient, Double> ingredients = new HashMap<>();
        Set<IngredientMapForRecipeDTO> ingredientsFromFrontend = postedRecipe.ingredients();
        for (IngredientMapForRecipeDTO ingr : ingredientsFromFrontend) {
            Ingredient foundIngredient = this.ingredientService.getIngredientById(ingr.id()).get();
            double amount = ingr.amount();
            ingredients.put(foundIngredient, amount);
        }

        Recipe newRecipe = new Recipe(ingredients, postedRecipe.name(), postedRecipe.description());
        return recipeDAO.createRecipe(newRecipe);

    }
}
