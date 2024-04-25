package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientForRecipeDTO;
import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.dto.RecipeDTO;
import com.codecool.cookpad.exception.RecipeNotFoundException;
import com.codecool.cookpad.model.entity.IngredientForRecipe;
import com.codecool.cookpad.model.entity.IngredientType;
import com.codecool.cookpad.model.entity.Recipe;
import com.codecool.cookpad.service.repository.IngredientTypeRepository;
import com.codecool.cookpad.service.repository.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

    @InjectMocks
    private RecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private IngredientTypeService ingredientTypeService;

    private IngredientType ingredient1;
    private IngredientType ingredient2;
    private IngredientType ingredient3;
    private IngredientType ingredient4;
    private IngredientForRecipe ingredientForRecipe1;
    private IngredientForRecipe ingredientForRecipe2;
    private IngredientForRecipe ingredientForRecipe3;
    private IngredientForRecipe ingredientForRecipe4;
    private Recipe recipe1;
    private Recipe recipe2;
    private Recipe recipe3;
    private Recipe recipe4;

    @BeforeEach
    void setUp() {


        ingredient1 = IngredientType.builder()
                .id(1L)
                .name("Milk")
                .isDairyFree(false)
                .isGlutenFree(true)
                .isMeatFree(true)
                .isEggFree(true)
                .unitOfMeasure("l")
                .build();
        ingredient2 = IngredientType.builder()
                .id(2L)
                .name("Wheat")
                .isDairyFree(true)
                .isGlutenFree(false)
                .isMeatFree(true)
                .isEggFree(true)
                .unitOfMeasure("g")
                .build();
        ingredient3 = IngredientType.builder()
                .id(3L)
                .name("Water")
                .isDairyFree(true)
                .isGlutenFree(true)
                .isMeatFree(true)
                .isEggFree(true)
                .unitOfMeasure("l")
                .build();

        ingredient4 = IngredientType.builder()
                .id(3L)
                .name("Chicken Wing")
                .isDairyFree(true)
                .isGlutenFree(true)
                .isMeatFree(true)
                .isEggFree(true)
                .unitOfMeasure("pcs")
                .build();
        ingredientForRecipe1 = IngredientForRecipe.builder()
                .id(1L)
                .ingredientType(ingredient1)
                .amount(1)
                .build();

        ingredientForRecipe2 = IngredientForRecipe.builder()
                .id(2L)
                .ingredientType(ingredient2)
                .amount(1)
                .build();
        ingredientForRecipe3 = IngredientForRecipe.builder()
                .id(3L)
                .ingredientType(ingredient3)
                .amount(1)
                .build();
        ingredientForRecipe4 = IngredientForRecipe.builder()
                .id(4L)
                .ingredientType(ingredient4)
                .amount(1)
                .build();
        recipe1 = Recipe.builder()
                .id(1L)
                .name("Sour Milk")
                .description("do it")
                .ingredients(Set.of(ingredientForRecipe1))
                .build();
        recipe2 = Recipe.builder()
                .id(2L)
                .name("Bread")
                .description("do it")
                .ingredients(Set.of(ingredientForRecipe2))
                .build();
        recipe3 = Recipe.builder()
                .id(3L)
                .name("Boiled Water")
                .description("do it")
                .ingredients(Set.of(ingredientForRecipe3))
                .build();
        recipe4 = Recipe.builder()
                .id(4L)
                .name("KFC chicken wing")
                .description("do it")
                .ingredients(Set.of(ingredientForRecipe4))
                .build();
        recipe1.setProperties();
        recipe2.setProperties();
        recipe3.setProperties();
        recipe4.setProperties();
    }

    @DisplayName("Test for getAllRecipes method")
    @Test
    void testGetAllRecipes_Expected() {
        given(recipeRepository.findAll()).willReturn(List.of(recipe1, recipe2, recipe3, recipe4));

        List<RecipeDTO> recipeList = recipeService.getAllRecipes();

        assertThat(recipeList).isNotNull();
        assertThat(recipeList.size()).isEqualTo(4);
    }

    @DisplayName("Test for getAllRecipes method if there are none")
    @Test
    public void testGetAll_EmptyList() {
        given(recipeRepository.findAll()).willReturn(List.of());

        List<RecipeDTO> recipeList = recipeService.getAllRecipes();

        assertThat(recipeList).isNotNull();
        assertThat(recipeList.size()).isEqualTo(0);
    }

    @DisplayName("Test for getRecipeById method")
    @Test
    void getRecipeById_Expected() {
        given(recipeRepository.findById(recipe1.getId())).willReturn(Optional.of(recipe1));

        RecipeDTO expected = RecipeDTO.builder()
                .id(recipe1.getId())
                .description(recipe1.getDescription())
                .name(recipe1.getName())
                .isDairyFree(false)
                .isVegetarian(true)
                .isGlutenFree(true)
                .isVegan(false)
                .ingredients(Set.of(
                        new IngredientForRecipeDTO(1L,
                                new IngredientTypeDTO(1L,
                                        "Milk",
                                        "l",
                                        true,
                                        false,
                                        true,
                                        true),
                                1)))
                .build();

        RecipeDTO actual = recipeService.getRecipeById(String.valueOf(recipe1.getId()));

        assertEquals(expected.id(), actual.id());
    }

    @DisplayName("Test for getRecipeById if no such Id")
    @Test
    void getRecipeById_NoSuchId(){
        Long id = 1L;

        given(recipeRepository.findById(id)).willReturn(Optional.empty());
        assertThrows(RecipeNotFoundException.class, () -> recipeService.getRecipeById(String.valueOf(id)));

    }

    @DisplayName("Test for getRecipeByName method")
    @Test
    void getRecipeByName_Expected() {
        String name = "ouR";
        List<Recipe> foundRecipes = List.of(recipe1);
        given(recipeRepository.findByNameContainingIgnoreCase(name)).willReturn(foundRecipes);

        RecipeDTO expected = RecipeDTO.builder()
                .id(recipe1.getId())
                .description(recipe1.getDescription())
                .name(recipe1.getName())
                .isDairyFree(false)
                .isVegetarian(true)
                .isGlutenFree(true)
                .isVegan(false)
                .ingredients(Set.of(
                        new IngredientForRecipeDTO(1L,
                                new IngredientTypeDTO(1L,
                                        "Milk",
                                        "l",
                                        true,
                                        false,
                                        true,
                                        true),
                                1)))
                .build();
        RecipeDTO actual = recipeService.getRecipeByName(name).get(0);
        assertThat(expected.name()).isEqualTo(actual.name());
        assertThat(expected.id()).isEqualTo(actual.id());

    }

    @Test
    void findRecipe() {
    }


}