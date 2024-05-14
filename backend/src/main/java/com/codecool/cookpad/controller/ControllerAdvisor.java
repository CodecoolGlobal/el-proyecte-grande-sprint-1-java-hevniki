package com.codecool.cookpad.controller;
import com.codecool.cookpad.exception.BadRequestException;
import com.codecool.cookpad.exception.IngredientNotFoundException;
import com.codecool.cookpad.exception.RecipeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
@ControllerAdvice
public class ControllerAdvisor {
    @ResponseBody
    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String recipeNotFoundException(RecipeNotFoundException ex) {

        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(IngredientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String ingredientNotFoundException(IngredientNotFoundException ex) {

        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badQueryException(RecipeNotFoundException ex) {

        return ex.getMessage();
    }
}
