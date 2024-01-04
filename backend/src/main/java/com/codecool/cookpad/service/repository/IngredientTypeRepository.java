package com.codecool.cookpad.service.repository;

import com.codecool.cookpad.model.entity.IngredientType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IngredientTypeRepository extends JpaRepository<IngredientType, Long> {
    Optional<IngredientType> findByName(String name);


}
