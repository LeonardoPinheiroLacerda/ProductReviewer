package com.leonardo.productreviewer.controllers;

import com.leonardo.productreviewer.inputs.PropertyInput;
import com.leonardo.productreviewer.models.Property;
import com.leonardo.productreviewer.services.PropertyService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public record PropertyController (PropertyService service){

    @MutationMapping
    public Property createProperty(@Argument PropertyInput propertyInput) {
        return service.createProperty(propertyInput);
    }
}
