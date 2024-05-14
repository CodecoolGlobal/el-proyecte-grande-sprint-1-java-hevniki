package com.codecool.cookpad.exception;

public class RecipeNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Recipe not found with id: ";
    public RecipeNotFoundException(String id) {
        super(MESSAGE + id);
    }
}
