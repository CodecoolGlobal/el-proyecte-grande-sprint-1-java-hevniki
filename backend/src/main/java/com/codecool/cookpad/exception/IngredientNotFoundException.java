package com.codecool.cookpad.exception;

public class IngredientNotFoundException extends RuntimeException{
    private static final String MESSAGE = "Ingredient not found with id: ";
    public IngredientNotFoundException(String id) {
        super(MESSAGE + id);
    }
}
