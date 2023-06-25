package com.leonardo.productreviewer.utils;

import com.leonardo.productreviewer.exceptions.MissingDataException;
import org.springframework.stereotype.Component;

@Component
public class DataIntegrityUtils {

    public void checkNullOrEmptyAndThrowException(String field, String message) {
        try {
            if (field.equals("") || field == null) {
                throw new MissingDataException(message);
            }
        } catch (NullPointerException e) {
            throw new MissingDataException(message, e);
        }
    }

    public void checkNullAndThrowException(Object field, String message) {
        try {
            if (field == null) {
                throw new MissingDataException(message);
            }
        } catch (NullPointerException e) {
            throw new MissingDataException(message, e);
        }
    }

    public Boolean checkNullOrEmpty(String field) {
        try {
            return field.equals("") || field == null;
        } catch (NullPointerException e) {
            return true;
        }

    }

    public Boolean checkNullOrEmpty(Float field) {
        try {
            return field.equals(0f) || field == null;
        } catch (NullPointerException e) {
            return true;
        }

    }

    public Boolean checkNull(Object field) {
        return field == null;
    }

}
