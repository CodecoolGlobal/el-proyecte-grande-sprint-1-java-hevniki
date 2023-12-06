package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientForRecipeDTO;
import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.exception.BadQueryException;
import com.codecool.cookpad.model.IngredientForRecipe;
import com.codecool.cookpad.model.Recipe;
import com.codecool.cookpad.exception.RecipeNotFoundException;
import com.codecool.cookpad.service.repository.RecipeRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

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

    public List<RecipeDTO> getRecipeByName(String name) {
        List<Recipe> foundRecipes = recipeRepository.findByNameContainingIgnoreCase(name);
        return foundRecipes.stream().map(this::mapToDTO).toList();
    }

    public boolean deleteRecipe(String id) {
        Optional<Recipe> optionalRecipe = this.recipeRepository.findById(Long.valueOf(id));
        if (optionalRecipe.isPresent()) {
            this.recipeRepository.delete(optionalRecipe.get());
            return true;
        }
        throw new RecipeNotFoundException();
    }

    public void createRecipe(RecipeDTO newRecipeDTO) {
        Recipe recipe = mapFromDTO(newRecipeDTO);
        recipe.setProperties();
        this.recipeRepository.save(mapFromDTO(newRecipeDTO));
    }

    public void updateRecipe(RecipeDTO updatedRecipeDTO) {
        if (recipeRepository.findById(updatedRecipeDTO.id()).isPresent()) {
            createRecipe(updatedRecipeDTO);
        }
        throw new RecipeNotFoundException();
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
        mappedIngredientForRecipe.setIngredientType(this.ingredientTypeService.mapFromDTO(ingredientForRecipeDTO.ingredient()));
        return mappedIngredientForRecipe;
    }

    public List<RecipeDTO> findRecipe(Map<String, String> params) {
        return recipeRepository.findAll(buildSpecification(params))
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private Specification<Recipe> buildSpecification(Map<String, String> params) {
        Specification<Recipe> spec = Specification.where(null);
        Object glutenFree = params.get("glutenFree");
        List<Long> ingredients=new ArrayList<>();
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
               ingredients.addAll(getIdsFromReqParams(params.get("ingredients")));
               spec=spec.and(hasIngredientTypeIn(ingredients));
            }

            return spec;
        } catch (NumberFormatException e) {
            throw new BadQueryException();
        }
    }

    private List<Long> getIdsFromReqParams(String param){
        String[] ids = param.split(",");
        List<Long> idsAsLong = new ArrayList<>();
        try{
            for (String id : ids){
                idsAsLong.add( Long.parseLong(id));
            }
        }
        catch (NumberFormatException e) {
            throw new BadQueryException();
        }
        return idsAsLong;
    }

    public static Specification<Recipe> hasIngredientTypeIn(List<Long> ingredientIds) {
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
