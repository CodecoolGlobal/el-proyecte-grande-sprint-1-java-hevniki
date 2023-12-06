package com.codecool.cookpad.service.repository;

import com.codecool.cookpad.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
    Optional<Recipe> findByName(String name);
    List<Recipe> findByNameContainingIgnoreCase(String name);

}

