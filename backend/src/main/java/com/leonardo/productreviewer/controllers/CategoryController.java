package com.leonardo.productreviewer.controllers;

import com.leonardo.productreviewer.inputs.CategoryInput;
import com.leonardo.productreviewer.models.Category;
import com.leonardo.productreviewer.services.CategoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public record CategoryController(CategoryService service) {

    @QueryMapping
    public List<Category> allCategories() {
        return service.getAll();
    }

    @MutationMapping
    public Category addCategory(@Argument CategoryInput categoryInput) {
        return service.create(categoryInput);
    }

}
