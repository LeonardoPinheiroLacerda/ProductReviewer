package com.leonardo.productreviewer.utils;

import com.leonardo.productreviewer.exceptions.UUIDException;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDUtils {

    public UUID parseFromString(String id) {
        try{
            return UUID.fromString(id);
        }catch (Exception e) {
            throw new UUIDException("ID inválido.");
        }
    }

}
