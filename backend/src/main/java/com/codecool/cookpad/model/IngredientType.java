package com.codecool.cookpad.model;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class IngredientType {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String unitOfMeasure;
    private boolean isGlutenFree;
    private boolean isDairyFree;
    private boolean isMeatFree;
    private boolean isEggFree;

    public IngredientType() {
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public boolean isGlutenFree() {
        return isGlutenFree;
    }

    public boolean isDairyFree() {
        return isDairyFree;
    }

    public boolean isMeatFree() {
        return isMeatFree;
    }

    public boolean isEggFree() {
        return isEggFree;
    }

    public boolean isVegan(){
        return isDairyFree && isEggFree && isMeatFree;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientType that = (IngredientType) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
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
