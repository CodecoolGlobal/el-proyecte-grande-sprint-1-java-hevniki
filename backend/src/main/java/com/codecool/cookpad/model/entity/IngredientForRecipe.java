package com.codecool.cookpad.model.entity;

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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_type_id")
    private IngredientType ingredientType;

    private String amount;
}

