package com.codecool.cookpad.service.dao;

import com.codecool.cookpad.model.IngredientType;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryIngredientDAO implements IngredientDAO {

    private final Set<IngredientType> ingredientTypes;

    public MemoryIngredientDAO() {
        this.ingredientTypes = new HashSet<>();
        /*

        IngredientType egg = new IngredientType("Egg", "pc", true,true,true,false);
        IngredientType oil = new IngredientType("Oil", "dl", true,true,true,true);
        IngredientType salt = new IngredientType("salt", "g", true,true,true,true);

        ingredientTypes.add(egg);
        ingredientTypes.add(oil);
        ingredientTypes.add(salt);
        */
    }


    @Override
    public List<IngredientType> getAllIngredients() {
        return ingredientTypes.stream().toList();
    }

    @Override
    public Optional<IngredientType> getIngredientById(String id) {
        return Optional.empty();
        //return ingredientTypes.stream().filter(ingredient -> ingredient.getId().equals(UUID.fromString(id))).findFirst();
    }
    public boolean addIngredient(IngredientType ingredientType){
        return this.ingredientTypes.add(ingredientType);
    }
public boolean deleteIngredient(IngredientType ingredientTypeToDelete){
        return this.ingredientTypes.remove(ingredientTypeToDelete);
}
}
