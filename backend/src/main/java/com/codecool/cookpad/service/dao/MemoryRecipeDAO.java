package com.codecool.cookpad.service.dao;

import com.codecool.cookpad.model.IngredientType;
import com.codecool.cookpad.model.Recipe;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryRecipeDAO implements RecipeDAO {
    private final Set<Recipe> recipes;

    public MemoryRecipeDAO() {
        this.recipes = new HashSet<>();
        addRecipes();
    }

    @Override
    public Set<Recipe> getRecipes() {
        return this.recipes;
    }


    public void addRecipes() {
        /*
        Map<IngredientType, Double> ingredients1 = new HashMap<>();

        ingredients1.put(new IngredientType("Salt", "spoon", true, true, true, true), 1.0);
        ingredients1.put(new IngredientType("Eggs", "pc", true, true, true, false), 1.0);
        ingredients1.put(new IngredientType("Oil", "dl", true, true, true, true), 1.0);

        Map<IngredientType, Double> ingredients2 = new HashMap<>();

        ingredients2.put(new IngredientType("Flour", "kg", false, true, true, true), 0.5);
        ingredients2.put(new IngredientType("Eggs", "pc", false, true, true, false), 2.0);
        ingredients2.put(new IngredientType("Peach", "pc", true, true, true, true), 0.1);

        recipes.add(new Recipe(ingredients1, "Omelettes", "Put egg on oil."));
        recipes.add(new Recipe(ingredients2, "Magic", "Do as you wish"));

         */
    }
    @Override
    public Optional<Recipe> getRecipeById(String id) {
        return Optional.empty();

       // return recipes.stream().filter(recipe -> recipe.getId().equals(UUID.fromString(id))).findFirst();
       }
    public boolean deleteRecipe(Recipe recipeToDelete){
        return this.recipes.remove(recipeToDelete);
    }
    public boolean createRecipe(Recipe recipeToAdd){
        return this.recipes.add(recipeToAdd);
    }
}
