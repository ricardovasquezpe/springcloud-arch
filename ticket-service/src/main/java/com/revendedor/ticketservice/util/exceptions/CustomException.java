package com.revendedor.ticketservice.util.exceptions;

public class CustomException extends RuntimeException{
    public CustomException(String msg){
        super(msg);
    }

    public CustomException(String message, Throwable cause){
        super(message, cause);
    }
}
