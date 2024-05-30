package com.codecool.cookpad.controller;

import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.exception.ImageNotFoundException;
import com.codecool.cookpad.model.entity.Recipe;
import com.codecool.cookpad.service.RecipeService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);
    
    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public List<RecipeDTO> getRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRecipeById(@PathVariable String id) {
        RecipeDTO foundRecipe = recipeService.getRecipeDTO(id);
        if (foundRecipe == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundRecipe);
    }

    @GetMapping("/{id}/image")
    public  ResponseEntity<byte[]> getImage(@PathVariable String id) {
        Recipe recipe = recipeService.getRecipe(id);

        if (recipe != null && recipe.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(recipe.getImage(), headers, HttpStatus.OK);
        }
        throw new ImageNotFoundException(id);
    }

    @GetMapping("/search")
    public ResponseEntity<?> filterRecipes(@RequestParam Map<String, String> params) {
        return ResponseEntity.ok(recipeService.findRecipe(params));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRecipeById(@PathVariable String id) {
        RecipeDTO foundRecipe = recipeService.getRecipeDTO(id);
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
    public ResponseEntity<?> updateRecipe(
            @RequestPart("recipeDTO") RecipeDTO updatedRecipe,
            @PathVariable String id,
            @RequestPart(required = false) MultipartFile image) {
        RecipeDTO recipeToUpdate = recipeService.getRecipeDTO(id);
        if(recipeToUpdate == null){
            return ResponseEntity.badRequest().build();
        }
        try {
            recipeService.updateRecipe(updatedRecipe, image);
        } catch (IOException exception) {
            System.out.println("Failed to save image");
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(updatedRecipe);
    }

    @PostMapping
    public ResponseEntity<?> postRecipe(
            @RequestPart("recipeDTO") RecipeDTO recipeDTO,
            @RequestPart(required = false) MultipartFile image) {
        logger.info("Received request to create recipe");
        logger.info("RecipeDTO: {}", recipeDTO);
        try {
            recipeService.createRecipe(recipeDTO, image);
        } catch (IOException exception) {
            System.out.println("Failed to save image");
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok(recipeDTO);
    }
}
