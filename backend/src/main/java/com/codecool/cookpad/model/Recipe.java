package com.codecool.cookpad.model;

import java.util.*;

import jakarta.persistence.*;

@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private Set<IngredientForRecipe> ingredients; // lombok builder
    private String name;
    private String description;
    private boolean vegan;
    private boolean vegetarian;
    private boolean dairyFree;
    private boolean glutenFree;

    public Recipe() {
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setIngredients(Set<IngredientForRecipe> ingredients) {
        this.ingredients = ingredients;
        setProperties();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }
    public Set<IngredientForRecipe> getIngredients() {
        return ingredients;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isVegan(){
        return this.vegan;
    }
    public boolean isVegetarian(){
        return this.vegetarian;
    }
    public boolean isGlutenFree(){
        return this.glutenFree;
    }
    public boolean isDairyFree(){
        return this.dairyFree;
    }

    public void setProperties() {
        this.vegan=ingredients.stream().allMatch(ingredient->ingredient.getIngredientType().isVegan());
        this.vegetarian=ingredients.stream().allMatch(ingredient->ingredient.getIngredientType().isMeatFree());
        this.glutenFree=ingredients.stream().allMatch(ingredient->ingredient.getIngredientType().isGlutenFree());
        this.dairyFree = ingredients.stream().allMatch(ingredient->ingredient.getIngredientType().isDairyFree());


    }


}
