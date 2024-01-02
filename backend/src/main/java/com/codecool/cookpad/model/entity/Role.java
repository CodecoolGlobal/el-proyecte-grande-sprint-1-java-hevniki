package com.codecool.cookpad.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@Entity
public class Role {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private boolean canAddIngredient;
    private boolean canDeleteIngredient;
    private boolean canModify;




}
