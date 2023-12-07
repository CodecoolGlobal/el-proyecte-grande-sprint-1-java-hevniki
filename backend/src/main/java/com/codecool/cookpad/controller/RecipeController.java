package com.codecool.cookpad.controller;

import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeDTO> getRecipes() {
        return recipeService.getAllRecipes();

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable String id) {
        RecipeDTO foundRecipe = recipeService.getRecipeById(id);
        if (foundRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundRecipe);
    }

    @GetMapping("/search")
    public ResponseEntity<?> filterRecipes(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(recipeService.findRecipe(params));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable String id) {
        RecipeDTO foundRecipe = recipeService.getRecipeById(id);
        if (foundRecipe == null) {
            return ResponseEntity.badRequest().build();
        }
        boolean success = recipeService.deleteRecipe(id);
        if (!success) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundRecipe);

    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateRecipe(@RequestBody RecipeDTO updatedRecipe, @PathVariable String id){
        RecipeDTO recipeToUpdate = recipeService.getRecipeById(id);
        if(recipeToUpdate == null){
            return ResponseEntity.badRequest().build();
        }
        recipeService.updateRecipe(updatedRecipe);
        return ResponseEntity.ok(updatedRecipe);
    }

    @PostMapping
    public ResponseEntity<?> postRecipe(@RequestBody RecipeDTO postedRecipe) {
        recipeService.createRecipe(postedRecipe);
        return ResponseEntity.ok(postedRecipe);
    }

    @PostMapping("/dummy")
    public void addDummyData() {
        this.recipeService.addDummyData();
    }
}
