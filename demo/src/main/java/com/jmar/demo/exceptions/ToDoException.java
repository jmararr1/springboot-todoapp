package com.jmar.demo.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ToDoException extends RuntimeException{
    private String message;
    private HttpStatus httpStatus;

    public ToDoException (HttpStatus httpStatus, String message){
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;

    }
}
