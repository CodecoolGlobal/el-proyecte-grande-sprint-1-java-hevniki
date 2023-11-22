package com.codecool.cookpad.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class Recipe {
    private final UUID id;
    private final Map<Ingredient, Double> ingredients;
    private final String name;
    private final String description;

    public Recipe(Map<Ingredient, Double> ingredients, String name, String description) {
        this.id = UUID.randomUUID();
        this.ingredients = new HashMap<>(ingredients);
        this.name = name;
        this.description = description;
    }
    public UUID getId() {
        return id;
    }
    public Map<Ingredient, Double> getIngredients() {
        return ingredients;
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public boolean isVegan(){
        return ingredients.keySet().stream().allMatch(Ingredient::isVegan);
    }
    public boolean isVegetarian(){
        return ingredients.keySet().stream().allMatch(Ingredient::isMeatFree);
    }
    public boolean isGlutenFree(){
        return ingredients.keySet().stream().allMatch(Ingredient::isGlutenFree);
    }
    public boolean isDairyFree(){
        return ingredients.keySet().stream().allMatch(Ingredient::isDairyFree);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return Objects.equals(ingredients, recipe.ingredients) && Objects.equals(name, recipe.name) && Objects.equals(description, recipe.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredients, name, description);
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
