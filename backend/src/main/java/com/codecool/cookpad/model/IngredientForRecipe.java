package com.codecool.cookpad.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode

@Entity
public class IngredientForRecipe {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn (name = "ingredient_type_id")
    private IngredientType ingredientType;
    private double amount;

}
