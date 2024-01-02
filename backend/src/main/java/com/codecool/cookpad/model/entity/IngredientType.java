package com.codecool.cookpad.model.entity;

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
    private String unitOfMeasure;
    private boolean isGlutenFree;
    private boolean isDairyFree;
    private boolean isMeatFree;
    private boolean isEggFree;

    public boolean isVegan(){
        return isDairyFree && isEggFree && isMeatFree;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", unitOfMeasure='" + unitOfMeasure + '\'' +
                ", isGlutenFree=" + isGlutenFree +
                ", isDairyFree=" + isDairyFree +
                ", isMeatFree=" + isMeatFree +
                ", isEggFree=" + isEggFree +
                '}';
    }
}
