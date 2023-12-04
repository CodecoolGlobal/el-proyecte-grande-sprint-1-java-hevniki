package com.codecool.cookpad.model;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Recipe {
    @Id
    @GeneratedValue
    private long id;
    @OneToMany
    private Set<IngredientForRecipe> ingredients = new HashSet<>();
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
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Recipe{" +
                "ingredients=" + ingredients +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }


}
