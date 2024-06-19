package com.codecool.cookpad.model.entity;

import java.util.*;

import com.codecool.cookpad.model.IngredientCategory;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "recipe_id")
    private Set<IngredientForRecipe> ingredients;

    private String name;
    private String description;

    @Lob
    private byte[] image;
    private String createdBy;
    private boolean vegan;
    private boolean vegetarian;
    private boolean dairyFree;
    private boolean glutenFree;
    private boolean containsTreeNuts;

    public void setProperties() {
        this.vegan = ingredients.stream().noneMatch(ingredient ->
                ingredient.getIngredientType().getCategory() == IngredientCategory.MEAT ||
                        ingredient.getIngredientType().getCategory() == IngredientCategory.FISH ||
                        ingredient.getIngredientType().getCategory() == IngredientCategory.EGG ||
                        ingredient.getIngredientType().getCategory() == IngredientCategory.MILK_OR_DAIRY
        );
        this.vegetarian = ingredients.stream().noneMatch(ingredient ->
                ingredient.getIngredientType().getCategory() == IngredientCategory.MEAT ||
                        ingredient.getIngredientType().getCategory() == IngredientCategory.FISH
        );

        this.glutenFree = ingredients.stream().noneMatch(ingredient ->
                ingredient.getIngredientType().getCategory() == IngredientCategory.WHEAT);
        this.dairyFree = ingredients.stream().allMatch(ingredient ->
                ingredient.getIngredientType().getCategory() == IngredientCategory.MILK_OR_DAIRY);
        this.containsTreeNuts = ingredients.stream().anyMatch(ingredient ->
                ingredient.getIngredientType().getCategory() == IngredientCategory.TREE_NUT);
    }
}
