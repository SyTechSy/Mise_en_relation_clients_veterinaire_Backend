package com.odk3.vetcare.exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message) {
        super(message);
    }
}
