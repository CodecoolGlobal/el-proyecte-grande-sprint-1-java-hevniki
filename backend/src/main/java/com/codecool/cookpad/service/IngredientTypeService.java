package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.exception.BadRequestException;
import com.codecool.cookpad.exception.IngredientNotFoundException;
import com.codecool.cookpad.model.IngredientCategory;
import com.codecool.cookpad.model.entity.IngredientType;
import com.codecool.cookpad.security.AuthEntryPointJwt;
import com.codecool.cookpad.service.repository.IngredientTypeRepository;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientTypeService {
    private final IngredientTypeRepository ingredientTypeRepository;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    public IngredientTypeService(IngredientTypeRepository ingredientTypeRepository) {
        this.ingredientTypeRepository = ingredientTypeRepository;
    }

    public List<IngredientTypeDTO> getAllIngredients() {
        List<IngredientTypeDTO> ingredients = ingredientTypeRepository.findAll().stream().map(this::mapToDTO).toList();
        logger.info(String.format("Got %d ingredients", ingredients.size()));
        return ingredients;
    }

    public IngredientTypeDTO getIngredientById(String id) {
        Optional<IngredientType> optionalIngredient = ingredientTypeRepository.findById(Long.valueOf(id));
        if (optionalIngredient.isPresent()) {
            IngredientType ingredient = optionalIngredient.get();
            logger.info("Found ingredient %s, ingredient.getName()");
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
        IngredientType ingredient = mapFromDTO(newIngredient);
        IngredientType savedIngredient = ingredientTypeRepository.save(ingredient);
        return mapToDTO(savedIngredient);
    }

    public boolean deleteIngredient(String id) {
        Optional<IngredientType> optionalIngredient = this.ingredientTypeRepository.findById(Long.valueOf(id));
        if (optionalIngredient.isPresent()) {
            this.ingredientTypeRepository.delete(optionalIngredient.get());
            return true;
        }
        throw new IngredientNotFoundException(id);
    }

    public void updateIngredient(String id, IngredientTypeDTO ingredientToUpdate) throws IngredientNotFoundException {
        Optional<IngredientType> optionalIngredient = this.ingredientTypeRepository.findById(Long.valueOf(id));
        if (optionalIngredient.isPresent()) {
            IngredientType ingredientType = optionalIngredient.get();
            logger.info(String.format("Found ingredient: %s", ingredientType.getName()));

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
        return new IngredientTypeDTO(
                ingredientType.getId(),
                ingredientType.getName(),
                ingredientType.getCategory(),
                ingredientType.isApproved()
        );
    }

    protected IngredientType mapFromDTO(IngredientTypeDTO newIngredientDTO) {
        if (newIngredientDTO == null) {
            throw new BadRequestException();
        }

        IngredientType newIngredient = new IngredientType();
        newIngredient.setId(newIngredientDTO.id());
        newIngredient.setName(newIngredientDTO.name());
        newIngredient.setApproved(newIngredientDTO.approved());
        newIngredient.setCategory(newIngredientDTO.category());
        return newIngredient;
    }

    public IngredientType getIngredient(String ingredientName) {
        if (ingredientName == null) {
            throw new BadRequestException();
        }
        Optional<IngredientType> byName = this.ingredientTypeRepository.findByName(ingredientName);

        if (byName.isPresent()) {
            return byName.get();
        }
        IngredientType unknownIngredient = new IngredientType(
                0L,
                ingredientName,
                IngredientCategory.UNKNOWN,
                false
        );

        ingredientTypeRepository.save(unknownIngredient);
        return getIngredient(ingredientName);
    }
}
