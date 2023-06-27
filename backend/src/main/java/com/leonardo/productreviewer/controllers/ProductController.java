package com.leonardo.productreviewer.controllers;

import com.leonardo.productreviewer.inputs.ProductInput;
import com.leonardo.productreviewer.inputs.ProductPropertyValueInput;
import com.leonardo.productreviewer.inputs.PropertyInput;
import com.leonardo.productreviewer.models.Product;
import com.leonardo.productreviewer.models.Property;
import com.leonardo.productreviewer.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor

@Controller
public class ProductController {

    private final ProductService service;

    @QueryMapping
    public Product getProductById(@Argument UUID id) {
        return service.getById(id);
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        return service.getAll();
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Product setPropertyValue(@Argument ProductPropertyValueInput productPropertyValueInput) {
        return service.setPropertyValue(productPropertyValueInput);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Product createProduct(@Argument ProductInput productInput) {
        return service.create(productInput);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Product updateProduct(@Argument UUID id, @Argument ProductInput productInput) {
        return service.update(id, productInput);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public UUID deleteProduct(@Argument UUID id) {
        return service.delete(id);
    }
}
