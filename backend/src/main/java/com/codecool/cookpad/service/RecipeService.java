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

    public List<RecipeDTO> getAllRecipes() {
        return this.recipeRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public RecipeDTO getRecipeById(String id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            return mapToDTO(optionalRecipe.get());
        }
        return null;
    }

    public boolean deleteRecipe(String id) {
        Optional<Recipe> optionalRecipe = this.recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            this.recipeRepository.delete(optionalRecipe.get());
            return true;
        }
        return false;
    }

    public void createRecipe(RecipeDTO newRecipeDTO) {
        this.recipeRepository.save(mapFromDTO(newRecipeDTO));
    }

    public void updateRecipe(RecipeDTO updatedRecipeDTO){
        this.recipeRepository.save(mapFromDTO(updatedRecipeDTO));
    }
    private Recipe mapFromDTO(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        if(recipeDTO.id()!=null){
            recipe.setId(recipeDTO.id());
        }
        recipe.setName(recipeDTO.name());
        recipe.setDescription(recipeDTO.description());
        recipe.setIngredients(recipeDTO.ingredients().stream().map(this::mapFromIngredientForRecipeDTO).collect(Collectors.toSet()));
        return recipe;
    }

    private RecipeDTO mapToDTO(Recipe recipe) {
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

    private IngredientForRecipeDTO mapToIngredientForRecipeDTO(IngredientForRecipe ingredientForRecipe) {
        return new IngredientForRecipeDTO(
                ingredientForRecipe.getId(),
                this.ingredientTypeService.mapToDTO(ingredientForRecipe.getIngredientType()),
                ingredientForRecipe.getAmount()
        );
    }

    private IngredientForRecipe mapFromIngredientForRecipeDTO(IngredientForRecipeDTO ingredientForRecipeDTO) {
        IngredientForRecipe mappedIngredientForRecipe = new IngredientForRecipe();
        if (ingredientForRecipeDTO.id() != null) {
            mappedIngredientForRecipe.setId(ingredientForRecipeDTO.id());
        }
        mappedIngredientForRecipe.setAmount(ingredientForRecipeDTO.amount());
        mappedIngredientForRecipe.setIngredientType(this.ingredientTypeService.mapFromDTO(ingredientForRecipeDTO.ingredient()));
        return mappedIngredientForRecipe;
    }

}
