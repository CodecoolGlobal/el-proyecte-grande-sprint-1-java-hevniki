package com.codecool.cookpad.controller;

import com.codecool.cookpad.dto.IngredientDTO;
import com.codecool.cookpad.service.IngredientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        IngredientDTO foundIngredient =ingredientService.getIngredientDTOById(id);
        if(foundIngredient==null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundIngredient);
    }
    @PostMapping
    public ResponseEntity<?> postIngredient(@RequestBody IngredientDTO postedIngredient){
        try{
            IngredientDTO createdIngredient= ingredientService.createIngredient(postedIngredient);
            if(createdIngredient==null){
                ResponseEntity.badRequest();
            }
            return ResponseEntity.ok(createdIngredient);
        }
        catch(Exception e){
           return ResponseEntity.internalServerError().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIngredientById(@PathVariable String id) {
        IngredientDTO foundIngredient =ingredientService.getIngredientDTOById(id);
        if(foundIngredient==null){
            return ResponseEntity.badRequest().build();
        }
        boolean success = ingredientService.deleteIngredient(foundIngredient);
        if(!success) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(foundIngredient);

    }
}
