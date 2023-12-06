package com.codecool.cookpad.model;

public class RecipeNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Recipe not found";
    public RecipeNotFoundException() {
        super(MESSAGE);
    }
}
