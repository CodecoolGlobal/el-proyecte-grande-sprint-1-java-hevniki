package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientForRecipeDTO;
import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.model.IngredientForRecipe;
import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.model.RecipeNotFoundException;
import com.codecool.cookpad.service.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientTypeService ingredientTypeService;

    public RecipeService(RecipeRepository recipeRepository, IngredientTypeService ingredientTypeService) {
        this.recipeRepository = recipeRepository;
        this.ingredientTypeService = ingredientTypeService;
    }

    public List<RecipeDTO> getAllRecipes() {
        return this.recipeRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    public RecipeDTO getRecipeById(String id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            return mapToDTO(optionalRecipe.get());
        }
       throw new RecipeNotFoundException();
    }

    public boolean deleteRecipe(String id) {
        Optional<Recipe> optionalRecipe = this.recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            this.recipeRepository.delete(optionalRecipe.get());
            return true;
        }
        return false;
    }

    public void createRecipe(RecipeDTO newRecipeDTO) {
        this.recipeRepository.save(mapFromDTO(newRecipeDTO));
    }

    public void updateRecipe(RecipeDTO updatedRecipeDTO){
        this.recipeRepository.save(mapFromDTO(updatedRecipeDTO));
    }
    private Recipe mapFromDTO(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        if(recipeDTO.id()!=null){
            recipe.setId(recipeDTO.id());
        }
        recipe.setName(recipeDTO.name());
        recipe.setDescription(recipeDTO.description());
        recipe.setIngredients(recipeDTO.ingredients().stream().map(this::mapFromIngredientForRecipeDTO).collect(Collectors.toSet()));
        return recipe;
    }

    private RecipeDTO mapToDTO(Recipe recipe) {
        Set<IngredientForRecipeDTO> ingredients = recipe.getIngredients().stream().map(this::mapToIngredientForRecipeDTO).collect(Collectors.toSet());

        return new RecipeDTO(
                recipe.getId(),
                ingredients,
                recipe.getName(),
                recipe.getDescription(),
                recipe.isVegan(),
                recipe.isVegetarian(),
                recipe.isGlutenFree(),
                recipe.isDairyFree()
        );
    }

    private IngredientForRecipeDTO mapToIngredientForRecipeDTO(IngredientForRecipe ingredientForRecipe) {
        return new IngredientForRecipeDTO(
                ingredientForRecipe.getId(),
                this.ingredientTypeService.mapToDTO(ingredientForRecipe.getIngredientType()),
                ingredientForRecipe.getAmount()
        );
    }

    private IngredientForRecipe mapFromIngredientForRecipeDTO(IngredientForRecipeDTO ingredientForRecipeDTO) {
        IngredientForRecipe mappedIngredientForRecipe = new IngredientForRecipe();
        if (ingredientForRecipeDTO.id() != null) {
            mappedIngredientForRecipe.setId(ingredientForRecipeDTO.id());
        }
        mappedIngredientForRecipe.setAmount(ingredientForRecipeDTO.amount());
        mappedIngredientForRecipe.setIngredientType(this.ingredientTypeService.mapFromDTO(ingredientForRecipeDTO.ingredient()));
        return mappedIngredientForRecipe;
    }

    public void addDummyData() {
        List<IngredientTypeDTO> ingredients = ingredientTypeService.getAllIngredients();
        IngredientTypeDTO salt = ingredients.get(0);
        IngredientTypeDTO sugar = ingredients.get(1);
        IngredientTypeDTO oil = ingredients.get(2);
        IngredientTypeDTO egg = ingredients.get(3);
        IngredientTypeDTO milk = ingredients.get(4);
        IngredientTypeDTO flour = ingredients.get(5);
        IngredientTypeDTO milkChocolate = ingredients.get(6);

        List<RecipeDTO> recipes = new ArrayList<>();

        Set<IngredientForRecipeDTO> cookieIngredients = new HashSet<>();
        cookieIngredients.add(new IngredientForRecipeDTO(null, salt, 3));
        cookieIngredients.add(new IngredientForRecipeDTO(null, milk, 4));
        cookieIngredients.add(new IngredientForRecipeDTO(null, sugar, 30));
        cookieIngredients.add(new IngredientForRecipeDTO(null, milkChocolate, 70));
        cookieIngredients.add(new IngredientForRecipeDTO(null, flour, 100));
        recipes.add(new RecipeDTO(null, cookieIngredients, "Cookie", "Refer to an actual recipe sharing site for detailed steps!", false, true, false, false));

        Set<IngredientForRecipeDTO> omeletteIngredients = new HashSet<>();
        omeletteIngredients.add(new IngredientForRecipeDTO(null, egg, 2));
        omeletteIngredients.add(new IngredientForRecipeDTO(null, oil, 0.5));
        recipes.add(new RecipeDTO(null, omeletteIngredients, "Omelette", "Fry the eggs on some oil. Bon appetite!", false, true, true, true));

        Set<Recipe> recipeEntities = recipes.stream().map(this::mapFromDTO).collect(Collectors.toSet());
        this.recipeRepository.saveAll(recipeEntities);
    }

}
