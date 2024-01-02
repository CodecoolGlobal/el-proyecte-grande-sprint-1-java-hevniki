package com.codecool.cookpad.model.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class IngredientForRecipe {
    @Id
    @GeneratedValue
    private Long id;


    @ManyToOne
    @JoinColumn (name = "ingredient_type_id")
    private IngredientType ingredientType;
    private double amount;


    public IngredientType getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(IngredientType ingredientType) {
        this.ingredientType = ingredientType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientForRecipe that = (IngredientForRecipe) o;
        return Double.compare(amount, that.amount) == 0 && Objects.equals(id, that.id) && Objects.equals(ingredientType, that.ingredientType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ingredientType, amount);
    }
}