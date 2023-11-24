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

    public List<IngredientDTO> getAllIngredients() {
        return ingredientDAO.getAllIngredients().stream().map(this::mapToDTO).toList();
    }

    public IngredientDTO getIngredientDTOById(String id) {
        Optional<Ingredient> optionalIngredient = ingredientDAO.getIngredientById(id);
        return optionalIngredient.map(this::mapToDTO).orElse(null);
    }
    public Optional<Ingredient> getIngredientById(String id) {
       return ingredientDAO.getIngredientById(id);

    }

    public IngredientDTO createIngredient(IngredientDTO newIngredient) {
        Ingredient createdIngredient = mapDtoToIngredient(newIngredient);
        boolean success = ingredientDAO.addIngredient(createdIngredient);
        if (success) {
            return mapToDTO(createdIngredient);
        }
        return null;
    }

    private IngredientDTO mapToDTO(Ingredient ingredient) {
        return new IngredientDTO(ingredient.getId().toString(), ingredient.getName(), ingredient.getUnitOfMeasure(), ingredient.isGlutenFree(), ingredient.isDairyFree(), ingredient.isMeatFree(), ingredient.isEggFree());
    }

    private Ingredient mapDtoToIngredient(IngredientDTO ingredientAsDto) {
        return new Ingredient(ingredientAsDto.name(), ingredientAsDto.unitOfMeasure(), ingredientAsDto.isGlutenFree(), ingredientAsDto.isDairyFree(), ingredientAsDto.isMeatFree(), ingredientAsDto.isEggFree());
    }

    public boolean deleteIngredient(IngredientDTO ingredientToDelete) {
        Optional<Ingredient> optionalIngredient = ingredientDAO.getIngredientById(ingredientToDelete.id());
        return optionalIngredient.filter(ingredientDAO::deleteIngredient).isPresent();
    }
}
