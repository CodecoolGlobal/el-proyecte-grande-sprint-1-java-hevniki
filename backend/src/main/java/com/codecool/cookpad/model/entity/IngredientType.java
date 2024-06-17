package com.codecool.cookpad.model.entity;

import com.codecool.cookpad.model.IngredientCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode

@Entity
public class IngredientType {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private IngredientCategory category;
    private boolean approved;
//    public boolean isVegan(){
//        return isDairyFree && isEggFree && isMeatFree;
//    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
