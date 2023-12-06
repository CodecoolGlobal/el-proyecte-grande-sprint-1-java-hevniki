package com.codecool.cookpad.controller;

import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.service.IngredientTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientTypeService ingredientTypeService;

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
    public ResponseEntity<?> postIngredient(@RequestBody IngredientTypeDTO postedIngredient) {
        try {
            IngredientTypeDTO createdIngredient = ingredientTypeService.createIngredient(postedIngredient);
            if (createdIngredient == null) {
                ResponseEntity.badRequest();
            }
            return ResponseEntity.ok(createdIngredient);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/dummy")
    public void addDummyData() {
        ingredientTypeService.addDummyData();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredientById(@PathVariable String id) {
        IngredientTypeDTO foundIngredient = ingredientTypeService.getIngredientById(id);
        if (foundIngredient == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean success = ingredientTypeService.deleteIngredient(id);
        if (!success) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundIngredient);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIngredient(@RequestBody IngredientTypeDTO updatedIngredient, @PathVariable String id){
        IngredientTypeDTO ingredientToUpdate = ingredientTypeService.getIngredientById(id);
        if(ingredientToUpdate == null){
            return ResponseEntity.badRequest().build();
        }
        ingredientTypeService.updateIngredient( updatedIngredient);
        return ResponseEntity.ok(updatedIngredient);
    }
}
