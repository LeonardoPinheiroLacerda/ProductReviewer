package com.leonardo.productreviewer.controllers;

import com.leonardo.productreviewer.inputs.CategoryInput;
import com.leonardo.productreviewer.models.Category;
import com.leonardo.productreviewer.services.CategoryService;
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
public class CategoryController {

    private final CategoryService service;

    @QueryMapping
    public List<Category> getAllCategories() {
        return service.getAll();
    }

    @QueryMapping
    public Category getCategoryById(@Argument UUID id) {
        return service.getById(id);
    }

    @MutationMapping
    @PreAuthorize("isAuthenticated()")
    public Category createCategory(@Argument CategoryInput categoryInput) {
        return service.create(categoryInput);
    }

    @MutationMapping
    public Category updateCategory(@Argument UUID id, @Argument CategoryInput categoryInput) {
        return service.update(id, categoryInput);
    }

    @MutationMapping
    public UUID deleteCategory(@Argument UUID id) {
        return service.delete(id);
    }


}
