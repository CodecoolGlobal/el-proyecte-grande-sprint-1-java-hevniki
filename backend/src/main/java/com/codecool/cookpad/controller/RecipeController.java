package com.codecool.cookpad.controller;

import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.service.RecipeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    public Set<Recipe> getRecipes() {
        return recipeService.getRecipes();
    }
}
