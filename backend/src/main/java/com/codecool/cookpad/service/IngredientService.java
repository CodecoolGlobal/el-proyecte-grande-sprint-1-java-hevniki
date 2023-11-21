package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientDTO;
import com.codecool.cookpad.model.Ingredient;
import com.codecool.cookpad.service.dao.IngredientDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final IngredientDAO ingredientDAO;

    public IngredientService(IngredientDAO ingredientDAO) {
        this.ingredientDAO = ingredientDAO;
    }
    public List<IngredientDTO> getAllIngredients(){
        return ingredientDAO.getAllIngredients().stream().map(this::mapToDTO).toList();
    }

    public IngredientDTO getIngredientById(String id){
        Optional<Ingredient> optionalIngredient = ingredientDAO.getIngredientById(id);
        if (optionalIngredient.isEmpty()){
            return null;
        }
        return mapToDTO(optionalIngredient.get());
    }

    private IngredientDTO mapToDTO(Ingredient ingredient){
        return new IngredientDTO(ingredient.getId(), ingredient.getName(), ingredient.getUnitOfMeasure(), ingredient.isGlutenFree(), ingredient.isDairyFree(), ingredient.isMeatFree(), ingredient.isEggFree());
    }
}
