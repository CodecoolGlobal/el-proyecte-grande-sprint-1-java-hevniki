package com.codecool.cookpad.model;

import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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

    public IngredientType() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
    }

    public void setGlutenFree(boolean glutenFree) {
        isGlutenFree = glutenFree;
    }

    public void setDairyFree(boolean dairyFree) {
        isDairyFree = dairyFree;
    }

    public void setMeatFree(boolean meatFree) {
        isMeatFree = meatFree;
    }

    public void setEggFree(boolean eggFree) {
        isEggFree = eggFree;
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
        return isGlutenFree == that.isGlutenFree && isDairyFree == that.isDairyFree && isMeatFree == that.isMeatFree && isEggFree == that.isEggFree && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(unitOfMeasure, that.unitOfMeasure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, unitOfMeasure, isGlutenFree, isDairyFree, isMeatFree, isEggFree);
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
