package com.codecool.cookpad.controller;

import com.codecool.cookpad.model.Ingredient;
import com.codecool.cookpad.service.IngredientsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
public class IngredientsController {
    private final IngredientsService ingredientsService;
    public IngredientsController(IngredientsService ingredientsService) {
        this.ingredientsService = ingredientsService;
    }

    @GetMapping
    public List<Ingredient> getAllIngredients(){
        return ingredientsService.getAllIngredients();
    }
}
