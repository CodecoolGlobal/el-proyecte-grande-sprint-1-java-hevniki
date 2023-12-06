package com.codecool.cookpad.exception;

public class RecipeNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Recipe not found";
    public RecipeNotFoundException() {
        super(MESSAGE);
    }
}
