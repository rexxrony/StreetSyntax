package com.rex.streetSyntax.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) //404 Not Found response
public class ResourceNotFoundException extends RuntimeException { //RuntimeException is an unchecked exception
    public ResourceNotFoundException (String message){ //constructor
        super(message);
    }
}
