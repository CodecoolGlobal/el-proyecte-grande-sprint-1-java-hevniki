package com.codecool.cookpad.exception;

public class ImageNotFoundException extends RuntimeException {
    private static final String MESSAGE = "Image not found for recipe with id: ";
    public ImageNotFoundException(String id) {
        super(MESSAGE + id);
    }
}
