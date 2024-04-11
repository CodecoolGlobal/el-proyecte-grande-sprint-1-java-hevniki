package com.codecool.cookpad.service;

import com.codecool.cookpad.dto.IngredientTypeDTO;
import com.codecool.cookpad.model.entity.IngredientType;
import com.codecool.cookpad.service.repository.IngredientTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

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

    private IngredientType ingredient1;
    private IngredientType ingredient2;
    private IngredientType ingredient3;

    @BeforeEach
    public void setup() {
        ingredientTypeRepository = mock(IngredientTypeRepository.class);
        ingredientTypeService = new IngredientTypeService(ingredientTypeRepository);
        ingredient1 = IngredientType.builder()
                .id(1L)
                .name("Salt")
                .isDairyFree(true)
                .isGlutenFree(true)
                .isMeatFree(true)
                .isEggFree(true)
                .unitOfMeasure("g")
                .build();

        ingredient2 = IngredientType.builder()
                .id(2L)
                .name("Sugar")
                .isDairyFree(true)
                .isGlutenFree(true)
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
    }

    @DisplayName("Test for findAll method")
    @Test
    public void testFindAll_Expected() {
        given(ingredientTypeRepository.findAll()).willReturn(List.of(ingredient1, ingredient2, ingredient3));

        List<IngredientTypeDTO> ingredientList = ingredientTypeService.getAllIngredients();

        assertThat(ingredientList).isNotNull();
        assertThat(ingredientList.size()).isEqualTo(3);
    }

    @DisplayName("Test for mapToDTO method")
    @Test
    public void testMapToDTO_expected() {
        IngredientTypeDTO expected = new IngredientTypeDTO(1L, "Salt", "g", true, true, true, true);
        IngredientTypeDTO actual = ingredientTypeService.mapToDTO(ingredient1);

        assertEquals(expected, actual);
    }

    @DisplayName("Test for mapFromDTO method")
    @Test
    public void testMapFromDTO_expected() {
        given(ingredientTypeRepository.findById(ingredient1.getId())).willReturn(Optional.of(ingredient1));

        IngredientType expected = ingredient1;
        IngredientType actual = ingredientTypeService.mapFromDTO(new IngredientTypeDTO(1L, "Salt", "g", true, true, true, true));
        assertEquals(expected, actual);
    }

    @DisplayName("Test for getIngredientById method")
    @Test
    public void givenIngredientId_whenGetIngredientById_thenReturnIngredientDTO() {
        given(ingredientTypeRepository.findById(ingredient1.getId())).willReturn(Optional.of(ingredient1));

        IngredientTypeDTO expected = new IngredientTypeDTO(1L, "Salt", "g", true, true, true, true);
        IngredientTypeDTO actual = ingredientTypeService.getIngredientById("1");
        assertEquals(expected, actual);
    }
}