package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.model.IngredientType;
import com.codecool.cookpad.service.repository.IngredientTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class IngredientTypeServiceTest {

    @Mock
    private IngredientTypeRepository ingredientTypeRepository;

    @InjectMocks
    private IngredientTypeService ingredientTypeService;

    private IngredientType ingredient;
    private IngredientType ingredient1;

    @BeforeEach
    public void setup() {
        ingredientTypeRepository = mock(IngredientTypeRepository.class);
        ingredientTypeService = new IngredientTypeService(ingredientTypeRepository);
        ingredient = IngredientType.builder()
                .id(1L)
                .name("Salt")
                .isDairyFree(true)
                .isGlutenFree(true)
                .isMeatFree(true)
                .isEggFree(true)
                .unitOfMeasure("g")
                .build();

        ingredient1 = IngredientType.builder()
                .id(2L)
                .name("Sugar")
                .isDairyFree(true)
                .isGlutenFree(true)
                .isMeatFree(true)
                .isEggFree(true)
                .unitOfMeasure("g")
                .build();
    }

    @DisplayName("Test for getAllEmployees method")
    @Test
    public void givenIngredientList_whenGetAllIngredients_thenReturnIngredients() {
        given(ingredientTypeRepository.findAll()).willReturn(List.of(ingredient, ingredient1));

        List<IngredientTypeDTO> ingredientList = ingredientTypeService.getAllIngredients();

        assertThat(ingredientList).isNotNull();
        assertThat(ingredientList.size()).isEqualTo(2);
    }

    @DisplayName("Test for mapToDTO method")
    @Test
    public void givenIngredient_whenMapToDTO_thenReturnIngredientDTO() {
        IngredientTypeDTO expected = new IngredientTypeDTO(1L, "Salt", "g", true, true, true, true);
        IngredientTypeDTO actual = ingredientTypeService.mapToDTO(ingredient);

        assertEquals(expected, actual);
    }

    @DisplayName("Test for getIngredientById method")
    @Test
    public void givenIngredientId_whenGetIngredientById_thenReturnIngredientDTO() {
        given(ingredientTypeRepository.findById(ingredient.getId())).willReturn(Optional.of(ingredient));

        IngredientTypeDTO expected = new IngredientTypeDTO(1L, "Salt", "g", true, true, true, true);
        IngredientTypeDTO actual = ingredientTypeService.getIngredientById("1");
    }
}