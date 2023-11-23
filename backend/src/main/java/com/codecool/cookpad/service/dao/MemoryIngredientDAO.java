package com.codecool.cookpad.service.dao;

import com.codecool.cookpad.dto.IngredientDTO;
import com.codecool.cookpad.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryIngredientDAO implements IngredientDAO {

    private final Set<Ingredient> ingredients;

    public MemoryIngredientDAO() {
        this.ingredients = new HashSet<>();
        Ingredient egg = new Ingredient("Egg", "pc", true,true,true,false);
        Ingredient oil = new Ingredient("Oil", "dl", true,true,true,true);
        Ingredient salt = new Ingredient("salt", "g", true,true,true,true);

        ingredients.add(egg);
        ingredients.add(oil);
        ingredients.add(salt);
    }


    @Override
    public List<Ingredient> getAllIngredients() {
        return ingredients.stream().toList();
    }

    @Override
    public Optional<Ingredient> getIngredientById(String id) {
        return ingredients.stream().filter(ingredient -> ingredient.getId().equals(UUID.fromString(id))).findFirst();
    }
    public boolean addIngredient(Ingredient ingredient){
        return this.ingredients.add(ingredient);
    }
public boolean deleteIngredient(Ingredient ingredientToDelete){
        return this.ingredients.remove(ingredientToDelete);
}
}
