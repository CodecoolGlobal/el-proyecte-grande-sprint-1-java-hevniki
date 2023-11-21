package com.codecool.cookpad.service;

import com.codecool.cookpad.model.Ingredient;
import com.codecool.cookpad.service.dao.IngredientDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientsService {
    private final IngredientDAO ingredientDAO;

    public IngredientsService(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }
    public List<Ingredient> getAllIngredients(){
        return ingredientDAO.getAllIngredients();
    }
}
