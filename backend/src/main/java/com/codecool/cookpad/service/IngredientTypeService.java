package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.exception.BadRequestException;
import com.codecool.cookpad.exception.IngredientNotFoundException;
import com.codecool.cookpad.model.entity.IngredientType;
import com.codecool.cookpad.service.repository.IngredientTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        throw new IngredientNotFoundException();
    }

    public IngredientType getIngredientById(Long id) {
        Optional<IngredientType> optionalIngredient = ingredientTypeRepository.findById((id));
        if (optionalIngredient.isPresent()) {
            return optionalIngredient.get();
        }
        throw new IngredientNotFoundException();
    }

    public IngredientTypeDTO createIngredient(IngredientTypeDTO newIngredient) {
        return mapToDTO(ingredientTypeRepository.save(mapFromDTO(newIngredient)));
    }

    public boolean deleteIngredient(String id) {
        Optional<IngredientType> optionalIngredient = this.ingredientTypeRepository.findById(Long.valueOf(id));
        if (optionalIngredient.isPresent()) {
            this.ingredientTypeRepository.delete(optionalIngredient.get());
            return true;
        }
        throw new IngredientNotFoundException();
    }

    public boolean updateIngredient(String id, IngredientTypeDTO ingredientToUpdate) {
        Optional<IngredientType> optionalIngredient = this.ingredientTypeRepository.findById(Long.valueOf(id));
        if (optionalIngredient.isPresent()) {
            IngredientType updatedIngredientType = mapFromDTO(ingredientToUpdate);
            updatedIngredientType.setId(optionalIngredient.get().getId());
            this.ingredientTypeRepository.save(updatedIngredientType);
            return true;
        }
        return false;
    }

    protected IngredientTypeDTO mapToDTO(IngredientType ingredientType) {
        if (ingredientType == null) {
            throw new BadRequestException();
        }
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
        if (newIngredientDTO == null) {
            throw new BadRequestException();
        }

        if (newIngredientDTO.id() != null) {
            return getIngredientById(newIngredientDTO.id());
        }

        IngredientType newIngredient = new IngredientType();
        newIngredient.setName(newIngredientDTO.name());
        newIngredient.setUnitOfMeasure(newIngredientDTO.unitOfMeasure());
        newIngredient.setDairyFree(newIngredientDTO.isDairyFree());
        newIngredient.setEggFree(newIngredientDTO.isEggFree());
        newIngredient.setMeatFree(newIngredientDTO.isMeatFree());
        newIngredient.setGlutenFree(newIngredientDTO.isGlutenFree());
        return newIngredient;
    }

    public void addDummyData() {
        List<IngredientTypeDTO> ingredients = new ArrayList<>();
        ingredients.add(new IngredientTypeDTO(0L, "Salt", "g", true, true, true, true));
        ingredients.add(new IngredientTypeDTO(1L, "Sugar", "g", true, true, true, true));
        ingredients.add(new IngredientTypeDTO(2L, "Oil", "dl", true, true, true, true));
        ingredients.add(new IngredientTypeDTO(3L, "Egg", "pc", true, true, true, false));
        ingredients.add(new IngredientTypeDTO(4L, "Milk", "dl", true, false, true, true));
        ingredients.add(new IngredientTypeDTO(5L, "Flour", "g", false, true, true, true));
        ingredients.add(new IngredientTypeDTO(6L, "Milk chocolate", "g", true, false, true, true));
        ingredients.add(new IngredientTypeDTO(7L, "Chicken wings", "pc", true, false, false, true));
        ingredientTypeRepository.saveAll(ingredients.stream().map(this::mapFromDTO).toList());
    }

}
