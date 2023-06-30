package com.leonardo.productreviewer.exceptions;

public class PropertyTypeException extends RuntimeException{
    public PropertyTypeException() {
        super();
    }

    public PropertyTypeException(String message) {
        super(message);
    }

    public PropertyTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
