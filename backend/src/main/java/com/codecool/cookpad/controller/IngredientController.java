package com.codecool.cookpad.controller;

import com.codecool.cookpad.dto.IngredientDTO;
import com.codecool.cookpad.model.Ingredient;
import com.codecool.cookpad.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;

    }

    @GetMapping
    public List<IngredientDTO> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIngredientById(@PathVariable String id) {
        IngredientDTO foundIngredient =ingredientService.getIngredientById(id);
        if(foundIngredient==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundIngredient);
    }
}
