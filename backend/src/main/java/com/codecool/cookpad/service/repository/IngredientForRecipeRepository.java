package com.codecool.cookpad.service.repository;

import com.codecool.cookpad.model.entity.IngredientForRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientForRecipeRepository extends JpaRepository<IngredientForRecipe, Long> {
}
