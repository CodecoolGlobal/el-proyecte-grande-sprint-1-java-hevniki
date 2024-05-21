package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.exception.BadRequestException;
import com.codecool.cookpad.exception.IngredientNotFoundException;
import com.codecool.cookpad.model.entity.IngredientType;
import com.codecool.cookpad.service.logger.ConsoleLogger;
import com.codecool.cookpad.service.logger.Logger;
import com.codecool.cookpad.service.repository.IngredientTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientTypeService {
    private final IngredientTypeRepository ingredientTypeRepository;
    private final Logger logger;

    public IngredientTypeService(IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;
        this.logger = new ConsoleLogger();
    }

    public List<IngredientTypeDTO> getAllIngredients() {
        List<IngredientTypeDTO> ingredients = ingredientTypeRepository.findAll().stream().map(this::mapToDTO).toList();
        logger.logMessage(String.format("Got %d ingredients", ingredients.size()));
        return ingredients;
    }

    public IngredientTypeDTO getIngredientById(String id) {
        Optional<IngredientType> optionalIngredient = ingredientTypeRepository.findById(Long.valueOf(id));
        if (optionalIngredient.isPresent()) {
            IngredientType ingredient = optionalIngredient.get();
            logger.logMessage("Found ingredient %s, ingredient.getName()");
            return this.mapToDTO(ingredient);
        }
        throw new IngredientNotFoundException(id);
    }

    public IngredientType getIngredientById(Long id) {
        Optional<IngredientType> optionalIngredient = ingredientTypeRepository.findById((id));
        if (optionalIngredient.isPresent()) {
            return optionalIngredient.get();
        }
        throw new IngredientNotFoundException(id.toString());
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
        throw new IngredientNotFoundException(id);
    }

    public void updateIngredient(String id, IngredientTypeDTO ingredientToUpdate) throws IngredientNotFoundException{
        Optional<IngredientType> optionalIngredient = this.ingredientTypeRepository.findById(Long.valueOf(id));
        if (optionalIngredient.isPresent()) {
            IngredientType ingredientType = optionalIngredient.get();
            logger.logMessage(String.format("Found ingredient: %s", ingredientType.getName()));

            IngredientType updatedIngredientType = mapFromDTO(ingredientToUpdate);
            updatedIngredientType.setId(ingredientType.getId());
            this.ingredientTypeRepository.save(updatedIngredientType);

        }
        throw new IngredientNotFoundException(id);
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

        IngredientType newIngredient = new IngredientType();
        newIngredient.setId(newIngredientDTO.id());
        newIngredient.setName(newIngredientDTO.name());
        newIngredient.setUnitOfMeasure(newIngredientDTO.unitOfMeasure());
        newIngredient.setDairyFree(newIngredientDTO.isDairyFree());
        newIngredient.setEggFree(newIngredientDTO.isEggFree());
        newIngredient.setMeatFree(newIngredientDTO.isMeatFree());
        newIngredient.setGlutenFree(newIngredientDTO.isGlutenFree());
        return newIngredient;
    }

    public IngredientType getIngredient(IngredientTypeDTO ingredientDTO) {
        if (ingredientDTO == null) {
            throw new BadRequestException();
        }
        if (ingredientDTO.id() != null) {
            return this.getIngredientById(ingredientDTO.id());
        }
        return this.mapFromDTO(ingredientDTO);
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
