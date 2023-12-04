package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.model.IngredientType;
import com.codecool.cookpad.service.repository.IngredientTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientTypeService {
    private final IngredientTypeRepository ingredientTypeRepository;

    public IngredientTypeService(IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    public List<IngredientTypeDTO> getAllIngredients() {
        return ingredientTypeRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public IngredientTypeDTO getIngredientById(String id) {
        Optional<IngredientType> optionalIngredient = ingredientTypeRepository.findById(Long.valueOf(id));
        if (optionalIngredient.isPresent()) {
            return this.mapToDTO(optionalIngredient.get());
        }
        return null;
    }

    public IngredientTypeDTO createIngredient(IngredientTypeDTO newIngredient) {
        return mapToDTO(ingredientTypeRepository.save(mapFromDTO(newIngredient)));
    }

    public boolean deleteIngredient(IngredientTypeDTO ingredientToDelete) {
        Optional<IngredientType> optionalIngredient = this.ingredientTypeRepository.findById(ingredientToDelete.id());
        if (optionalIngredient.isPresent()) {
            this.ingredientTypeRepository.delete(optionalIngredient.get());
            return true;
        }
        return false;
    }

    protected IngredientTypeDTO mapToDTO(IngredientType ingredientType) {
        return new IngredientTypeDTO(ingredientType.getId(),
                ingredientType.getName(),
                ingredientType.getUnitOfMeasure(),
                ingredientType.isGlutenFree(),
                ingredientType.isDairyFree(),
                ingredientType.isMeatFree(),
                ingredientType.isEggFree()
        );
    }

    protected IngredientType mapFromDTO(IngredientTypeDTO newIngredientDTO) {
        IngredientType newIngredient = new IngredientType();
        newIngredient.setName(newIngredientDTO.name());
        newIngredient.setUnitOfMeasure(newIngredientDTO.unitOfMeasure());
        newIngredient.setDairyFree(newIngredientDTO.isDairyFree());
        newIngredient.setEggFree(newIngredientDTO.isEggFree());
        newIngredient.setMeatFree(newIngredientDTO.isMeatFree());
        newIngredient.setGlutenFree(newIngredientDTO.isGlutenFree());
        return newIngredient;
    }


}
