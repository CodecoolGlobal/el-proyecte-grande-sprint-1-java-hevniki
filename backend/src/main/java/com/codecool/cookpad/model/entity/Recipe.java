package com.codecool.cookpad.model.entity;

import java.util.*;

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
    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
    @JoinColumn(name = "recipe_id")
    private Set<IngredientForRecipe> ingredients;
    private String name;
    private String description;
    @Lob
    private byte[] image;
    private String createdBy;
}
