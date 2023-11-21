package com.codecool.cookpad.service.dao;

import com.codecool.cookpad.model.Ingredient;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
