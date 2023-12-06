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

    public Recipe() {
    }
    public void setId(long id) {
        this.id = id;
    }

    public void setIngredients(Set<IngredientForRecipe> ingredients) {
        this.ingredients = ingredients;
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
        return ingredients.stream().allMatch(ingredient->ingredient.getIngredientType().isVegan());
    }
    public boolean isVegetarian(){
        return ingredients.stream().allMatch(ingredient->ingredient.getIngredientType().isMeatFree());
    }
    public boolean isGlutenFree(){
        return ingredients.stream().allMatch(ingredient->ingredient.getIngredientType().isGlutenFree());
    }
    public boolean isDairyFree(){
        return ingredients.stream().allMatch(ingredient->ingredient.getIngredientType().isDairyFree());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return id == recipe.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredients, name, description);
    }
}
