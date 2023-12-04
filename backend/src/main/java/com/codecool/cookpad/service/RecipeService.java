package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientForRecipeDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.model.IngredientForRecipe;
import com.codecool.cookpad.model.IngredientType;
import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.service.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientTypeService ingredientTypeService;

    public RecipeService(RecipeRepository recipeRepository, IngredientTypeService ingredientTypeService) {
        this.recipeRepository = recipeRepository;
        this.ingredientTypeService = ingredientTypeService;
    }

    public List<RecipeDTO> getAllRecipes(){

    }

    private RecipeDTO mapToDTO(Recipe recipe){
        Set<IngredientForRecipeDTO> ingredients = recipe.getIngredients().stream().map(this::mapToIngredientForRecipeDTO).collect(Collectors.toSet());

        return new RecipeDTO(
                recipe.getId(),
                ingredients,
                recipe.getName(),
                recipe.getDescription(),
                recipe.isVegan(),
                recipe.isVegetarian(),
                recipe.isGlutenFree(),
                recipe.isDairyFree()
        );
    }
    private IngredientForRecipeDTO mapToIngredientForRecipeDTO(IngredientForRecipe ingredientForRecipe){
        return new IngredientForRecipeDTO(
                ingredientForRecipe.getId(),
                this.ingredientTypeService.mapToDTO(ingredientForRecipe.getIngredientType()),
                ingredientForRecipe.getAmount()
        );
    }
}
