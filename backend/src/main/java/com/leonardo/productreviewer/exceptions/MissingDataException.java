package com.leonardo.productreviewer.exceptions;

public class MissingDataException extends RuntimeException{
    public MissingDataException() {
        super();
    }

    public MissingDataException(String message) {
        super(message);
    }

    public MissingDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
