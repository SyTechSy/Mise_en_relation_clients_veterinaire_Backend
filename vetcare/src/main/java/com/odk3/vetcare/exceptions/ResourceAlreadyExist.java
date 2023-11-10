package com.odk3.vetcare.exceptions;

public class ResourceAlreadyExist extends RuntimeException{

    public ResourceAlreadyExist(String message){
        super(message);
    }
}
