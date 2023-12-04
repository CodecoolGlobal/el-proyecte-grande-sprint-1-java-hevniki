package com.codecool.cookpad.service.repository;
import com.codecool.cookpad.model.IngredientForRecipe;
import com.codecool.cookpad.model.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientForRecipeRepository extends JpaRepository<IngredientForRecipe, Long> {
    Optional<IngredientForRecipe> findByName(String name);

}

