package com.leonardo.productreviewer.exceptions;

public class UUIDException extends RuntimeException{

    public UUIDException(String message) {
        super(message);
    }

    public UUIDException(String message, Throwable cause) {
        super(message, cause);
    }
}
