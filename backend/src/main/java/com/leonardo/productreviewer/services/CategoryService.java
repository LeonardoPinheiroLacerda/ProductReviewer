package com.leonardo.productreviewer.services;

import com.leonardo.productreviewer.inputs.CategoryInput;
import com.leonardo.productreviewer.models.Category;
import com.leonardo.productreviewer.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public record CategoryService(CategoryRepository repository) {

    public Category create(CategoryInput input) {
        Category category = repository.save(
                Category
                        .builder()
                        .description(input.description())
                        .build()
        );
        return category;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }
}
