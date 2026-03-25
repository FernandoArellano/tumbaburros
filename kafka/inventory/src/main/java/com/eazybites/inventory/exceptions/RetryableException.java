package com.eazybites.inventory.exceptions;

public class RetryableException extends RuntimeException{

    public RetryableException(String msg){
        super(msg);
    }
}
