package com.codecool.cookpad.model;

import java.util.Objects;
import java.util.UUID;

public class Ingredient {
    private final UUID id;
    private final String name;
    private final String unitOfMeasure;
    private final boolean isGlutenFree;
    private final boolean isDairyFree;
    private final boolean isMeatFree;
    private final boolean isEggFree;

    public Ingredient(String name, String unitOfMeasure, boolean isGlutenFree, boolean isDairyFree, boolean isMeatFree, boolean isEggFree) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.unitOfMeasure = unitOfMeasure;
        this.isGlutenFree = isGlutenFree;
        this.isDairyFree = isDairyFree;
        this.isMeatFree = isMeatFree;
        this.isEggFree = isEggFree;
    }

    public UUID getId() {
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
        Ingredient that = (Ingredient) o;
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
