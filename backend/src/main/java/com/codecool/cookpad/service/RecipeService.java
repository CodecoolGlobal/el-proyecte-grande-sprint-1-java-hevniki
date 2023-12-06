package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientForRecipeDTO;
import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.exception.BadRequestException;
import com.codecool.cookpad.model.entity.IngredientForRecipe;
import com.codecool.cookpad.model.entity.Recipe;
import com.codecool.cookpad.exception.RecipeNotFoundException;
import com.codecool.cookpad.service.logger.ConsoleLogger;
import com.codecool.cookpad.service.logger.Logger;
import com.codecool.cookpad.service.repository.RecipeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private final Logger logger;
    private final RecipeRepository recipeRepository;
    private final IngredientTypeService ingredientTypeService;

    public RecipeService(RecipeRepository recipeRepository, IngredientTypeService ingredientTypeService) {
        this.recipeRepository = recipeRepository;
        this.ingredientTypeService = ingredientTypeService;
        this.logger = new ConsoleLogger();
    }

    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> recipeDTOS = this.recipeRepository.findAll()
                .stream().map(this::mapToDTO).toList();
        logger.logMessage(String.format("Found %d recipes", recipeDTOS.size()));
        return recipeDTOS;
    }

    public RecipeDTO getRecipeById(String id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            logger.logMessage(String.format("Found %s", recipe.getName()));
            return mapToDTO(recipe);
        }
        throw new RecipeNotFoundException(id);
    }

    public List<RecipeDTO> getRecipeByName(String name) {
        List<Recipe> foundRecipes = recipeRepository.findByNameContainingIgnoreCase(name);
        logger.logMessage(String.format("Found %d recipes by name %s", foundRecipes.size(), name));
        return foundRecipes.stream().map(this::mapToDTO).toList();
    }

    public List<RecipeDTO> getRecipeByName(String name) {
        List<Recipe> foundRecipes = recipeRepository.findByNameContainingIgnoreCase(name);
      return foundRecipes.stream().map(this::mapToDTO).toList();
    }

    public boolean deleteRecipe(String id) {
        Optional<Recipe> optionalRecipe = this.recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            logger.logMessage(String.format("Deleting recipe named %s", recipe.getName()));
            this.recipeRepository.delete(recipe);
            return true;
        }
        throw new RecipeNotFoundException(id);
    }

    public void createRecipe(RecipeDTO newRecipeDTO) {
        Recipe recipe = mapFromDTO(newRecipeDTO);
        recipe.setProperties();
        String message = String.format("Creating recipe with name: %s, and %d ingredients",
                recipe.getName(), recipe.getIngredients().size());
        logger.logMessage(message);
        this.recipeRepository.save(recipe);
    }

    public void updateRecipe(RecipeDTO updatedRecipeDTO) {
        var id = updatedRecipeDTO.id();
        if (recipeRepository.findById(id).isPresent()) {
            logger.logMessage("Updating recipe");
            createRecipe(updatedRecipeDTO);
        } else {
            logger.logError("Can't update recipe");
            throw new RecipeNotFoundException(id.toString());
        }
    }

    private Recipe mapFromDTO(RecipeDTO recipeDTO) {
        Recipe recipe = new Recipe();
        if (recipeDTO.id() != null) {
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
        mappedIngredientForRecipe.setIngredientType(this.ingredientTypeService.getIngredient(ingredientForRecipeDTO.ingredient()));//here
        return mappedIngredientForRecipe;
    }

    public List<RecipeDTO> findRecipe(Map<String, String> params) {
        List<RecipeDTO> recipeDTOS = recipeRepository.findAll(buildSpecification(params))
                .stream()
                .map(this::mapToDTO)
                .toList();
        logger.logMessage(String.format("Found %d recipes", recipeDTOS.size()));
        return recipeDTOS;
    }

    private Specification<Recipe> buildSpecification(Map<String, String> params) {
        Specification<Recipe> spec = Specification.where(null);
        try {
            if (params.containsKey("name")) {
                spec = spec.and(containsName(params.get("name")));
            }
            if (params.containsKey("vegan")) {

                spec = spec.and(checkProperty("vegan", Boolean.parseBoolean(params.get("vegan"))));
            }
            if (params.containsKey("vegetarian")) {
                spec = spec.and(checkProperty("vegetarian", Boolean.parseBoolean(params.get("vegetarian"))));
            }
            if (params.containsKey("glutenFree")) {
                spec = spec.and(checkProperty("glutenFree", Boolean.parseBoolean(params.get("glutenFree"))));

            }
            if (params.containsKey("dairyFree")) {
                spec = spec.and(checkProperty("dairyFree", Boolean.parseBoolean(params.get("dairyFree"))));
            }
            if(params.containsKey("ingredients")){
                List<Long> ingredients = new ArrayList<>(getIdsFromReqParams(params.get("ingredients")));
               spec=spec.and(hasIngredientTypeIn(ingredients));
            }

            return spec;
        } catch (NumberFormatException e) {
            throw new BadRequestException();
            //this is weird
        }
    }

    private List<Long> getIdsFromReqParams(String param) {
        String[] ids = param.split(",");
        List<Long> idsAsLong = new ArrayList<>();
        try{
            for (String id : ids){
                idsAsLong.add( Long.parseLong(id));
            }
        }
        catch (NumberFormatException e) {
            throw new BadRequestException();
        }
        return idsAsLong;
    }

    private Specification<Recipe> hasIngredientTypeIn(List<Long> ingredientIds) {
        return (recipe, cq, cb)
                -> recipe.join("ingredients")
                        .join("ingredientType")
                        .get("id")
                        .in(ingredientIds);
    }
    private Specification<Recipe> containsName(String name) {
        return (recipe, cq, cb)
                -> cb.like(cb.lower(recipe.get("name")), "%" + name.toLowerCase() + "%");
    }


    private Specification<Recipe> checkProperty(String property, boolean value) {
        return (recipe, cq, cb)
                -> cb.equal(recipe.get(property), value);
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
        recipeEntities.forEach(Recipe::setProperties);
        this.recipeRepository.saveAll(recipeEntities);
    }

}
