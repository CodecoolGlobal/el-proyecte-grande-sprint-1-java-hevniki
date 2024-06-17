package com.codecool.cookpad.controller;

import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.exception.IngredientNotFoundException;
import com.codecool.cookpad.service.IngredientTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientTypeService ingredientTypeService;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);


    public IngredientController(IngredientTypeService ingredientTypeService) {
        this.ingredientTypeService = ingredientTypeService;
    }

    @GetMapping
    public List<IngredientTypeDTO> getAllIngredients() {
        return ingredientTypeService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable String id) {
        IngredientTypeDTO foundIngredient = ingredientTypeService.getIngredientById(id);
        if (foundIngredient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundIngredient);
    }

    @PostMapping
    public ResponseEntity<IngredientTypeDTO> addIngredient(@RequestBody IngredientTypeDTO newIngredient) {
        logger.info("Received request to add ingredient");
        logger.info("IngredientDTO: {}", newIngredient);
        IngredientTypeDTO createdIngredient = ingredientTypeService.createIngredient(newIngredient);
        return ResponseEntity.ok(createdIngredient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIngredient(@RequestBody IngredientTypeDTO updatedIngredient, @PathVariable String id) {
        try {
            ingredientTypeService.updateIngredient(id, updatedIngredient);
            return ResponseEntity.ok(updatedIngredient);
        } catch (IngredientNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
