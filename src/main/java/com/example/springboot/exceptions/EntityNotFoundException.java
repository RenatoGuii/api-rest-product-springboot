package com.example.springboot.exceptions;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException (String msg) {
        super(msg);
    }

}
