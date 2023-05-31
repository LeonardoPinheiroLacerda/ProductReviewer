package com.leonardo.productreviewer.controllers;

import com.leonardo.productreviewer.inputs.PropertyInput;
import com.leonardo.productreviewer.models.Property;
import com.leonardo.productreviewer.services.PropertyService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public record PropertyController (PropertyService service){

    @QueryMapping
    public Property getPropertyById(@Argument UUID id) {
        return service.getById(id);
    }

    @QueryMapping
    public List<Property> getAllProperties() {
        return service.getAll();
    }

    @MutationMapping
    public Property createProperty(@Argument PropertyInput propertyInput) {
        return service.create(propertyInput);
    }

    @MutationMapping
    public Property updateProperty(@Argument UUID id, @Argument PropertyInput propertyInput) {
        return service.update(id, propertyInput);
    }

    @MutationMapping
    public UUID deleteProperty(@Argument UUID id) {
        return service.delete(id);
    }
}
