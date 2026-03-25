package com.eazybites.inventory.exceptions;


public class NonRetryableException extends RuntimeException{

    public NonRetryableException(String msg){
        super(msg);
    }
}
