package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientForRecipeDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.exception.BadRequestException;
import com.codecool.cookpad.model.entity.IngredientForRecipe;
import com.codecool.cookpad.model.entity.IngredientType;
import com.codecool.cookpad.model.entity.Recipe;
import com.codecool.cookpad.exception.RecipeNotFoundException;
import com.codecool.cookpad.security.AuthEntryPointJwt;
import com.codecool.cookpad.service.repository.IngredientForRecipeRepository;
import com.codecool.cookpad.service.repository.RecipeRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
    private final RecipeRepository recipeRepository;
    private final IngredientTypeService ingredientTypeService;
    private final IngredientForRecipeRepository ingredientForRecipeRepository;
    public RecipeService(RecipeRepository recipeRepository, IngredientTypeService ingredientTypeService, IngredientForRecipeRepository ingredientForRecipeRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientTypeService = ingredientTypeService;
        this.ingredientForRecipeRepository = ingredientForRecipeRepository;
    }

    @Transactional
    public Recipe getRecipe(String id) {
        Optional<Recipe> recipeOptional = this.recipeRepository.findById(Long.valueOf(id));

        if (recipeOptional.isPresent()) {
            Recipe recipe = recipeOptional.get();
            logger.info(String.format("Found %s", recipe.getName()));
            return recipe;
        }
        throw new RecipeNotFoundException(id);
    }

    public List<RecipeDTO> getAllRecipes() {
        List<RecipeDTO> recipeDTOS = this.recipeRepository.findAll()
                .stream().map(this::mapToDTO).toList();
        logger.info(String.format("Found %d recipes", recipeDTOS.size()));
        return recipeDTOS;
    }

    public RecipeDTO getRecipeDTO(String id) {
        Optional<Recipe> optionalRecipe = recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            logger.info(String.format("Found %s", recipe.getName()));
            return mapToDTO(recipe);
        }
        throw new RecipeNotFoundException(id);
    }

    public List<RecipeDTO> getRecipeByName(String name) {
        List<Recipe> foundRecipes = recipeRepository.findByNameContainingIgnoreCase(name);
        logger.info(String.format("Found %d recipes by name %s", foundRecipes.size(), name));
        return foundRecipes.stream().map(this::mapToDTO).toList();
    }

    public boolean deleteRecipe(String id) {
        Optional<Recipe> optionalRecipe = this.recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            Recipe recipe = optionalRecipe.get();
            logger.info(String.format("Deleting recipe named %s", recipe.getName()));
            this.recipeRepository.delete(recipe);
            return true;
        }
        throw new RecipeNotFoundException(id);
    }

    public void createRecipe(RecipeDTO newRecipeDTO, MultipartFile imageFile) throws IOException {
        Recipe recipe = mapFromDTO(newRecipeDTO);

        if (imageFile != null && !imageFile.isEmpty()) {
            recipe.setImage(imageFile.getBytes());
        }

        String message = String.format("Creating recipe with name: %s, and %d ingredients",
                recipe.getName(), recipe.getIngredients().size());
        logger.info(message);
        this.recipeRepository.save(recipe);
    }

    public void updateRecipe(RecipeDTO updatedRecipeDTO, MultipartFile imageFile) throws IOException {
        var id = updatedRecipeDTO.id();
        if (recipeRepository.findById(id).isPresent()) {
            logger.info("Updating recipe");
            createRecipe(updatedRecipeDTO, imageFile);
        } else {
            logger.error("Can't update recipe");
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
        recipe.setCreatedBy(recipeDTO.createdBy());
        recipe.setIngredients(recipeDTO.ingredients().stream().map(this::mapFromIngredientForRecipeDTO).collect(Collectors.toSet()));
        recipe.setProperties();
        return recipe;
    }

    private RecipeDTO mapToDTO(Recipe recipe) {
        Set<IngredientForRecipeDTO> ingredients = recipe.getIngredients().stream().map(this::mapToIngredientForRecipeDTO).collect(Collectors.toSet());

        return new RecipeDTO(
                recipe.getId(),
                ingredients,
                recipe.getName(),
                recipe.getDescription(),
                recipe.getCreatedBy(),
                recipe.isVegan(),
                recipe.isVegetarian(),
                recipe.isDairyFree(),
                recipe.isGlutenFree(),
                recipe.isContainsTreeNuts(),
                "/api/recipes/" + recipe.getId() + "/image"
                );
    }

    private IngredientForRecipeDTO mapToIngredientForRecipeDTO(IngredientForRecipe ingredientForRecipe) {
        return new IngredientForRecipeDTO(
                ingredientForRecipe.getIngredientType().getName(),
                ingredientForRecipe.getAmount()
       );
    }

    private IngredientForRecipe mapFromIngredientForRecipeDTO(IngredientForRecipeDTO ingredientForRecipeDTO) {
        IngredientForRecipe mappedIngredientForRecipe = new IngredientForRecipe();
        mappedIngredientForRecipe.setAmount(ingredientForRecipeDTO.amount());
        IngredientType ingredient = this.ingredientTypeService.getIngredient(ingredientForRecipeDTO.ingredient());
        mappedIngredientForRecipe.setIngredientType(ingredient);
        ingredientForRecipeRepository.save(mappedIngredientForRecipe);
        ingredientForRecipeRepository.flush();
        return mappedIngredientForRecipe;
    }

    public List<RecipeDTO> findRecipe(Map<String, String> params) {
        List<RecipeDTO> recipeDTOS = recipeRepository.findAll(buildSpecification(params))
                .stream()
                .map(this::mapToDTO)
                .toList();
        logger.info(String.format("Found %d recipes", recipeDTOS.size()));
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

}
