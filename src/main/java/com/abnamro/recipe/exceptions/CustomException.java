package com.abnamro.recipe.exceptions;

import org.springframework.http.HttpStatus;

public interface CustomException {
    HttpStatus getStatus();
}