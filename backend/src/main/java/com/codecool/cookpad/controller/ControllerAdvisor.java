package com.codecool.cookpad.controller;
import com.codecool.cookpad.exception.BadRequestException;
import com.codecool.cookpad.exception.IngredientNotFoundException;
import com.codecool.cookpad.exception.RecipeNotFoundException;
import com.codecool.cookpad.security.AuthEntryPointJwt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvisor {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    @ResponseBody
    @ExceptionHandler(RecipeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String recipeNotFoundException(RecipeNotFoundException ex) {
        var message = ex.getMessage();
        logger.error(message);
        return message;
    }
    @ResponseBody
    @ExceptionHandler(IngredientNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String ingredientNotFoundException(IngredientNotFoundException ex) {
        var message = ex.getMessage();
        logger.error(message);
        return message;
    }
    @ResponseBody
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String badQueryException(RecipeNotFoundException ex) {
        var message = ex.getMessage();
        logger.error(message);
        return message;
    }
}
