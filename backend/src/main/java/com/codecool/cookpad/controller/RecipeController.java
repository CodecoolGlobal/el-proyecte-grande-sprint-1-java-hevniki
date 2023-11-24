package com.codecool.cookpad.controller;

import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Set<RecipeDTO> getRecipes() {
        return recipeService.getRecipes();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable String id) {
        RecipeDTO foundRecipe =recipeService.getRecipeById(id);
        if(foundRecipe==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundRecipe);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable String id) {
        RecipeDTO foundRecipe =recipeService.getRecipeById(id);
        if(foundRecipe==null){
            return ResponseEntity.badRequest().build();
        }
        boolean success = recipeService.deleteRecipe(foundRecipe);
        if(!success) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundRecipe);
    }

    @PostMapping
    public ResponseEntity<RecipeDTO> postRecipe(@RequestBody RecipeDTO postedRecipe){
        RecipeDTO receipe = recipeService.createRecipe(postedRecipe);
        if(receipe != null){
            return ResponseEntity.ok().body(receipe);
        }
        return ResponseEntity.internalServerError().build();
    }
}
