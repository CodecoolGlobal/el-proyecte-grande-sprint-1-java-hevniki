package com.codecool.cookpad.controller;

import com.codecool.cookpad.dto.IngredientTypeDTO;
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

    @PostMapping("/dummy")
    public void addDummyData() {
        ingredientTypeService.addDummyData();

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateIngredient(@RequestBody IngredientTypeDTO updatedIngredient, @PathVariable String id){
        IngredientTypeDTO ingredientToUpdate = ingredientTypeService.getIngredientById(id);
        if(ingredientToUpdate == null){
            return ResponseEntity.badRequest().build();
        }
        ingredientTypeService.updateIngredient(id, updatedIngredient);
        return ResponseEntity.ok(updatedIngredient);
    }
}
